package com.br.geld.repositories;

import com.br.geld.domain.MonthlyBilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<MonthlyBilling, Long> {
    @Query(value = "SELECT * FROM MONTHLY_BILLING WHERE CREATED_AT BETWEEN :initial AND :final", nativeQuery = true)
    List<MonthlyBilling> findBillingBetweenTwoDates(@Param("initial") Timestamp initialDate, @Param("final") Timestamp finalDate);

    @Query(value = "SELECT TOP(1) * FROM [MONTHLY_BILLING] WHERE MONTH = ?1 AND YEAR = ?2", nativeQuery = true)
    MonthlyBilling findByMonthAndYear(String month, String year);
}
