package com.br.geld.services;

import com.br.geld.domain.MonthlyBilling;
import com.br.geld.dto.OrderDTO;
import com.br.geld.repositories.BillingRepository;
import com.br.geld.util.TimestampHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillingService {
    @Autowired
    private BillingRepository billingRepository;

    public BillingService(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    public List<MonthlyBilling> updateMonthlyBilling(OrderDTO orderDTO){
        List<MonthlyBilling> responseList = new ArrayList<>();

        int creationDay = orderDTO.getCreatedAt().getDay();
        int creationMonth = orderDTO.getCreatedAt().getMonth();
        int creationYear = orderDTO.getCreatedAt().getYear() + 1900;
        Double value = orderDTO.getValue().doubleValue() / orderDTO.getInstallmentsNumber();

        int currentMonth = creationMonth;
        int currentYear = creationYear;

        // getting final date
        List<Integer> finalInstallment = TimestampHelper.getFinalMonthAndYear(creationMonth, creationYear,
                orderDTO.getInstallmentsNumber());

        int finalMonth = finalInstallment.get(0);
        int finalYear = finalInstallment.get(1);

        if (creationDay >= 2){
            List<Integer> nextMonth = TimestampHelper.getNextMonth(currentMonth, creationYear);
            currentMonth = nextMonth.get(0);
            currentYear = nextMonth.get(1);
        }

        while(currentMonth != finalMonth || currentYear != finalYear){
            MonthlyBilling billing = this.getBillingForMonth(currentMonth, currentYear);

            if (billing != null){
                BigDecimal newValue = BigDecimal.valueOf(billing.getTotalValue().doubleValue() + value);
                billing.setTotalValue(newValue);
                responseList.add(this.billingRepository.save(billing));
            }
            else{
                responseList.add(this.createBilling(currentMonth, currentYear, BigDecimal.valueOf(value)));
            }

            List<Integer> nextMonth = TimestampHelper.getNextMonth(currentMonth, currentYear);
            currentMonth = nextMonth.get(0);
            currentYear = nextMonth.get(1);
        }
        return responseList;
    }

    private MonthlyBilling createBilling(int month, int year, BigDecimal initialValue){
        Timestamp currentTimestamp = Timestamp.from(Instant.now());

        MonthlyBilling billing = new MonthlyBilling();
        billing.setTotalValue(initialValue);
        billing.setPaid(false);
        billing.setYear(year);
        billing.setMonth(month);
        billing.setCreatedAt(currentTimestamp);
        billing.setUpdatedAt(currentTimestamp);
        billing.setExpiration(Timestamp.valueOf(this.formatTimestampStr(year, month, 11, 0, 0, 0)));
        this.billingRepository.save(billing);
        return billing;
    }

    private MonthlyBilling getBillingForMonth(int month, int year){
        return this.billingRepository.findByMonthAndYear(Integer.toString(month), Integer.toString(year));
    }

    private String formatTimestampStr(int year, int month, int day, int hour, int min, int sec){
        return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
    }
}
