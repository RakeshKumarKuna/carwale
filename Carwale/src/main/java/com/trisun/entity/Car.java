package com.trisun.entity;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;

import com.trisun.core.FuelType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cars")
 public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String name;
	@NotNull
	private String currentLocation;
	@NotNull
	private Double price;
	@NotNull
	private String model;//
	@NotNull
	private String description;
	@NotNull
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;
	@NotNull
	private Integer carNumber;
	@NotNull
	private Long contact;
	@NotNull
	private Integer pincode;
   
    @ManyToOne(targetEntity = Brand.class)
	@JoinColumn(name = "brandId")
	Brand brand;
    
    @ManyToOne(targetEntity = Category.class)
   	@JoinColumn(name = "categoryId")
   	Category category;   
  
    @OneToOne()
    @JoinColumn(name = "image_id")
    private Image image;
    
    @ManyToOne(targetEntity = Seller.class,fetch   = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller  seller;
     

}
