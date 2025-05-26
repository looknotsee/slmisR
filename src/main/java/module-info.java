module finalproject2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens finalproject2 to javafx.fxml;
    exports finalproject2;
}
