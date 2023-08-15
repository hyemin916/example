package com.java.example;

import com.java.example.entities.Shipping;
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
        final List<Long> orderIds = orderIdsWith(orders);
        final Map<Long, List<OrderedProductDto>> productsByOrderId = getProductsByOrderId(orderIds);
        return pairOrdersWithProducts(orders, productsByOrderId);
    }

    @BatchMapping Map<OrderDto, ShippingDto> shipping(List<OrderDto> orders) {
        final List<Long> orderIds = orderIdsWith(orders);
        final Map<Object, ShippingDto> shippingsByOrderId= orderDao.listShippings(orderIds)
                .stream()
                .collect(Collectors.toUnmodifiableMap(
                        ShippingDto::orderId,
                        Function.identity()
                ));
        return orders.stream()
                .collect(Collectors.toUnmodifiableMap(
                        Function.identity(),
                        order -> shippingsByOrderId.getOrDefault(order.id(), new ShippingDto(null, null))
                ));
    }

    private Map<OrderDto, List<OrderedProductDto>> pairOrdersWithProducts(final List<OrderDto> orders, final Map<Long, List<OrderedProductDto>> productsByOrderId) {
        return orders.stream()
                .collect(Collectors.toUnmodifiableMap(
                        Function.identity(),
                        order -> productsByOrderId.getOrDefault(order.id(), List.of())
                ));
    }

    private Map<Long, List<OrderedProductDto>> getProductsByOrderId(final List<Long> orderIds) {
        final Map<Long, List<OrderedProductDto>> productsByOrderId = orderDao.listOrderedProducts(orderIds)
                .stream()
                .collect(Collectors.groupingBy(OrderedProductDto::orderId));
        return productsByOrderId;
    }

    private List<Long> orderIdsWith(final List<OrderDto> orders) {
        return orders.stream()
                .map(OrderDto::id)
                .toList();
    }
}
