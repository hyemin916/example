package com.java.example;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GetOrders {
    private final OrderDao orderDao;

    @QueryMapping
    public Slice<OrderDto> getOrders(@Argument final Long userId) {
        return orderDao.list(userId);
    }
}
