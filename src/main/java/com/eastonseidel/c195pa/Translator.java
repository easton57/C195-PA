package com.eastonseidel.c195pa;

import java.util.Dictionary;
import java.util.Hashtable;

public class Translator {
    public static Dictionary<String, String> ln = new Hashtable();

    /**
     * English settings for the program
     */
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

        // Text for the schedule view
        // edit appointment error
        ln.put("editApptTitleError", "Error Opening Appointment!");
        ln.put("editApptErrorText", "There was an error modifying the selected appointment!\n");

        // write appointment error
        ln.put("writeApptError", "There was an error writing changes to the database!");

        // delete successful
        ln.put("deleteApptTitleAlert", "Appointment Canceled");
        ln.put("deleteApptAlertText", "Successfully canceled the following appointment with id and type: ");

        // The months
        ln.put("January", "January");
        ln.put("February", "February");
        ln.put("March", "March");
        ln.put("April", "April");
        ln.put("May", "May");
        ln.put("June", "June");
        ln.put("July", "July");
        ln.put("August", "August");
        ln.put("September", "September");
        ln.put("October", "October");
        ln.put("November", "November");
        ln.put("December", "December");

        // Appointment Creation/Edit Text
        ln.put("startDateTime", "Start Date/Time");
        ln.put("endDateTime", "End Date/Time");

        ln.put("createAppointment", "Create Appointment");
        ln.put("editAppointment", "Edit Appointment");

        ln.put("id", "ID");

        // Prompt text
        ln.put("countryPromptText", "Select a Country");
        ln.put("divisionPromptText", "Select a Division");

        // Save appointment validation Errors
        // Time format validation
        ln.put("timeFormatHeader", "Time Format Error!");
        ln.put("timeFormatText", "Please format your input times like the following examples: 12:00 pm or 4:00 am");

        // Time conflict
        ln.put("conflictingApptHeader", "Conflicting Appointment!");
        ln.put("conflictingApptText", "Please verify your times aren't overlapping and try again");

        // outside of business hours
        ln.put("businessHoursHeader", "Out of Business Hours!");
        ln.put("buisnessHoursText", "Please verify that your appointment is scheduled between 8am and 10pm EST");

        // 15 minute warning
        ln.put("15MinuteHeader", "15 Minutes!");
        ln.put("15MinuteText", "You have an appointment within the next 15 minutes for:\n");

        // no appointments
        ln.put("NoApptHeader", "No Appointment");
        ln.put("NoApptText", "No upcoming appointments");

        // Reports buttons
        ln.put("rptByType", "Total Appt. By Type");
        ln.put("rptByContact", "Schedule By Contact");
        ln.put("rptByMonth", "Total Appt. Per Month");

        // report text
        ln.put("monthType", "Month and Type");
        ln.put("totalType", "Total By Type");

        ln.put("totalAppt", "Total Appointments");
    }

    /**
     * French settings for the program
     */
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

        // Text for the schedule view
        // edit appointment error
        ln.put("editApptTitleError", "Erreur d'ouverture de rendez-vous!");
        ln.put("editApptErrorText", "Une erreur s'est produite lors de la modification du rendez-vous sélectionné!\n");

        // write appointment error
        ln.put("writeApptError", "Une erreur s'est produite lors de l'écriture des modifications dans la base de données!");

        // delete successful
        ln.put("deleteApptTitleAlert", "Rendez-vous annulé");
        ln.put("deleteApptAlertText", "Le rendez-vous suivant a bien été annulé avec l'identifiant et le type: ");

        // The months
        ln.put("January", "Janvier");
        ln.put("February", "Fevrier");
        ln.put("March", "Mars");
        ln.put("April", "Avril");
        ln.put("May", "Mai");
        ln.put("June", "Juin");
        ln.put("July", "Julliet");
        ln.put("August", "Aout");
        ln.put("September", "Septembre");
        ln.put("October", "Octobre");
        ln.put("November", "Novembre");
        ln.put("December", "Decembre");

        // Appointment Creation/Edit Text
        ln.put("startDateTime", "Date/heure de début");
        ln.put("endDateTime", "Date/heure de fin");

        ln.put("createAppointment", "Créer un rendez-vous");
        ln.put("editAppointment", "Modifier le rendez-vous");

        ln.put("id", "ID");

        // Prompt text
        ln.put("countryPromptText", "Choisissez un pays");
        ln.put("divisionPromptText", "Sélectionnez un département");

        // Time format validation
        ln.put("timeFormatHeader", "Erreur de format d'heure!");
        ln.put("timeFormatText", "Veuillez formater vos heures d'entrée comme les exemples suivants: 12:00 pm or 4:00 am");

        // Time conflict
        ln.put("conflictingApptHeader", "Rendez-vous conflictuel!");
        ln.put("conflictingApptText", "Veuillez vérifier que vos heures ne se chevauchent pas et réessayer");

        // outside of business hours
        ln.put("businessHoursHeader", "En dehors des heures d'ouverture!");
        ln.put("buisnessHoursText", "Veuillez vérifier que votre rendez-vous est prévu entre 8am et 10pm EST");

        // 15 minute warning
        ln.put("15MinuteHeader", "15 Minutes!");
        ln.put("15MinuteText", "Vous avez un rendez-vous dans les 15 prochaines minutes pour:\n");

        // no appointments
        ln.put("NoApptHeader", "Pas de rendez-vous");
        ln.put("NoApptText", "Aucun rendez-vous à venir");

        // Reports buttons
        ln.put("rptByType", "Total rv par type");
        ln.put("rptByContact", "Horaire par contact");
        ln.put("rptByMonth", "Total rv par mois");

        // report text
        ln.put("monthType", "Mois et Type");
        ln.put("totalType", "Total par Type");

        ln.put("totalAppt", "Total Rendez-vous");
    }
}
