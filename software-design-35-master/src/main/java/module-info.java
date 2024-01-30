module softwaredesign {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens softwaredesign to javafx.fxml;
    exports softwaredesign;
}