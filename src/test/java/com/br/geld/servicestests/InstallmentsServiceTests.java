/**
 * TODO: Implementar testes de virada de ano
 */
package com.br.geld.servicestests;

import com.br.geld.domain.Installment;
import com.br.geld.domain.MonthlyBilling;
import com.br.geld.domain.Order;
import com.br.geld.repositories.InstallmentRepository;
import com.br.geld.services.InstallmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

public class InstallmentsServiceTests {
    private InstallmentRepository installmentRepository;
    private InstallmentService installmentService;
    private Order order;
    private List<MonthlyBilling> billings;
    private List<Installment> installments;

    @BeforeEach
    void setup(){
        this.installmentRepository = mock(InstallmentRepository.class);
        this.installmentService = new InstallmentService(this.installmentRepository);
        this.order = new Order();
        this.billings = new ArrayList<>();
        this.installments = new ArrayList<>();
        this.order.setValue(BigDecimal.valueOf(200.0));
        this.order.setInstallmentsNumber(2);

        MonthlyBilling billing = new MonthlyBilling();
        Installment installment = new Installment();

        billing.setYear(2023);
        billing.setMonth(9);
        billing.setPaid(false);
        billing.setTotalValue(BigDecimal.valueOf(100.0));

        installment.setMonthlyBilling(billing);
        installment.setOrder(this.order);
        installment.setValue(BigDecimal.valueOf(100.0));

        this.billings.add(billing);
        this.installments.add(installment);
    }

    @Test
    void testShouldCreateInstallment(){
        when(this.installmentRepository.saveAll(any(ArrayList.class))).thenReturn(this.installments);

        List<Installment> installmentsReturned = this.installmentService.createInstallmentsForOrder(this.order, this.billings);

        for (Installment installment : installmentsReturned){
            Assertions.assertEquals(installment, this.installments.get(0));
        }
    }
}
