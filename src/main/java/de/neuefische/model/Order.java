package de.neuefische.model;

import lombok.With;

import java.time.ZonedDateTime;
import java.util.List;

public record Order(
		String id,
		List<String> productIds,
		@With OrderStatus orderStatus,
		ZonedDateTime orderDate
) {
}
