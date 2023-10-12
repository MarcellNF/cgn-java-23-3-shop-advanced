package de.neuefische.repository;

import de.neuefische.exception.OrderNotFoundException;
import de.neuefische.model.Order;
import de.neuefische.model.OrderStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderRepository {
	private final Map<String, Order> orderMap;

	public List<Order> getAllOrders() {
		return List.copyOf(orderMap.values());
	}

	public Optional<Order> getOrderById(String id) {
		return Optional.ofNullable(orderMap.get(id));
	}

	public Order saveOrder(Order order) {
		orderMap.put(order.id(), order);
		return order;
	}

	public Order updateOrderStatus(String id, OrderStatus orderStatus) {
		Order order = getOrderById(id).orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found"));
		orderMap.put(id, new Order(order.id(), order.productIds(), orderStatus, order.orderDate()));
		return order;
	}

	public void removeOrderById(String id) {
		orderMap.remove(id);
	}
}
