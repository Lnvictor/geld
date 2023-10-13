package com.br.geld.util;

import java.util.ArrayList;
import java.util.List;

public class TimestampHelper {

    private static final int NUMBER_OF_MONTHS_BY_YEAR = 12;

    public static List<Integer> getNextMonth(int month, int year){
        if (month == 12){
            year++;
            month = 1;
        }
        else{
            month++;
        }
        List<Integer> response = new ArrayList<Integer>(){};
        response.add(month);
        response.add(year);
        return response;
    }

    /**
     *
     * It returns the month and year of last installment
     *
     * @param creationMonth
     * @param creationYear
     * @param numberOfInstallments
     *
     * @return List<Integer>
     */
    public static List<Integer> getFinalMonthAndYear(int creationMonth, int creationYear,
                                                      int numberOfInstallments){
        int month;
        int year;
        List<Integer> responseList = new ArrayList<>();

        if (creationMonth + numberOfInstallments > NUMBER_OF_MONTHS_BY_YEAR){
            month = creationMonth + numberOfInstallments - NUMBER_OF_MONTHS_BY_YEAR;
            year = creationYear + 1;
        }
        else{
            month = creationMonth + numberOfInstallments;
            year = creationYear;
        }

        return getNextMonth(month, year);
    }
}
