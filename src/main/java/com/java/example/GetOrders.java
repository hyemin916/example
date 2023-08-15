package com.java.example;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class GetOrders {
    private final OrderDao orderDao;

    @QueryMapping
    public Slice<OrderDto> getOrders(@Argument final Long userId) {
        return orderDao.list(userId);
    }

    @BatchMapping
    public Map<OrderDto, List<OrderedProductDto>> products(List<OrderDto> orders) {
        final List<Long> orderIds = orders.stream()
                .map(OrderDto::id)
                .toList();
        final List<OrderedProductDto> products = orderDao.listOrderedProducts(orderIds);
        final Map<Long, List<OrderedProductDto>> productsByOrderId = products.stream().collect(Collectors.groupingBy(OrderedProductDto::orderId));
        return orders.stream()
                .collect(Collectors.toUnmodifiableMap(
                        Function.identity(),
                        order -> productsByOrderId.getOrDefault(order.id(), List.of())
                ));
    }
}
