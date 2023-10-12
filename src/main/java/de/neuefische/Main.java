package de.neuefische;

import de.neuefische.model.Order;
import de.neuefische.model.OrderStatus;
import de.neuefische.repository.OrderRepository;
import de.neuefische.repository.ProductRepository;
import de.neuefische.service.IdService;
import de.neuefische.service.ShopService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		OrderRepository orderRepository = new OrderRepository();
		ProductRepository productRepository = new ProductRepository();
		IdService idService = new IdService();
		ShopService shopService = new ShopService(productRepository, idService, orderRepository);

		/*Order order = new Order(null, List.of("1", "2"), OrderStatus.PROCESSING, null);
		Order newOrder = shopService.addOrder(order);
		System.out.println(newOrder);

		Order order2 = new Order(null, List.of("1", "2"), OrderStatus.PROCESSING, null);
		Order newOrder2 = shopService.addOrder(order2);
		System.out.println(newOrder2);

		Order order3 = new Order(null, List.of("1", "2"), OrderStatus.PROCESSING, null);
		Order newOrder3 = shopService.addOrder(order3);
		System.out.println(newOrder3);

		System.out.println(shopService.getOldestOrderPerStatus().values());*/
		try {
			Files.lines(Path.of("transactions.txt"))
					.map(line -> line.split(" "))
					.forEach(split -> {
						String method = split[0];
						if (method.equals("addOrder")) {
							List<String> productIds = new ArrayList<>();
							for (int i = 2; i < split.length; i++) {
								productIds.add(split[i]);
							}
							shopService.addOrder(new Order(null, productIds, OrderStatus.PROCESSING, null));
						} else if (method.equals("setStatus")) {
							String id = split[1];
							OrderStatus orderStatus = OrderStatus.valueOf(split[2]);
							shopService.updateOrderStatus(id, orderStatus);
						} else if (method.equals("printOrders")) {
							System.out.println(shopService.getAllOrders());
						}
					});
		} catch (Exception e) {
			System.out.println("Error while reading file: " + e.getMessage());
		}
	}
}