package io.github.minus1over12.terraria_activity_tracker;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Keeps track of each boss/event
 *
 * @author War Pigeon
 */
public class Activity
{
    /**
     * Object used by JavaFX to display an image
     */
    public final ImageView view;
    /**
     * Effect to darken the image.
     */
    private final ColorAdjust darkEffect;
    /**
     * Tracks the current state of the activity
     */
    private boolean complete = false;

    /**
     * The shortcut for this Activity
     */
    public final String shortcut;

    /**
     * Constructs an Activity
     * @param uri The URI of the image to use.
     * @param shortcut The shortcut to use.
     */
    public Activity(String uri, String shortcut)
    {
        Image color = new Image(Objects.requireNonNull(Activity.class.getResourceAsStream("Icons/Color/" + uri)));
        darkEffect = new ColorAdjust(0, 0, -0.8, -0.1);
        this.shortcut = shortcut;
        view = new ImageView(color);
        view.setPreserveRatio(true);
        view.setFitWidth(50);
        view.setFitHeight(50);
        view.setEffect(darkEffect);
        view.setOnMouseClicked(event -> toggle());
    }

    /**
     * Switches between colored image and dark image
     */
    public void toggle() {
        complete = !complete;
        if (complete)
            view.setEffect(null);
        else
            view.setEffect(darkEffect);
    }

    /**
     * Changes complete to false and image view to dark
     */
    public void reset() {
        complete = false;
        view.setEffect(darkEffect);
    }
}
