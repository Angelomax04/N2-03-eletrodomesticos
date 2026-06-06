module org.example.n203eletrodomesticos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens eletrodomesticos to javafx.fxml;
    exports eletrodomesticos;
}