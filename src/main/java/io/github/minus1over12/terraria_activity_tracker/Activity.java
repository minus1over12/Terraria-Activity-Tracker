package io.github.minus1over12.terraria_activity_tracker;

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
     * Image used for when an activity is complete.
     */
    private final Image color;
    /**
     * Image used for when an activity is not complete. I made them using PhotoScape's filters
     */
    private final Image dark;
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
        dark = new Image(Objects.requireNonNull(Activity.class.getResourceAsStream("Icons/Dark/" + uri)));
        color = new Image(Objects.requireNonNull(Activity.class.getResourceAsStream("Icons/Color/" + uri)));
        this.shortcut = shortcut;
        view = new ImageView(dark);
        view.setPreserveRatio(true);
        view.setFitWidth(50);
        view.setFitHeight(50);
        view.setOnMouseClicked(event -> toggle());
    }

    /**
     * Switches between colored image and dark image
     */
    public void toggle() {
        complete = !complete;
        if (complete)
            view.setImage(color);
        else
            view.setImage(dark);
    }

    /**
     * Changes complete to false and image view to dark
     */
    public void reset() {
        complete = false;
        view.setImage(dark);
    }
}
