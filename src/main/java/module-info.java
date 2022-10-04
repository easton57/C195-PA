module com.eastonseidel.c195pa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.eastonseidel.c195pa to javafx.fxml;
    exports com.eastonseidel.c195pa;
}