package de.neuefische.repository;

import de.neuefische.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductRepository {
	private final Map<String, Product> productMap;

	public ProductRepository(){
		productMap = new HashMap<>();
		productMap.put("1", new Product("1", "Apple"));
		productMap.put("2", new Product("2", "Banana"));
		productMap.put("3", new Product("3", "Orange"));
		productMap.put("4", new Product("4", "Pineapple"));
		productMap.put("5", new Product("5", "Kiwi"));
		productMap.put("6", new Product("6", "Mango"));
		productMap.put("7", new Product("7", "Peach"));
		productMap.put("8", new Product("8", "Pear"));
		productMap.put("9", new Product("9", "Strawberry"));
		productMap.put("10", new Product("10", "Raspberry"));
	}

	public List<Product> getAllProducts() {
		return List.copyOf(productMap.values());
	}

	public Optional<Product> getProductById(String id) {
		return Optional.ofNullable(productMap.get(id));
	}

	public Product saveProduct(Product product) {
		productMap.put(product.id(), product);
		return product;
	}

	public void removeProductById(String id) {
		productMap.remove(id);
	}
}
