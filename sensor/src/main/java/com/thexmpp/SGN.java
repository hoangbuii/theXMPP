package com.thexmpp;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.jivesoftware.smackx.amp.AMPDeliverCondition.Value;
public class SGN {
    private static final double MIN_TEMP = 10.0;
    private static final double MAX_TEMP = 40.0;
    private static final double MIN_HUMID = 25.0;
    private static final double MAX_HUMID = 100.0;
    private static final double MIN_ATM = 990.0;
    private static final double MAX_ATM = 1020.0;
    
    private static Random random = new Random();

    public static String formatData( double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String fomattedValue = decimalFormat.format(value);
        return fomattedValue;
    }

    public static String getTemp() {
        double Temp = MIN_TEMP + (MAX_TEMP - MIN_TEMP) * random.nextDouble();
        String tempString = formatData(Temp);
        return tempString;
    }

    public static String getHumid() {
        double Humid = MIN_HUMID + (MAX_HUMID - MIN_HUMID) * random.nextDouble();
        String humidString = formatData(Humid);
         return humidString;
    }

    public static String getAtm() {
        double getAtm =  MIN_ATM + (MAX_ATM - MIN_ATM) * random.nextDouble();
        String atmString = formatData(getAtm);
        return atmString;
    }
    
    public static String getTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmssddMMyyyy");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
    public static void main(String[] args) {
        System.out.println(getAtm());
    }
}

