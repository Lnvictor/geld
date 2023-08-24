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

    @Column(name = "MONTH")
    private int month;

    @Column(name = "value")
    private BigDecimal value;
}
