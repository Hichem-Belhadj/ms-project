package com.hbtheme.orderservice.dto;

import com.hbtheme.orderservice.model.OrderLineItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class OrderRequest {
    private List<OrderLineItemDto> orderLineItemDtoList;
}
