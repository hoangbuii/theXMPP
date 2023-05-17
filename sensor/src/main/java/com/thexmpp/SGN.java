package com.thexmpp;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SGN {
    static double weightRange = 1.5;
    static double weightRange_Humid = 4.5;
    static double minTemp = 20;
    static double maxTemp = 40;
    static double minHumid = 25;
    static double maxHumid = 80;
    static double minAtm = 990;
    static double maxAtm = 1020;
    static double Temp = Math.random() * 20 + 20;
    static double Humid = Math.random() * 55 + 25;
    static double Atm = Math.random() * 1020 + 990;
    static double sign = Math.random() < 0.5 ? 1 : -1;

    public static String formatData(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String fomattedValue = decimalFormat.format(value);
        return fomattedValue;
    }

    public static String getTemp() {
        double offset = Math.random() * weightRange; //[ 1-1.5]
        Temp += sign * offset;

        if (Temp < minTemp) {
            // Adjust Temp within the range [2-5] if it falls below minTemp
            Temp = minTemp + 2 + Math.random() * 3;
            weightRange = 1.5; // Reset the weight range
        } else if (Temp >= maxTemp) {
            // Adjust Temp within the range [2-5] if it exceeds maxTemp
            Temp = maxTemp - 2 - Math.random() * 3;
            weightRange = 1.5; // Reset the weight range
        } else {
            weightRange *= 0.99; // Reduce the weight range slightly after each iteration
        }
        String tempString = formatData(Temp);
        return tempString;
    }

    public static String getHumid() {
        double offset = Math.random() * weightRange_Humid; // [0-4.5]
        Humid += sign * offset;

        if (Humid < minHumid) {
            // Adjust Humid within the range [5-8] if it falls below minHumid
            Humid = minHumid + 3 + Math.random() * 5;
            weightRange_Humid = 4.5; // Reset the weight range
        } else if (Humid > maxHumid) {
            // Adjust Humid within the range [5-8] if it exceeds maxHumid
            Humid = maxHumid - 3 - Math.random() * 5;
            weightRange_Humid = 4.5; // Reset the weight range
        } else {
            weightRange *= 0.99; // Reduce the weight range slightly after each iteration
        }
        String humidString = formatData(Humid);
        return humidString;
    }

    public static String getAtm() {
        double offset = Math.random() * weightRange; // [1-1.5]
        Atm += sign * offset;
        if (Atm < minAtm) {
            // Adjust Atm within the range [2-5] if it falls below minAtm
            Atm = minTemp + 2 + Math.random() * 3;
            weightRange = 1.5; // Reset the weight range
        } else if (Atm > maxAtm) {
            // Adjust Atm within the range [2-5] if it exceeds maxAtm
            Atm = maxAtm - 2 - Math.random() * 3;
            weightRange = 1.5; // Reset the weight range
        } else {
            weightRange *= 0.99; // Reduce the weight range slightly after each iteration
        }
        String atmString = formatData(Atm);
        return atmString;
    }

    public static String getTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmssddMMyyyy");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
}
