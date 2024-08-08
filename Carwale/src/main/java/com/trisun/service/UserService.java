package com.trisun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trisun.config.PasswordEncoderConfig;
import com.trisun.config.UserAuthenticationProvider;
import com.trisun.core.UserRoles;
import com.trisun.entity.Admin;
import com.trisun.entity.Buyer;
import com.trisun.entity.Seller;
import com.trisun.entity.User;
import com.trisun.entity.UserType;
import com.trisun.entity.dto.AdminDto;
import com.trisun.entity.dto.BuyerDto;
import com.trisun.entity.dto.SellerDto;
import com.trisun.entity.dto.UserAuthDto;
import com.trisun.exceptionhandler.DataNotPresentException;
import com.trisun.exceptionhandler.EmailNotFoundException;
import com.trisun.exceptionhandler.IllegalRequesrException;
import com.trisun.repository.AdminRepository;
import com.trisun.repository.BuyerRepository;
import com.trisun.repository.SellerRepository;
import com.trisun.repository.UserRepository;
import com.trisun.repository.UserTypeRepository;
import com.trisun.response.ApiResponse;
import com.trisun.utils.DtoMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserTypeRepository userTypeRepository;
	private final BuyerRepository buyerRepository;
	private final UserAuthenticationProvider userAuthenticationProvider;
	private final SellerRepository sellerRepository;
	private final AdminRepository adminRepository;

	@Transactional
	public ApiResponse createUser(UserAuthDto userRegisterDto) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			UserType userType = userTypeRepository.findByRole(userRegisterDto.getUserRole())
					.orElseThrow(() -> new RuntimeException("unKnown uSerRole"));			
			User user = new User();
			user.setEmailId(userRegisterDto.getEmail());
			String encodedPassword = passwordEncoder.encode(userRegisterDto.getPassword());
			user.setPassword(encodedPassword);
			user.setUserType(userType);
			User savedUser = userRepository.save(user);
			
			System.out.println("user is saved in database");
			if (userRegisterDto.getUserRole().equals(UserRoles.SELLER)) {				
				Seller seller = new Seller();
				seller.setFullName(userRegisterDto.getFullName());
				seller.setUser(savedUser);
				Seller savedSeller = sellerRepository.save(seller);
				
				SellerDto sellerDto = DtoMapper.sellerToSellerDto(savedSeller, savedUser);
				String token = userAuthenticationProvider.createToken(seller.getId(),userRegisterDto.getEmail(),UserRoles.SELLER);
				sellerDto.setToken(token);
				System.out.println("saved seller ====== "+sellerDto);
				return apiResponse.success(sellerDto);
			} else if (userRegisterDto.getUserRole().equals(UserRoles.BUYER)) {				
				Buyer buyer = new Buyer();
				buyer.setFullName(userRegisterDto.getFullName());
				buyer.setUser(savedUser);
				Buyer savedBuyer = buyerRepository.save(buyer);
				BuyerDto buyerDto = DtoMapper.buyerToBuyerDto(savedBuyer, savedUser);
				String token = userAuthenticationProvider.createToken(buyer.getId(), userRegisterDto.getEmail(),UserRoles.BUYER);
				buyerDto.setToken(token);
				return apiResponse.success(buyerDto);
			} else if (userRegisterDto.getUserRole().equals(UserRoles.ADMIN) ) {
				Admin admin = new Admin();
				admin.setFullName(userRegisterDto.getFullName());
				admin.setUser(savedUser);
				Admin savedAdmin = adminRepository.save(admin);
				AdminDto adminDto = DtoMapper.adminToAdminDto(savedAdmin, savedUser);
				String token = userAuthenticationProvider.createToken(admin.getId(), userRegisterDto.getEmail(),UserRoles.ADMIN);
				adminDto.setToken(token);
				return apiResponse.success(adminDto);
				
			} else {
				return apiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "data not saved in databse");
			}

		} catch (Exception e) {
			return apiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "data not saved in databse");
		}
	}

	public ApiResponse login(UserAuthDto userAuthDto) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			UserType userType = userTypeRepository.findByRole(userAuthDto.getUserRole())
					.orElseThrow(() -> new RuntimeException("unKnown uSerRole"));
			User user = userRepository.findByEmailId(userAuthDto.getEmail())
					.orElseThrow(() -> new RuntimeException(userAuthDto.getEmail()));
			if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
				if (userAuthDto.getUserRole().equals(UserRoles.BUYER)) {
					Buyer buyer = buyerRepository.findByUser(user)
							.orElseThrow(() -> new RuntimeException("Buyer not found"));
					BuyerDto buyerDto = DtoMapper.buyerToBuyerDto(buyer, user);
					String token = userAuthenticationProvider.createToken(buyer.getId(), userAuthDto.getEmail(),UserRoles.BUYER);
					buyerDto.setToken(token);
					return apiResponse.success(buyerDto);
				} else if (userAuthDto.getUserRole().equals(UserRoles.SELLER)) {
					Seller seller = sellerRepository.findByUser(user)
							.orElseThrow(() -> new RuntimeException("Seller not found"));
					SellerDto sellerDto = DtoMapper.sellerToSellerDto(seller, user);
					String token = userAuthenticationProvider.createToken(seller.getId(), userAuthDto.getEmail(),UserRoles.SELLER);
					sellerDto.setToken(token);
					return apiResponse.success(sellerDto);
				}else if(userAuthDto.getUserRole().equals(UserRoles.ADMIN)) {
					Admin admin = adminRepository.findByUser(user)
							.orElseThrow(() -> new RuntimeException("Admin not found"));
					AdminDto adminDto = DtoMapper.adminToAdminDto(admin, user);
					String token = userAuthenticationProvider.createToken(admin.getId(), userAuthDto.getEmail(),UserRoles.ADMIN);
					adminDto.setToken(token);
					return apiResponse.success(adminDto);
	
				}
				
				else {
					return apiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "user type Not found ");
				}
			} else {
				return apiResponse.error(HttpStatus.UNAUTHORIZED.value(), "invalid user");
			}
		} catch (Exception e) {
			return apiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "data not saved in databse");
		}
	}
	

}
