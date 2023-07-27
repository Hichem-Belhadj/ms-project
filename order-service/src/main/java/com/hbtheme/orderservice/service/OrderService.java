package com.hbtheme.orderservice.service;

import com.hbtheme.coreapi.dto.InventoryResponse;
import com.hbtheme.orderservice.dto.OrderLineItemDto;
import com.hbtheme.orderservice.dto.OrderRequest;
import com.hbtheme.orderservice.model.Order;
import com.hbtheme.orderservice.model.OrderLineItem;
import com.hbtheme.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service @RequiredArgsConstructor @Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest request) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItemList = request.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToOrderLineItem).toList();
        order.setOrderLineItems(orderLineItemList);

        List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItem::getSkuCode).toList();

        // Call Inventory-Service and place order if product is in stock.
        InventoryResponse[] inventoryResponses = Objects.requireNonNull(webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()
                )
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block());

        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInnStock);

        if (allProductsInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later!");
        }
    }

    private OrderLineItem mapToOrderLineItem(OrderLineItemDto orderLineItemDto) {
      return OrderLineItem.builder()
              .price(orderLineItemDto.getPrice())
              .quantity(orderLineItemDto.getQuantity())
              .skuCode(orderLineItemDto.getSkuCode())
              .build();
    }
}
