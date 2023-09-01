package com.br.geld.controllers;

import com.azure.core.annotation.Post;
import com.br.geld.dto.OrderDTO;
import com.br.geld.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private OrderDTO createOrder(@Valid @RequestBody OrderDTO orderDTO){
        orderService.createOrder(orderDTO);
        return orderDTO;
    }
}
