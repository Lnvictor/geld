package com.br.geld.servicestests;

import com.br.geld.domain.MonthlyBilling;
import com.br.geld.domain.Order;
import com.br.geld.dto.OrderDTO;
import com.br.geld.repositories.BillingRepository;
import com.br.geld.services.BillingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BillingTests {

    private Order order;
    private List<MonthlyBilling> existentBillings;
    private BillingService billingService;
    private BillingRepository billingRepository;

    @BeforeEach
    void setup(){
        this.order = new Order();
        this.order.setValue(BigDecimal.valueOf(200.0));
        this.order.setInstallmentsNumber(3);
        this.billingRepository = mock(BillingRepository.class);
        this.billingService = new BillingService(this.billingRepository);
    }

    @Test
    void testUpdateMonthlyBillingWithoutAnyBillingsCreated(){
        List<MonthlyBilling> emptyList = new ArrayList<>();
        when(this.billingRepository.findBillingBetweenTwoDates(any(Timestamp.class), any(Timestamp.class))).
             thenReturn(emptyList);
        when(this.billingRepository.saveAll(any(Iterable.class))).thenReturn(this.existentBillings);
        OrderDTO dto = new OrderDTO(BigDecimal.valueOf(100.0), 2);
        List<MonthlyBilling> billings = this.billingService.updateMonthlyBilling(dto);

        verify(this.billingRepository, times(2)).save(any(MonthlyBilling.class));

        Assert.isTrue(billings.size() == 2, "erro");
        Assert.isTrue(billings.get(0).getTotalValue().doubleValue() == 50.0, "ERRO");
    }
}
