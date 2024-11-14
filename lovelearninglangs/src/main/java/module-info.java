module thecoolcoders {
    requires javafx.controls;
    requires javafx.fxml;

    opens thecoolcoders to javafx.fxml;
    exports thecoolcoders;
}
