package de.neuefische.model;

import java.time.ZonedDateTime;
import java.util.List;

public record Order(
		String id,
		List<Product> products,
		OrderStatus orderStatus,
		ZonedDateTime orderDate
) {
}
