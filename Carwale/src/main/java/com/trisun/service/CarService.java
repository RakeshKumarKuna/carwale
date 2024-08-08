package com.trisun.service;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.trisun.core.FuelType;
import com.trisun.entity.Brand;
import com.trisun.entity.Car;
import com.trisun.entity.Category;
import com.trisun.entity.Image;
import com.trisun.entity.Seller;
import com.trisun.entity.dto.CarDto;
import com.trisun.repository.BrandRepository;
import com.trisun.repository.CarRepository;
import com.trisun.repository.CategoryRepository;
import com.trisun.repository.ImageRepository;
import com.trisun.repository.SellerRepository;
import com.trisun.response.ApiResponse;
import com.trisun.utils.DtoMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CarService {

	private final CarRepository carRepository;
	private final SellerRepository sellerRepository;
	private final ImageRepository imageRepository;
	private final BrandRepository brandRepository;
	private final CategoryRepository categoryRepository;
	
	@Transactional
	public ApiResponse addCar(CarDto carDto,Integer id) {
		System.out.println("CarService.addCar()++++++++++++++entry");
		ApiResponse apiResponse = new ApiResponse();
		Image image = new Image();
		try {
			 System.out.println("CarService.addCar()++++++++++++++entry 2 id "+id);
			  Seller seller = sellerRepository.findById(id)
					  .orElseThrow(()-> new RuntimeException("seller not found"));
				//System.out.println("Seller++++"+seller);		
			    Brand brand = brandRepository.findByName(carDto.getBrand())
					  .orElseThrow(()-> new RuntimeException("brand not found"));	
						System.out.println("brand found");
					//	System.out.println("Brand+++++"+brand);
				Category category=categoryRepository.findByName(carDto.getCategory())
						 .orElseThrow(()-> new RuntimeException("Category not found"));
				//System.out.println("Category +++++ "+category);
				System.out.println("Category found");
						image.setUrl(carDto.getImage());
						Image savedImage = imageRepository.save(image);
						Car car = new Car();
						car.setName(carDto.getName());
						car.setCarNumber(carDto.getCarNumber());
						car.setContact(carDto.getContact());
						car.setModel(carDto.getModel());
						car.setPrice(carDto.getPrice());	
									
						car.setImage(savedImage);
						car.setDescription(carDto.getDescription());
						car.setCurrentLocation(carDto.getCurrentLocation());
						car.setFuelType(FuelType.valueOf(carDto.getFuelType()));
						car.setPincode(carDto.getPincode());
						System.out.println("after pin code");
    				  //car.setBrand(brand); 
     				 // car.setCategory(category);	
					//  car.setSeller(seller);
						System.out.println("after Seller");
						Car  savedCar = carRepository.save(car);	
						CarDto carToCarDto = DtoMapper.carToCarDto(savedCar, brand, category, savedImage);
						System.out.println ("Car Saved CarService.addCar()++++++++++++++ end");						
						apiResponse.success(carToCarDto);
						return apiResponse;
		} catch (Exception e) {
			System.out.println("inside catch");
			 throw new RuntimeException();		
			 }	
	}
	
	public ApiResponse getLimitedCars(Integer limit) {
		List<Car> allCars = carRepository.findAll(PageRequest.of(0, limit)).getContent();
		ApiResponse apiResponse = new ApiResponse();	
		apiResponse.success(allCars);	
		System.out.println("api response all cars "+apiResponse);
		return apiResponse;
	}
	public ApiResponse getAllCars() {
		List<Car> allCars = carRepository.findAll();
		ApiResponse apiResponse = new ApiResponse();	
		apiResponse.success(allCars);	
		System.out.println("api response all cars "+apiResponse);
		return apiResponse;
	}
	
	public ApiResponse getCarById(Integer id) {
		Car carById = carRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("car not found"));
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.success(carById);
		return apiResponse;
	}
	
	
	

	
	
//	private final  SellerRepository sellerRepo;
//	private final CarRepository carRepository;
//	private Car mapToCar(CarDto request) {
//		return Car.builder()
//				.brand(request.getBrand())
//				.category(request.getCategory())
//				.colour(request.getColour())
//				.fuelType(null)
//				.image(request.getImage())
//				.name(request.getName())
//				.year(request.getYear())
//				.model(request.getModel())
//				.mileage(request.getMileage()).build();
// 
//	}
// 
//	public ResponseEntity<ApiResponse> addCar(int sellerId, CarDto request) {	
//		Optional<Seller> sellerOptional = sellerRepo.findById(sellerId);
//		if(sellerOptional.isPresent())
//		{
//			carRepository.save(mapToCar(request));
//			ApiResponse apiResponse=new ApiResponse();
//			apiResponse.setMessage("car added by seller");
//			apiResponse.setStatus(HttpStatus.OK.value());
//			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);			
//		}
//		else
//		throw  new RuntimeException("seller is not present");
//	}

	
}
