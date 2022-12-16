package com.eastonseidel.c195pa;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Creates customer objects for use in assigning appointments
 */
public class Customer {
    private final int customerId;
    private String name;
    private String address;
    private String modifiedAddress;
    private String postalCode;
    private String phone;
    private int divisionId;
    private final static Dictionary<Integer, String> divisionIdToCountry = new Hashtable<>();
    private final static Dictionary<Integer, String> countryIds = new Hashtable<>();
    private final static Dictionary<Integer, String> divisionIds = new Hashtable<>();
    private final static Dictionary<String, Integer> divisionNames = new Hashtable<>();

    /**
     * Constructor for customer object type
     * @param id auto-generated customer ID
     * @param name customer first and last name
     * @param address customer address
     * @param postalCode address's postal code
     * @param phone customers phone number
     * @param divisionId customers division
     */
    public Customer(int id, String name, String address, String postalCode, String phone, int divisionId) {
        this.customerId = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
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

    /**
     * Retriever for customerId
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Retriever for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name new name for object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retriever for address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for address
     * @param address new address for object
     */
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

    /**
     * Retriever for postalCode
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter for postalCode
     * @param postalCode new postalCode for object
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Retriever for phone
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter for phone
     * @param phone new phone for object
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retriever for id
     * @return id
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Setter for id
     * @param id new id for object
     */
    public void setDivisionId(int id) {
        this.divisionId = id;
    }

    /**
     * Retriever for modified address
     * @return the modified address with the additional text
     */
    public String getModifiedAddress() {
        return modifiedAddress;
    }

    /**
     * Method for getting the division and country id's from the database
     */
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
            String errorString = Translator.ln.get("dbFailed") + exception;

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
