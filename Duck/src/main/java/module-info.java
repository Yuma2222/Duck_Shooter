module com.example.duck {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires lombok;

    opens com.example.duck to javafx.fxml;
    exports com.example.duck;
    exports com.example.duck.Model;
    opens com.example.duck.Model to javafx.fxml;
}