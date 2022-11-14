# C195-PA
Performance Assessment for Software II at WGU

<H2>To Do:</H2>
~~1.  Create a log-in form with the following capabilities:~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  accepts a user ID and password and provides an appropriate error message~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  determines the user’s location (i.e., ZoneId) and displays it in a label on the log-in form~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  displays the log-in form in English or French based on the user’s computer language setting to translate all the text, labels, buttons, and errors on the form~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  automatically translates error control messages into English or French based on the user’s computer language setting~~


~~&nbsp;&nbsp;&nbsp;Note: Some operating systems require a reboot when changing the language settings.~~

<br>

~~2.  Write code that provides the following customer record functionalities:~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  Customer records and appointments can be added, updated, and deleted.~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-  When deleting a customer record, all of the customer’s appointments must be deleted first, due to foreign key constraints.~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  When adding and updating a customer, text fields are used to collect the following data: customer name, address, postal code, and phone number.~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-  Customer IDs are auto-generated, and first-level division (i.e., states, provinces) and country data are collected using separate combo boxes.~~


~~&nbsp;&nbsp;&nbsp;Note: The address text field should not include first-level division and country data. Please use the following examples to format addresses:~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  U.S. address: 123 ABC Street, White Plains~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  Canadian address: 123 ABC Street, Newmarket~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  UK address: 123 ABC Street, Greenwich, London~~


~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-  When updating a customer, the customer data autopopulates in the form.~~


~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  Country and first-level division data is prepopulated in separate combo boxes or lists in the user interface for the user to choose. The first-level list should be filtered by the user’s selection of a country (e.g., when choosing U.S., filter so it only shows states).~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  All of the original customer information is displayed on the update form.~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-  Customer_ID must be disabled.~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  All of the fields can be updated except for Customer_ID.~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  Customer data is displayed using a TableView, including first-level division data. A list of all the customers and their information may be viewed in a TableView, and updates of the data can be performed in text fields on the form.~~

~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  When a customer record is deleted, a custom message should display in the user interface.~~

<br>

3.  Add scheduling functionalities to the GUI-based application by doing the following:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a.  Write code that enables the user to add, update, and delete appointments. The code should also include the following functionalities:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  A contact name is assigned to an appointment using a drop-down menu or combo box.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  A custom message is displayed in the user interface with the Appointment_ID and type of appointment canceled.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  The Appointment_ID is auto-generated and disabled throughout the application.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  When adding and updating an appointment, record the following data: Appointment_ID, title, description, location, contact, type, start date and time, end date and time, Customer_ID, and User_ID.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  All of the original appointment information is displayed on the update form in local time zone.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  All of the appointment fields can be updated except Appointment_ID, which must be disabled.


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b.  Write code that enables the user to view appointment schedules by month and week using a TableView and allows the user to choose between these two options using tabs or radio buttons for filtering appointments. Please include each of the following requirements as columns:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  Appointment_ID

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  Title

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  Description

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  Location

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  Contact

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  Type

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  Start Date and Time

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  End Date and Time

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  Customer_ID

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  User_ID


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c.  Write code that enables the user to adjust appointment times. While the appointment times should be stored in Coordinated Universal Time (UTC), they should be automatically and consistently updated according to the local time zone set on the user’s computer wherever appointments are displayed in the application.


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note: There are up to three time zones in effect. Coordinated Universal Time (UTC) is used for storing the time in the database, the user’s local time is used for display purposes, and Eastern Standard Time (EST) is used for the company’s office hours. Local time will be checked against EST business hours before they are stored in the database as UTC.


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d.  Write code to implement input validation and logical error checks to prevent each of the following changes when adding or updating information; display a custom message specific for each error check in the user interface:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. EST, including weekends

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  scheduling overlapping appointments for customers

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  entering an incorrect username and password


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;e.  Write code to provide an alert when there is an appointment within 15 minutes of the user’s log-in. A custom message should be displayed in the user interface and include the appointment ID, date, and time. If the user does not have any appointments within 15 minutes of logging in, display a custom message in the user interface indicating there are no upcoming appointments.


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note: Since evaluation may be testing your application outside of business hours, your alerts must be robust enough to trigger an appointment within 15 minutes of the local time set on the user’s computer, which may or may not be EST.


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;f.  Write code that generates accurate information in each of the following reports and will display the reports in the user interface:


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note: You do not need to save and print the reports to a file or provide a screenshot.


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  the total number of customer appointments by type and month

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  a schedule for each contact in your organization that includes appointment ID, title, type and description, start date and time, end date and time, and customer ID

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  an additional report of your choice that is different from the two other required reports in this prompt and from the user log-in date and time stamp that will be tracked in part C


B.  Write at least two different lambda expressions to improve your code.


~~C.  Write code that provides the ability to track user activity by recording all user log-in attempts, dates, and time stamps and whether each attempt was successful in a file named login_activity.txt. Append each new record to the existing file, and save to the root folder of the application.~~


~~D.  Provide descriptive Javadoc comments for at least 70 percent of the classes and their members throughout the code, and create an index.html file of your comments to include with your submission based on Oracle’s guidelines for the Javadoc tool best practices. Your comments should include a justification for each lambda expression in the method where it is used.~~


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note: The comments on the lambda need to be located in the comments describing the method where it is located for it to export properly.


E.  Create a README.txt file that includes the following information:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  title and purpose of the application

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  author, contact information, student application version, and date

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  IDE including version number (e.g., IntelliJ Community 2020.01), full JDK of version used (e.g., Java SE 17.0.1), and JavaFX version compatible with JDK version (e.g. JavaFX-SDK-17.0.1)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  directions for how to run the program

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  a description of the additional report of your choice you ran in part A3f

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•  the MySQL Connector driver version number, including the update number (e.g., mysql-connector-java-8.1.23)


~~F.  Demonstrate professional communication in the content and presentation of your submission.~~