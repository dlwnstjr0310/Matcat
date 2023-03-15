package com.greedy.matcat.product.service;

import org.springframework.stereotype.Service;

import com.greedy.matcat.product.dao.ProductMapper;

@Service
public class ProductService {

	private final ProductMapper productMapper;
	
	public ProductService(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}
	
}
