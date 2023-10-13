package com.br.geld.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "INSTALLMENT")
public class Installment {
    @Id
    @Column(name = "ID")
    @GeneratedValue()
    private long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "BILLING_ID")
    private MonthlyBilling monthlyBilling;

    @Column(name = "value")
    private BigDecimal value;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MonthlyBilling getMonthlyBilling() {
        return monthlyBilling;
    }

    public void setMonthlyBilling(MonthlyBilling monthlyBilling) {
        this.monthlyBilling = monthlyBilling;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
