package com.br.geld.controllers;

import com.br.geld.domain.Installment;
import com.br.geld.domain.MonthlyBilling;
import com.br.geld.domain.Order;
import com.br.geld.dto.OrderDTO;
import com.br.geld.services.BillingService;
import com.br.geld.services.InstallmentService;
import com.br.geld.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private InstallmentService installmentService;

    public OrderController(OrderService orderService, BillingService billingService, InstallmentService installmentService) {
        this.orderService = orderService;
        this.billingService = billingService;
        this.installmentService = installmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private OrderDTO createOrder(@Valid @RequestBody OrderDTO orderDTO){
        Order order = this.orderService.createOrder(orderDTO);
        List<MonthlyBilling> billings = this.billingService.updateMonthlyBilling(orderDTO);
        List<Installment> installments = this.installmentService.createInstallmentsForOrder(order, billings);
        return orderDTO;
    }
}
