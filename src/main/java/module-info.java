module io.github.minus1over12.terraria_activity_tracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.github.kwhat.jnativehook;


    opens io.github.minus1over12.terraria_activity_tracker to javafx.fxml;
    exports io.github.minus1over12.terraria_activity_tracker;
}
