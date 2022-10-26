module com.eastonseidel.c195pa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires org.apache.logging.log4j;


    opens com.eastonseidel.c195pa to javafx.fxml;
    exports com.eastonseidel.c195pa;
}