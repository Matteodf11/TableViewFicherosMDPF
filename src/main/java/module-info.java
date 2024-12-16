module com.mycompany.tableviewmdpf {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.tableviewmdpf to javafx.fxml;
    exports com.mycompany.tableviewmdpf;
}
