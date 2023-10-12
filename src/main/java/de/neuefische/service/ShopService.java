package de.neuefische.service;

import de.neuefische.exception.ProductNotFoundException;
import de.neuefische.model.Order;
import de.neuefische.model.OrderStatus;
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

	public List<Order> getAllOrders() {
		return orderRepository.getAllOrders();
	}

	public Order addOrder(Order order) {
		for (String id : order.productIds()) {
			if (!productRepository.getProductById(id).isPresent()) {
				throw new ProductNotFoundException("Product with id " + id + " not found");
			}
		}
		Order newOrder = new Order(idService.generateId(), order.productIds(), OrderStatus.PROCESSING, ZonedDateTime.now());
		return orderRepository.saveOrder(newOrder);
	}

	public Order updateOrderStatus(String id, OrderStatus orderStatus) {
		return orderRepository.updateOrderStatus(id, orderStatus);
	}

	public void removeOrderById(String id) {
		orderRepository.removeOrderById(id);
	}
}
