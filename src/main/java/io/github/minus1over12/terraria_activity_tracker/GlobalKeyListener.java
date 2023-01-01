package io.github.minus1over12.terraria_activity_tracker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeInputEvent;
import com.github.kwhat.jnativehook.dispatcher.VoidDispatchService;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.lang.reflect.Field;

// Code based off template from JNativeHook

/**
 * @author War Pigeon
 */
public class GlobalKeyListener implements NativeKeyListener {
    private final Activity[] activities;
    private String input;
    private boolean reading;

    /**
     * The key listener used by the Activity Tracker.
     * @param activities The activities being tracked
     */
    public GlobalKeyListener(Activity[] activities) {
        GlobalScreen.setEventDispatcher(new VoidDispatchService());
        this.activities = activities;

    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        if ((e.getKeyCode() == NativeKeyEvent.VC_END) || (e.getKeyLocation() == 4 && e.getKeyCode() ==
                NativeKeyEvent.VC_1))
            for (Activity activity : activities)
                activity.reset();
        if (e.getKeyCode() == NativeKeyEvent.VC_F6) {
            consume(e);
            input = "";
            reading = true;
            System.out.println("Reading");
        } else if (reading) {
            consume(e);
                input += NativeKeyEvent.getKeyText(e.getKeyCode()).toUpperCase();
                for (Activity activity : activities) {
                    if (input.equals(activity.shortcut)) {
                        activity.toggle();
                        reading = false;
                        System.out.println("Activity Toggled");
                        break;
                    }
                }
                if (input.length() >= 3) {
                    reading = false;
                    System.out.println("Reading stopped");
                }
        }
        System.out.println(input);
    }

    //Code from JNativeHook docs

    /**
     * Prevents other apps (Terraria) from reading an input event
     */
    private void consume(NativeInputEvent e) {
        try {
            Field f = NativeInputEvent.class.getDeclaredField("reserved");
            f.setAccessible(true);
            f.setShort(e, (short) 0x01);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
