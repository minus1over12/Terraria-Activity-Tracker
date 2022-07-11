package io.github.minus1over12.terraria_activity_tracker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeInputEvent;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;
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
    private boolean control, shift, f2, f3, altControls = false;
    int numpad;

    public GlobalKeyListener(Activity[] activities) {
        GlobalScreen.setEventDispatcher(new VoidDispatchService());
        this.activities = activities;
        if (System.getProperty("os.name").startsWith("Windows"))
            numpad = 1;
        else
            numpad = 4;
    }

    public void nativeKeyPressed(NativeKeyEvent e) {

        // Update if modifier keys are being held
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_CONTROL -> control = true;
            case NativeKeyEvent.VC_SHIFT -> shift = true;
            case NativeKeyEvent.VC_F2 -> f2 = true;
            case NativeKeyEvent.VC_F3 -> f3 = true;
        }

        if ((f2 && e.getKeyCode() == NativeKeyEvent.VC_F3) || (f3 && e.getKeyCode() == NativeKeyEvent.VC_F2))
            altControls = !altControls;

        if ((e.getKeyLocation() == numpad && e.getKeyCode() == NativeKeyEvent.VC_END) || (e.getKeyLocation() == 4 && e.getKeyCode() == NativeKeyEvent.VC_1))
            for (Activity activity : activities)
                activity.reset();


        if (!altControls) {

            // Figure out toggling the activities. Hardcoded for 25.
            if (!control && !shift) {
                switch (e.getKeyCode()) {
                    case NativeKeyEvent.VC_F2 -> activities[0].toggle();
                    case NativeKeyEvent.VC_F3 -> activities[1].toggle();
                    case NativeKeyEvent.VC_F4 -> activities[2].toggle();
                    case NativeKeyEvent.VC_F5 -> activities[3].toggle();
                    case NativeKeyEvent.VC_F6 -> activities[4].toggle();
                    case NativeKeyEvent.VC_INSERT -> {
                        if (e.getKeyLocation() != numpad) activities[5].toggle();
                    }
                    case NativeKeyEvent.VC_DELETE -> {
                        if (e.getKeyLocation() != numpad) activities[6].toggle();
                    }
                    case NativeKeyEvent.VC_HOME -> {
                        if (e.getKeyLocation() != numpad) activities[7].toggle();
                    }
                    case NativeKeyEvent.VC_END -> {
                        if (e.getKeyLocation() != numpad) activities[8].toggle();
                    }
                    case NativeKeyEvent.VC_PAGE_UP -> {
                        if (e.getKeyLocation() != numpad) activities[9].toggle();
                    }
                    case NativeKeyEvent.VC_PAGE_DOWN -> {
                        if (e.getKeyLocation() != numpad) activities[10].toggle();
                    }
                }
            } else if (!control && shift) {
                switch (e.getKeyCode()) {
                    case NativeKeyEvent.VC_F2 -> activities[11].toggle();
                    case NativeKeyEvent.VC_F3 -> activities[12].toggle();
                    case NativeKeyEvent.VC_F4 -> activities[13].toggle();
                    case NativeKeyEvent.VC_F5 -> activities[14].toggle();
                    case NativeKeyEvent.VC_F6 -> activities[15].toggle();
                    case NativeKeyEvent.VC_INSERT -> {
                        if (e.getKeyLocation() != numpad) activities[16].toggle();
                    }
                    case NativeKeyEvent.VC_DELETE -> {
                        if (e.getKeyLocation() != numpad) activities[17].toggle();
                    }
                    case NativeKeyEvent.VC_HOME -> {
                        if (e.getKeyLocation() != numpad) activities[18].toggle();
                    }
                    case NativeKeyEvent.VC_END -> {
                        if (e.getKeyLocation() != numpad) activities[19].toggle();
                    }
                    case NativeKeyEvent.VC_PAGE_UP -> {
                        if (e.getKeyLocation() != numpad) activities[20].toggle();
                    }
                    case NativeKeyEvent.VC_PAGE_DOWN -> {
                        if (e.getKeyLocation() != numpad) activities[21].toggle();
                    }
                }
            } else if (control && shift) {
                switch (e.getKeyCode()) {
                    case NativeKeyEvent.VC_F2 -> activities[22].toggle();
                    case NativeKeyEvent.VC_F3 -> activities[23].toggle();
                    case NativeKeyEvent.VC_F4 -> activities[24].toggle();
                }
            }
        } else { // Alternate control scheme that consumes the key events, so it does not trigger things in Terraria.
            if (!shift) {
                switch (e.getKeyCode()) {
                    case NativeKeyEvent.VC_F1 -> activities[0].toggle();
                    case NativeKeyEvent.VC_F2 -> activities[1].toggle();
                    case NativeKeyEvent.VC_F3 -> activities[2].toggle();
                    case NativeKeyEvent.VC_F4 -> activities[3].toggle();
                    case NativeKeyEvent.VC_F5 -> activities[4].toggle();
                    case NativeKeyEvent.VC_F6 -> activities[5].toggle();
                    case NativeKeyEvent.VC_F7 -> {
                        activities[6].toggle();
                        consume(e);
                    }
                    case NativeKeyEvent.VC_F8 -> {
                        activities[7].toggle();
                        consume(e);
                    }
                    case NativeKeyEvent.VC_F9 -> activities[8].toggle();
                    case NativeKeyEvent.VC_F10 -> {
                        activities[9].toggle();
                        consume(e);
                    }
                    case NativeKeyEvent.VC_F11 -> {
                        activities[10].toggle();
                        consume(e);
                    }
                    case NativeKeyEvent.VC_INSERT -> {
                        if (e.getKeyLocation() != numpad) activities[11].toggle();
                    }
                    case NativeKeyEvent.VC_DELETE -> {
                        if (e.getKeyLocation() != numpad) activities[12].toggle();
                    }
                    case NativeKeyEvent.VC_HOME -> {
                        if (e.getKeyLocation() != numpad) activities[13].toggle();
                    }
                    case NativeKeyEvent.VC_END -> {
                        if (e.getKeyLocation() != numpad) activities[14].toggle();
                    }
                    case NativeKeyEvent.VC_PAGE_UP -> {
                        if (e.getKeyLocation() != numpad) activities[15].toggle();
                    }
                    case NativeKeyEvent.VC_PAGE_DOWN -> {
                        if (e.getKeyLocation() != numpad) activities[16].toggle();
                    }
                    case NativeKeyEvent.VC_SCROLL_LOCK -> activities[17].toggle();
                }
            } else {
                switch (e.getKeyCode()) {
                    case NativeKeyEvent.VC_F1 -> activities[18].toggle();
                    case NativeKeyEvent.VC_F2 -> activities[19].toggle();
                    case NativeKeyEvent.VC_F3 -> activities[20].toggle();
                    case NativeKeyEvent.VC_F4 -> activities[21].toggle();
                    case NativeKeyEvent.VC_F5 -> activities[22].toggle();
                    case NativeKeyEvent.VC_F6 -> activities[23].toggle();
                    case NativeKeyEvent.VC_F7 -> {
                        activities[24].toggle();
                        consume(e);
                    }
                }
            }
        }
    }


    public void nativeKeyReleased(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_CONTROL -> control = false;
            case NativeKeyEvent.VC_SHIFT -> shift = false;
            case NativeKeyEvent.VC_F2 -> f2 = false;
            case NativeKeyEvent.VC_F3 -> f3 = false;
        }
        if (altControls) {
            switch (e.getKeyCode()) {
                case NativeKeyEvent.VC_F7, NativeKeyEvent.VC_F8, NativeKeyEvent.VC_F10, NativeKeyEvent.VC_F11 ->
                        consume(e);
            }
        }
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
