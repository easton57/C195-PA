package com.eastonseidel.c195pa;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private int customerId;
    private int userId;
    private int contactId;

    public Appointment(int appointmentId, String title, String description, String location, String type, Timestamp start, Timestamp end, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = convertFromUtc(start, "local");
        this.end = convertFromUtc(end, "local");
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = convertFromUtc(start, "local");;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = convertFromUtc(end, "local");
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
            this.contactId = contactId;
    }

    public static String convertFromUtc(Timestamp oldTime, String timeZone) {
        DateTimeFormatter outgoingFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        ZoneId zoneId;

        // Get the zone ID from the computer
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime utcTime = oldTime.toLocalDateTime().atZone(utcZoneId);

        // Check to see which timezone was forwarded in
        if (timeZone.equals("EST")) {
            // Get the zone ID from the computer
            zoneId = ZoneId.of("America/New_York");
        }
        else {
            // Get the zone ID from the computer
            zoneId = ZoneId.of(ZoneId.systemDefault().getId());
        }

        ZonedDateTime tempTime = utcTime.withZoneSameInstant(zoneId);

        return outgoingFormat.format(tempTime);
    }

    public static Timestamp convertFromUtcTimeStamp(Timestamp oldTime, String timeZone) {
        DateTimeFormatter outgoingFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId zoneId;

        // Get the zone ID from the computer
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime utcTime = oldTime.toLocalDateTime().atZone(utcZoneId);

        // Check to see which timezone was forwarded in
        if (timeZone.equals("EST")) {
            // Get the zone ID from the computer
            zoneId = ZoneId.of("America/New_York");
        }
        else {
            // Get the zone ID from the computer
            zoneId = ZoneId.of(ZoneId.systemDefault().getId());
        }

        ZonedDateTime tempTime = utcTime.withZoneSameInstant(zoneId);
        String outgoingDate = outgoingFormat.format(tempTime);

        return Timestamp.valueOf(outgoingDate);
    }

    public static Timestamp convertToUtc(String oldTime, String timeZone) {
        DateTimeFormatter incomingFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        DateTimeFormatter outgoingFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime tempTime;
        ZoneId zoneId;

        if (oldTime.length() == 19) {
            oldTime = oldTime.split(" ")[0] + " " + oldTime.split(" ")[1] + ":00 " + oldTime.split(" ")[2];
        }
        else if (oldTime.length() == 21) {
            oldTime = oldTime.split(" ")[0] + " 0" + oldTime.split(" ")[1] + " " + oldTime.split(" ")[2];
        }
        else if (oldTime.length() == 18) {
            oldTime = oldTime.split(" ")[0] + " 0" + oldTime.split(" ")[1] + ":00 " + oldTime.split(" ")[2];
        }

        // Check to see which timezone was forwarded in
        if (timeZone.equals("EST")) {
            // Get the zone ID from the computer
            zoneId = ZoneId.of("America/New_York");
        }
        else {
            // Get the zone ID from the computer
            zoneId = ZoneId.of(ZoneId.systemDefault().getId());
        }

        // Get the zone ID from the computer
        tempTime = LocalDateTime.parse(oldTime.toUpperCase(), incomingFormat).atZone(zoneId);
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime utcZone = tempTime.withZoneSameInstant(utcZoneId);

        return Timestamp.valueOf(outgoingFormat.format(utcZone));
    }
}
