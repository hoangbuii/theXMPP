package com.thexmpp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Packet {
    public static Map<String, String> processTime(String timeString) {
        try {
            if (timeString.length() < 14) {
              System.out.println("Error packet: Error time format");
              return null;
            }
            Map<String, String> time = new HashMap<String, String>();
            time.put("hour", timeString.substring(0,2));
            time.put("minute", timeString.substring(2,4));
            time.put("second", timeString.substring(4,6));
            time.put("day", timeString.substring(6,8));
            time.put("month", timeString.substring(8,10));
            time.put("year", timeString.substring(10));
            return time;
        } catch (NullPointerException e) {
            System.out.println("Error packet: Error time");
        }
        return null;
    }

    public static Map<String, String> processPacket(String packet) 
    throws StringIndexOutOfBoundsException {
        try {
            String from = packet.substring(0, packet.indexOf('|'));
            String subpacket = packet.substring(packet.indexOf('|') + 1);
            String to = subpacket.substring(0, subpacket.indexOf('|'));
            subpacket = subpacket.substring(subpacket.indexOf('|') + 1);
            String time = subpacket.substring(0, subpacket.indexOf('|'));
            subpacket = subpacket.substring(subpacket.indexOf('|') + 1);
            String type = subpacket.substring(0, subpacket.indexOf('|'));
            subpacket = subpacket.substring(subpacket.indexOf('|') + 1);
            String temp = subpacket.substring(0, subpacket.indexOf('|'));
            subpacket = subpacket.substring(subpacket.indexOf('|') + 1);
            String humid = subpacket.substring(0, subpacket.indexOf('|'));
            subpacket = subpacket.substring(subpacket.indexOf('|') + 1);
            String atm = subpacket.substring(0, subpacket.indexOf('|'));
            subpacket = subpacket.substring(subpacket.indexOf('|') + 1);
            String control = subpacket.substring(0, subpacket.indexOf('|'));

            Map<String, String> sensorData = new HashMap<String, String>();

            sensorData.put("from", from);
            sensorData.put("to", to);
            sensorData.put("time", time);
            sensorData.put("type", type);
            sensorData.put("temp", temp);
            sensorData.put("humid", humid);
            sensorData.put("atm", atm);
            sensorData.put("control", control);

            return sensorData;
        } catch (NullPointerException e) {
            //e.printStackTrace();
            System.out.println("Error packet: Missing data");
        }
        return null;

    }

    public static String getTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmssddMMyyyy");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
}
