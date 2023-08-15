package com.java.example;

import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GetOrders {

    @QueryMapping
    public Slice<OrderDto> getOrders(@Argument final Long userId) {
        return new SliceImpl<>(List.of(new OrderDto(123L)));
    }
}
