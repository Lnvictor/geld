package com.br.geld.servicestests;

import com.br.geld.domain.Order;
import com.br.geld.dto.OrderDTO;
import com.br.geld.repositories.OrderRepository;
import com.br.geld.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

public class OrderServiceTests {
    private OrderRepository orderRepository;
    private OrderService orderService;
    private Order order;

    @BeforeEach
    void setup(){
        this.order = new Order();
        this.order.setValue(BigDecimal.valueOf(200.0));
        this.order.setInstallmentsNumber(3);
        orderRepository = mock(OrderRepository.class);
        this.orderService = new OrderService(this.orderRepository);
    }

    @Test
    void testShouldCreateOrderSuccessfully(){
        when(this.orderRepository.save(any(Order.class))).thenReturn(this.order);
        // create DTO to pass 'createOrder' method
        OrderDTO dto = new OrderDTO(BigDecimal.valueOf(100.0), 2);
        Order orderReturned = this.orderService.createOrder(dto);

        Assert.isTrue(orderReturned.getValue() == this.order.getValue());
        Assert.isTrue(orderReturned.getInstallmentsNumber() == this.order.getInstallmentsNumber());
        Assert.isTrue(orderReturned.getCreatedAt() == this.order.getCreatedAt());
        Assert.isTrue(orderReturned.getUpdatedAt() == this.order.getUpdatedAt());
    }

    @Test
    void testShouldThrowExceptionWhenDTOIsNull(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.orderService.createOrder(null);});

        Assertions.assertTrue(exception.getMessage().equals("DTO invalido para a entidade Order!"));
    }
}
