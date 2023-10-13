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
        try{
            Order order = new Order(dto.getValue(), dto.getInstallmentsNumber(), dto.getCreatedAt(), dto.getUpdatedAt());
            return orderRepository.save(order);
        }
        catch (NullPointerException exception){
            String message = "DTO invalido para a entidade Order!";
            System.out.println(message);
            throw new IllegalArgumentException(message);
        }
    }
}
