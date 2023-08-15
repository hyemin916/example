package com.java.example;

import com.java.example.entities.Order;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface OrderDao extends CrudRepository<Order, Long> {

    @Query("""
                select new com.java.example.OrderDto(id)
                from Order
                where userId = :userId
            """)
    Slice<OrderDto> list(final Long userId);

    @Query("""
        select new com.java.example.OrderedProductDto(name, order.id)
        from OrderedProduct
        where order.id in :orderIds
    """)
    List<OrderedProductDto> listOrderedProducts(List<Long> orderIds);
}