module com.example.zeldasae {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires org.json;

    opens com.example.zeldasae to javafx.fxml;
    exports com.example.zeldasae;
    exports com.example.zeldasae.controller;
    opens com.example.zeldasae.controller to javafx.fxml;
    exports com.example.zeldasae.modele;
    opens com.example.zeldasae.modele to javafx.fxml;
}