package com.br.geld.services;

import com.br.geld.domain.Order;
import com.br.geld.dto.OrderDTO;
import com.br.geld.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(OrderDTO dto){
        Order order = new Order(dto.getValue(), dto.getInstallmentsNumber(), dto.getCreatedAt(), dto.getUpdatedAt());
        orderRepository.save(order);
        return order;
    }
}
