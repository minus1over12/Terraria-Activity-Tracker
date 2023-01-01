package io.github.minus1over12.terraria_activity_tracker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * @author War Pigeon
 */
public class TerrariaActivityTracker extends Application
{

    /**
     * Array of activities
     */
    private final Activity[] activities = new Activity[25];

    private final TilePane pane = new TilePane();

    private final String[] shortcuts = {"KS", "EOC", "EOW", "BOC", "SK", "QB", "DEE", "WOF", "QS", "DF", "DES", "T", "SP",
            "PL", "EOL", "GOL", "LC", "ML", "GOB", "PI", "PM", "FM", "OOA", "MM", "FL"};

    @Override
    public void start(Stage stage)
    {
        // Setup Activities array
        for(byte i = 0; i < activities.length; i++) {
            if (i == 21 - 10)
                activities[i] = new Activity(i + 10 + ".gif", shortcuts[i]);
            else
                activities[i] = new Activity(i + 10 + ".png", shortcuts[i]);
        }

        // Add the icons into the pane
        for (Activity activity : activities)
            pane.getChildren().add(activity.view);

        // Setup Tile Pane
        pane.setVgap(2);
        pane.setHgap(1);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, (50+1)*3-1, (50+2)*6-2);
        scene.setFill(Color.PURPLE);
        stage.setTitle("Terraria Activity Tracker");
        stage.getIcons().add(new Image(Objects.requireNonNull(TerrariaActivityTracker.class.getResourceAsStream("Icons/3DS_Boss_Icon.png"))));
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();

        // Setup JNativeHook. Needs to go after stage.show() for threading reasons
        GlobalScreen.setEventDispatcher(new SwingDispatchService());

        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new GlobalKeyListener(activities));

    }

    @Override
    public void stop() {
        System.exit(0);
    }


}
