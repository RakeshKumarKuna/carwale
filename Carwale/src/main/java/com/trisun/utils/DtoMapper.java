package com.trisun.utils;

import com.trisun.core.FuelType;
import com.trisun.entity.Admin;
import com.trisun.entity.Brand;
import com.trisun.entity.Buyer;
import com.trisun.entity.Car;
import com.trisun.entity.Category;
import com.trisun.entity.Image;
import com.trisun.entity.Seller;
import com.trisun.entity.User;
import com.trisun.entity.dto.AdminDto;
import com.trisun.entity.dto.BuyerDto;
import com.trisun.entity.dto.CarDto;
import com.trisun.entity.dto.SellerDto;

public class DtoMapper {

	
	
	public static BuyerDto buyerToBuyerDto(Buyer buyer,User user) {
		BuyerDto buyerDto=new BuyerDto();
		buyerDto.setEmail(user.getEmailId());
		buyerDto.setFullName(buyer.getFullName());
		buyerDto.setId(buyer.getId());
		return buyerDto;
	}
	
	public static SellerDto sellerToSellerDto(Seller seller,User user) {
         SellerDto sellerDto=new SellerDto();        
         sellerDto.setEmail(user.getEmailId());
         sellerDto.setFullName(seller.getFullName());
         sellerDto.setId(seller.getId());
 		 return sellerDto;         
	}
	
	public static AdminDto adminToAdminDto(Admin admin,User user) {
		AdminDto adminDto=new AdminDto();      
		adminDto.setEmail(user.getEmailId());
		adminDto.setFullName(admin.getFullName());
		adminDto.setId(admin.getId());
		return adminDto;               
	}
	
	public static CarDto carToCarDto(Car car,Brand brand,Category category,Image image) {
		CarDto carDto=new CarDto();
		carDto.setName(car.getName());
		carDto.setCarNumber(car.getCarNumber());
		carDto.setBrand(brand.getName());
		carDto.setCategory(category.getName());
		carDto.setContact(car.getContact());
		carDto.setDescription(car.getDescription());
		//carDto.setFuelType(car.getFuelType().getDisplayName());
		carDto.setFuelType(car.getFuelType().toString());
		carDto.setPrice(car.getPrice());
		carDto.setPincode(car.getPincode());
		carDto.setModel(car.getModel());
		carDto.setCurrentLocation(car.getCurrentLocation());
		carDto.setSellerId(car.getSeller());
		carDto.setImage(image.getUrl());
		return carDto;
	}
	
}