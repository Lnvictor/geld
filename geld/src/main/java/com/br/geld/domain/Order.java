package com.br.geld.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ORDER")
public class Order {
    @Id
    @Column(name = "ID")
    @GeneratedValue()
    private long id;

    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "INSTALLMENTS_NUMBER")
    private int installmentsNumber;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT")
    private Timestamp updatedAt;
}
