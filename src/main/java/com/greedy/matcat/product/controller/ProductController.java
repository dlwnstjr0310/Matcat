package com.greedy.matcat.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.matcat.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/list")
	public String productList(@RequestParam(defaultValue="1") int page,
			@RequestParam(required=false) String searchCondition,
			@RequestParam(required=false) String searchValue,
			Model model) {
		
		log.info("[ProductController] page :{}", page);
		
		Map<String, String> searchMap = new HashMap<>();
		
		
	}

	
}
