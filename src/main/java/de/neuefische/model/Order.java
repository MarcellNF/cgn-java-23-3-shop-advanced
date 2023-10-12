package de.neuefische.model;

import java.time.ZonedDateTime;
import java.util.List;

public record Order(
		String id,
		List<String> productIds,
		OrderStatus orderStatus,
		ZonedDateTime orderDate
) {
}
