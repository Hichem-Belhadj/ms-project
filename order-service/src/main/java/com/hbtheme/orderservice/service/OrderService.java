package com.hbtheme.orderservice.service;

import com.hbtheme.orderservice.dto.OrderLineItemDto;
import com.hbtheme.orderservice.dto.OrderRequest;
import com.hbtheme.orderservice.model.Order;
import com.hbtheme.orderservice.model.OrderLineItem;
import com.hbtheme.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor @Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest request) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItemList = request.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToOrderLineItem).toList();
        order.setOrderLineItems(orderLineItemList);
        orderRepository.save(order);
    }

    private OrderLineItem mapToOrderLineItem(OrderLineItemDto orderLineItemDto) {
      return OrderLineItem.builder()
              .price(orderLineItemDto.getPrice())
              .quantity(orderLineItemDto.getQuantity())
              .skuCode(orderLineItemDto.getSkuCode())
              .build();
    }
}
