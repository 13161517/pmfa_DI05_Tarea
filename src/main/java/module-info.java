module di05_tarea {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens di05_tarea to javafx.fxml;
    exports di05_tarea;
}