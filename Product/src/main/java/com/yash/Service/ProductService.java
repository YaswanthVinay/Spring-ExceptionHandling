package com.yash.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yash.Exception.IdAlreaadyExists;
import com.yash.Exception.ProductNotFoundException;
import com.yash.Model.Product;
import com.yash.Repository.ProductRepository;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ResponseEntity<Product> saveTheProduct(Product product) {
		Optional<Product> optional = productRepository.findById(product.getId());
		if (optional.isPresent()) {

			throw new IdAlreaadyExists("id already exists");
		} else {
			productRepository.save(product);
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}

	}

	public List<Product> retrieveall() {
		return productRepository.findAll();
	}

	public ResponseEntity<Product> retrieveProductById(int id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			return new ResponseEntity<Product>(optional.get(), HttpStatus.ACCEPTED);
			}
		else {
			throw new ProductNotFoundException("data not found!");
		}
	}

	public ResponseEntity<Product> updateProductById(int id, Product product) {

		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Product optionalProdcut = optional.get();
			optionalProdcut.setName(product.getName());
			optionalProdcut.setDescription(product.getDescription());
			optionalProdcut.setCountry(product.getCountry());
			optionalProdcut.setPrice(product.getPrice());
			optionalProdcut.setQauntity(product.getQauntity());
			productRepository.save(optionalProdcut);
			return new ResponseEntity<Product>(optionalProdcut, HttpStatus.OK);

		} else {
			throw new ProductNotFoundException("product not found with id");
		}

	}

	
	public ResponseEntity<Product> deleteProductById(int id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return new ResponseEntity("deleted", HttpStatus.OK);

		} else {
			throw new ProductNotFoundException("product not found with id");
		}
	}
}
