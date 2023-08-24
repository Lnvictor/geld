package com.br.geld.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "PAYMENT")
public class Payment {
    @Id
    @Column(name = "ID")
    @GeneratedValue()
    private long id;

    @ManyToOne
    @JoinColumn(name = "BILLING_ID")
    private MonthlyBilling monthlyBilling;

    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT")
    private Timestamp updatedAt;

}
