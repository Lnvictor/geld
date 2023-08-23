package com.br.geld.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "MONTHLY_BILLING")
public class MonthlyBilling {
    @Id
    @Column(name = "ID")
    @GeneratedValue()
    private long id;

    @Column(name = "TOTAL_VALUE")
    private BigDecimal totalValue;

    @Column(name = "EXPIRATION")
    private Timestamp expiration;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT")
    private Timestamp updatedAt;

    @Column(name = "PAID")
    private Boolean paid;
}
