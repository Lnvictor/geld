package com.br.geld.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

public class OrderDTO {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    private long id;
    private BigDecimal value;

    private int installmentsNumber;

    private Timestamp createdAt;

    private Timestamp updatedAt;


    public OrderDTO(BigDecimal value, int installmentsNumber) {
        this.value = value;
        this.installmentsNumber = installmentsNumber;
        this.createdAt = Timestamp.from(Instant.now());
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public BigDecimal getValue() {
        return value;
    }

    public int getInstallmentsNumber() {
        return installmentsNumber;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
