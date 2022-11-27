package com.eastonseidel.c195pa;

import java.util.Dictionary;
import java.util.Hashtable;

public class Translator {
    public static Dictionary<String, String> ln = new Hashtable();

    public static void English() {
        // Login Strings
        // username label
        ln.put("username", "Username:");

        // password label
        ln.put("password", "Password:");

        // location label
        ln.put("location:", "Location:");

        // login button
        ln.put("login", "Login");

        // cancel button
        ln.put("cancel", "Cancel");

        // window title
        ln.put("appTitle", "Scheduler");

        // error strings
        // Db failed
        ln.put("dbFailed", "Please verify the local database is running and accessible!\n");

        // invalid password
        ln.put("invalidPasswordTitle", "Incorrect Password!");
        ln.put("invalidPassword", "Password is incorrect, please check your password and try again.");

        // Invalid Username
        ln.put("invalidUsernameTitle", "Invalid Username!");
        ln.put("invalidUsername", "Username doesn't exists in application, please check your Username and try again.");

        // Home Variables
        // home title
        ln.put("homeTitle", "Home");

        // quit button
        ln.put("quitButton", "Quit");

        // shcedule Button
        ln.put("schedule", "Schedule");

        // reports Button
        ln.put("reports", "Reports");

        // customers button
        ln.put("customers", "Customers");

        // Customers variables
        // new button
        ln.put("new", "New");

        // delete button
        ln.put("delete", "Delete");

        // edit button
        ln.put("edit", "Edit");

        // Customer Column
        ln.put("customerName", "Customer Name");

        // Customer action variables
        // Edit customer title
        ln.put("editCustomer", "Edit Customer");

        // Create Customer Title
        ln.put("createCustomer", "Create Customer");

        // Save
        ln.put("save", "Save");

        // phone number
        ln.put("phoneNumber", "Phone Number");

        // postal code
        ln.put("postalCode", "Postal Code");

        // address
        ln.put("address", "Address");

        // id
        ln.put("customerId", "Customer ID");

        // Appointment Variables
        // View Schedule By:
        ln.put("viewScheduleBy", "View Schedule By:");

        // Appointment Actions
        ln.put("appointmentActions", "Appointment Actions:");

        // Week
        ln.put("week", "Week");

        // Month
        ln.put("month", "Month");

        // Title
        ln.put("title", "Title");

        // Description
        ln.put("description", "Description");

        // Location
        ln.put("location", "Location");

        // Contact ID
        ln.put("contactId", "Contact ID");

        // Type
        ln.put("type", "Type");

        // Start Date and Time
        ln.put("startTime", "Start Date and Time");

        // End Date and Time
        ln.put("endTime", "End Date and Time");

        // User ID
        ln.put("userId", "User ID");

        // Error opening customer
        ln.put("editCustomerTitleError", "Error Opening Customer!");
        ln.put("editCustomerErrorText", "There was an error opening this customer for modification!\n");

        // Error writing change to customer
        ln.put("writeCustomerError", "There was an error writing changes to the database!\n");

        // Customer Deleted Alert Text
        ln.put("deleteCustomerTitleAlert", "Customer Deleted");
        ln.put("deleteCustomerAlertText", "Successfully deleted the following customer: ");

        // Current Month
        ln.put("currentMonth", "Current Month");
    }

    public static void French() {
        // Login Strings
        // username label
        ln.put("username", "Nom d'utilisateur:");

        // password label
        ln.put("password", "Mot de Passe:");

        // location label
        ln.put("location:", "Emplacement:");

        // login button
        ln.put("login", "Connectez-vous");

        // cancel button
        ln.put("cancel", "Annuler");

        // window title
        ln.put("appTitle", "Programmateur");

        // error strings
        // Db failed
        ln.put("dbFailed", "Veuillez vérifier que la base de données locale est en cours d'exécution et accessible!\n");

        // invalid password
        ln.put("invalidPasswordTitle", "Mot de passe incorrect!");
        ln.put("invalidPassword", "Le mot de passe est incorrect, veuillez vérifier votre mot de passe et réessayer.");

        // Invalid Username
        ln.put("invalidUsernameTitle", "Nom d'utilisateur invalide!");
        ln.put("invalidUsername", "Le nom d'utilisateur n'existe pas dans l'application, veuillez vérifier votre nom d'utilisateur et réessayer.");

        // Home Variables
        // home title
        ln.put("homeTitle", "Lanceur");

        // quit button
        ln.put("quitButton", "Quitter");

        // shcedule Button
        ln.put("schedule", "Programme");

        // reports Button
        ln.put("reports", "Rapports");

        // customers button
        ln.put("customers", "Clients");

        // Customers variables
        // new button
        ln.put("new", "Nouveau");

        // delete button
        ln.put("delete", "Effacer");

        // edit button
        ln.put("edit", "Éditer");

        // Customer Column
        ln.put("customerName", "Nom du client");

        // Customer action variables
        // Edit customer title
        ln.put("editCustomer", "Modifier le client");

        // Create Customer Title
        ln.put("createCustomer", "Créer un client");

        // Save
        ln.put("save", "Sauvegarder");

        // phone number
        ln.put("phoneNumber", "Numéro de téléphone");

        // postal code
        ln.put("postalCode", "Code postal");

        // address
        ln.put("address", "Adresse");

        // id
        ln.put("customerId", "N ° de client");

        // Appointment Variables
        // View Schedule By:
        ln.put("viewScheduleBy", "Afficher le calendrier par:");

        // Appointment Actions
        ln.put("appointmentActions", "Actions de rendez-vous:");

        // Week
        ln.put("week", "Semaine");

        // Month
        ln.put("month", "Mois");

        // Title
        ln.put("title", "Titre");

        // Description
        ln.put("description", "Description");

        // Location
        ln.put("location", "Emplacement");

        // Contact ID
        ln.put("contactId", "ID de Contact");

        // Type
        ln.put("type", "Catégorie");

        // Start Date and Time
        ln.put("startTime", "Date et heure de début");

        // End Date and Time
        ln.put("endTime", "Date et heure de fin");

        // User ID
        ln.put("userId", "Identifiant d'utilisateur");

        // Error opening customer
        ln.put("editCustomerTitleError", "Erreur lors de l'ouverture du client!");
        ln.put("editCustomerErrorText", "Une erreur s'est produite lors de l'ouverture de ce client pour modification!\n");

        // Error writing change to customer
        ln.put("writeCustomerError", "Une erreur s'est produite lors de l'écriture des modifications dans la base de données!\n");

        // Customer Deleted Alert Text
        ln.put("deleteCustomerTitleAlert", "Client supprimé");
        ln.put("deleteCustomerAlertText", "Le client suivant a bien été supprimé: ");

        // Current Month
        ln.put("currentMonth", "Mois en Cours");
    }
}
