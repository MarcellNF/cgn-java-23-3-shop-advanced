package de.neuefische.service;

import de.neuefische.exception.ProductNotFoundException;
import de.neuefische.model.Order;
import de.neuefische.model.OrderStatus;
import de.neuefische.model.Product;
import de.neuefische.repository.OrderRepository;
import de.neuefische.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ShopService {
	private final ProductRepository productRepository;
	private final IdService idService;
	private final OrderRepository orderRepository;

	public List<Order> listOrders() {
		return orderRepository.getAllOrders();
	}

	public Order addOrder(Order order) {
		for (Product product : order.products()) {
			if (!productRepository.getProductById(product.id()).isPresent()) {
				throw new ProductNotFoundException("Product with id " + product.id() + " not found");
			}
		}

		Order newOrder = new Order(idService.generateId(), order.products(), OrderStatus.PROCESSING, ZonedDateTime.now());
		return orderRepository.saveOrder(newOrder);
	}

	public Order updateOrderStatus(String id, OrderStatus orderStatus) {
		return orderRepository.updateOrderStatus(id, orderStatus);
	}

	public void removeOrderById(String id) {
		orderRepository.removeOrderById(id);
	}
}
