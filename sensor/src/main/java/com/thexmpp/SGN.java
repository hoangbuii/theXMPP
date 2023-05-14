package com.thexmpp;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SGN {
    static double Temp = Math.random() * 20 + 10;
    static double Humid = Math.random() * 40 + 25;
    static double Atm = Math.random() * 15 + 990;

    public static String formatData(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String fomattedValue = decimalFormat.format(value);
        return fomattedValue;
    }

    public static String getTemp() {
        double sign = Math.random() < 0.5 ? 1 : -1;
        double offset = Math.random() * 1.4 + 1;
        Temp = Temp + (sign * offset);
        String tempString = formatData(Temp);
        return tempString;
    }

    public static String getHumid() {
        double sign = Math.random() < 0.5 ? 1 : -1;
        double offset = Math.random() * 3 + 2;
        Humid = Humid + (sign * offset);
        String humidString = formatData(Humid);
        return humidString;
    }

    public static String getAtm() {
        double sign = Math.random() < 0.5 ? 1 : -1;
        double offset = Math.random() * 3 + 3;
        Atm = Atm + (sign * offset);
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
