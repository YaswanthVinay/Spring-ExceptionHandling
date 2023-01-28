package com.yash.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.Model.Product;
import com.yash.Service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		return productService.saveTheProduct(product);
	}
	@GetMapping("/get")
	public List<Product> retrieveProduct() {
	 return productService.retrieveall();
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Product> retrieveProductById(@PathVariable int id) {
	 return productService.retrieveProductById(id);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id,@RequestBody Product product){

		return productService.updateProductById(id,product);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int id){

		return productService.deleteProductById(id);
	}
}
