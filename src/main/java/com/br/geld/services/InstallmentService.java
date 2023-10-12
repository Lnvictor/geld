package com.br.geld.services;

import com.br.geld.domain.Installment;
import com.br.geld.domain.MonthlyBilling;
import com.br.geld.domain.Order;
import com.br.geld.repositories.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstallmentService {

    @Autowired
    private InstallmentRepository installmentRepository;

    public InstallmentService(InstallmentRepository installmentRepository) {
        this.installmentRepository = installmentRepository;
    }

    public List<Installment> createInstallmentsForOrder(Order order, List<MonthlyBilling> billings){
        double installmentValue = order.getValue().doubleValue() / order.getInstallmentsNumber();
        List<Installment> orderInstallments = new ArrayList<>();

        for (MonthlyBilling billing : billings){
            Installment installment = new Installment();
            installment.setValue(BigDecimal.valueOf(installmentValue));
            installment.setOrder(order);
            installment.setMonthlyBilling(billing);
            orderInstallments.add(installment);
        }

        return this.installmentRepository.saveAll(orderInstallments);
    }
}
