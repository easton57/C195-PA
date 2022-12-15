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

    /**
     * Constructor for the appointment object type
     * @param appointmentId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userId
     * @param contactId
     */
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

    /**
     * retriever for appointmentId
     * @return appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * retriever for title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for title
     * @param title for new appointment property value
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * retriever for description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter for description
     * @param description for new appointment property value
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * retriever for location
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * setter for location
     * @param location for new appointment property value
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * retriever for type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * setter for type
     * @param type for new appointment property value
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * retriever for start
     * @return start
     */
    public String getStart() {
        return start;
    }

    /**
     * setter for start
     * @param start for new appointment property value
     */
    public void setStart(Timestamp start) {
        this.start = convertFromUtc(start, "local");;
    }

    /**
     * retriever for end
     * @return end
     */
    public String getEnd() {
        return end;
    }

    /**
     * setter for end
     * @param end for new appointment property value
     */
    public void setEnd(Timestamp end) {
        this.end = convertFromUtc(end, "local");
    }

    /**
     * retriever for customerId
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * setter for customerId
     * @param customerId for new appointment property value
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * retriever for userId
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * setter for userId
     * @param userId for new appointment property value
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * retriever for contactId
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * setter for contactId
     * @param contactId for new appointment property value
     */
    public void setContactId(int contactId) {
            this.contactId = contactId;
    }

    /**
     * Converts timezone from UTC to a specified timezone
     * @param oldTime to be converted
     * @param timeZone timezone to be converted to
     * @return converted time zone as string
     */
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

    /**
     * Converts timezone from UTC to a specified timezone
     * @param oldTime to be converted
     * @param timeZone timezone to be converted to
     * @return converted time zone as Timestamp
     */
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

    /**
     * converts a timezone from a specified timezone back to UTC for storing in the database
     * @param oldTime to be converted
     * @param timeZone timezone to be converted from
     * @return converted time zone as Timestamp
     */
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
