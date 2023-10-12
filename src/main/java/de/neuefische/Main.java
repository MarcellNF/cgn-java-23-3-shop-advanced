package de.neuefische;

import de.neuefische.model.Order;
import de.neuefische.model.OrderStatus;
import de.neuefische.repository.OrderRepository;
import de.neuefische.repository.ProductRepository;
import de.neuefische.service.IdService;
import de.neuefische.service.ShopService;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		OrderRepository orderRepository = new OrderRepository();
		ProductRepository productRepository = new ProductRepository();
		IdService idService = new IdService();
		ShopService shopService = new ShopService(productRepository, idService, orderRepository);

		Order order = new Order(null, List.of("1", "2"), OrderStatus.PROCESSING, null);
		Order newOrder = shopService.addOrder(order);
		System.out.println(newOrder);

		Order order2 = new Order(null, List.of("1", "2"), OrderStatus.PROCESSING, null);
		Order newOrder2 = shopService.addOrder(order2);
		System.out.println(newOrder2);

		Order order3 = new Order(null, List.of("1", "2"), OrderStatus.PROCESSING, null);
		Order newOrder3 = shopService.addOrder(order3);
		System.out.println(newOrder3);

		System.out.println(shopService.getOldestOrderPerStatus().values());
	}
}