module thecoolcoders {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires junit;
    requires jlayer;
    requires software.amazon.awssdk.core;
    requires software.amazon.awssdk.services.polly;
    requires software.amazon.awssdk.regions;
    requires software.amazon.awssdk.utils;
    requires org.slf4j;
    requires org.slf4j.simple;
    requires software.amazon.awssdk.awscore;
    requires software.amazon.eventstream;

    opens thecoolcoders to javafx.fxml;
    exports thecoolcoders;
}
