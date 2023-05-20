package com.thexmpp;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;

public class SensorStatus {
    private class Sensor {
        private String name;
        private String status;
        private String inTime;

        public Sensor() {
            this.name = "";
            this.status = "";
            this.inTime = "";
        }

        public Sensor(String name, String status, String inTime) {
            this.name = name;
            this.status = status;
            this.inTime = inTime;
        }
        public String getName() {
            return this.name;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public void setInTime(String inTime) {
            this.inTime = inTime;
        }

        @Override
        public String toString() {
            return this.name + " " + this.status + " " + inTime;
        }
    }

    private ArrayList<Sensor> totalSensor = new ArrayList<>();


    public SensorStatus() {
    }

    public void exportToLog() {
        try {
            Formatter fo = new Formatter("logs/status.txt");
            for (int i = 0; i < totalSensor.size(); i++) {
                fo.format("%s\r", totalSensor.get(i));
            }
            fo.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void changeSensorStatus(String name,String status) {
        int i = 0;
        boolean contain = false;
        for(; i < totalSensor.size(); i++) {
            if (totalSensor.get(i).getName().equals(name)) {
                contain = true;
                break;
            }
        }
        //System.out.println(i);
        if (!(contain) || totalSensor.size() == 0) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[HH:mm:ss]");
            String formattedDateTime = currentDateTime.format(formatter);
            Sensor s = new Sensor(name, status, formattedDateTime);
            totalSensor.add(s);
            exportToLog();
            return;
        }
        if (!(totalSensor.get(i).getStatus().equals(status))) {
            totalSensor.get(i).setStatus(status);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[HH:mm:ss]");
            String formattedDateTime = currentDateTime.format(formatter);
            totalSensor.get(i).setInTime(formattedDateTime); 
            exportToLog();
        }
    }
    
}
