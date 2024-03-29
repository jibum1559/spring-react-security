package com.kh.springchap1.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="products_seq")
	@SequenceGenerator(name="products_seq", sequenceName="products_seq", allocationSize=1)
	private Long id;
	private String name;
	private double price;
}