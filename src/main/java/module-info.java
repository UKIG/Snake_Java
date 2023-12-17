/**
 * This module specifies the plug-in dependencies for the module
 * */
module ru.spd.spbcut.tracing_object {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;

    opens ru.spd.spbcut.tracing_object to javafx.fxml;
    exports ru.spd.spbcut.tracing_object;
}