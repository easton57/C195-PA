package com.eastonseidel.c195pa;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String modifiedAddress;
    private String postalCode;
    private String phone;
    private String creationDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;
    private static Dictionary<Integer, String> divisionIdToCountry = new Hashtable();
    private static Dictionary<Integer, String> countryIds = new Hashtable();
    private static Dictionary<Integer, String> divisionIds = new Hashtable();
    private static Dictionary<String, Integer> divisionNames = new Hashtable();

    public Customer(int id, String name, String address, String postalCode, String phone, String creationDate, String createdBy, String lastUpdate, String lastUpdatedBy, int divisionId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;

        // set the modified Address up
        getDivisions();
        if (divisionIdToCountry.get(divisionId).contains("U.S")) {
            modifiedAddress = "U.S. address: " + address + " " + divisionIds.get(divisionId);
        }
        else if (divisionIdToCountry.get(divisionId).contains("Canada")) {
            modifiedAddress = "Canada address: " + address + " " + divisionIds.get(divisionId);
        }
        else {
            modifiedAddress = "UK address: " + address + " " + divisionIds.get(divisionId);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;

        getDivisions();
        if (divisionIdToCountry.get(divisionId).contains("U.S")) {
            modifiedAddress = "U.S. address: " + address + " " + divisionIds.get(divisionId);
        }
        else if (divisionIdToCountry.get(divisionId).contains("Canada")) {
            modifiedAddress = "Canada address: " + address + " " + divisionIds.get(divisionId);
        }
        else {
            modifiedAddress = "UK address: " + address + " " + divisionIds.get(divisionId);
        }
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int id) {
        this.divisionId = id;
    }

    public String getModifiedAddress() {
        return modifiedAddress;
    }

    private void getDivisions() {
        // grab the id's and divisions from the db
        Connection localDb;
        try {
            // Grab the country Id's
            Class.forName("com.mysql.cj.jdbc.Driver");
            localDb = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/client_schedule",
                    "sqlUser", "Passw0rd!"
            );

            Statement statement;
            statement = localDb.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "SELECT Country_ID, Country FROM countries"
            );
            while (resultSet.next()) {
                // Add username and password to dictionary
                countryIds.put(resultSet.getInt("Country_ID"), resultSet.getString("Country"));
            }

            // grab the division ID's
            resultSet = statement.executeQuery(
                    "SELECT Division_ID, Division, Country_ID FROM first_level_divisions"
            );
            while (resultSet.next()) {
                // Add username and password to dictionary
                divisionNames.put(resultSet.getString("Division"), resultSet.getInt("Division_ID"));
                divisionIds.put(resultSet.getInt("Division_ID"), resultSet.getString("Division"));
                divisionIdToCountry.put(resultSet.getInt("Division_ID"), countryIds.get(resultSet.getInt("Country_ID")));
            }

            resultSet.close();
            statement.close();
            localDb.close();
        } catch (Exception exception) {
            String errorString = Translator.ln.get("dbFailed").toString() + exception;

            // Log the error
            SchedulerLogger.addToLog(errorString, "severe");

            // Alert that an error occured
            // Create a popup
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Database Error!");
            errorAlert.setContentText(errorString);
            errorAlert.showAndWait();
        }
    }
}
