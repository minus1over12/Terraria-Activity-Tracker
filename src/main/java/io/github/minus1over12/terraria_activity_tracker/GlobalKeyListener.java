package io.github.minus1over12.terraria_activity_tracker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

// Code based off template from JNativeHook

/**
 * @author War Pigeon
 */
public class GlobalKeyListener implements NativeKeyListener {
    private final Activity[] activities;
    private boolean control, shift = false;

    public GlobalKeyListener(Activity[] activities) {
        GlobalScreen.setEventDispatcher(new SwingDispatchService());
        this.activities = activities;
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        // Update if modifier keys are being held
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_CONTROL -> control = true;
            case NativeKeyEvent.VC_SHIFT -> shift = true;
        }

        // Figure out toggling the activities. Hardcoded for 25.
        if (!control && !shift) {
            switch (e.getKeyCode()) {
                case NativeKeyEvent.VC_F2 -> activities[0].toggle();
                case NativeKeyEvent.VC_F3 -> activities[1].toggle();
                case NativeKeyEvent.VC_F4 -> activities[2].toggle();
                case NativeKeyEvent.VC_F5 -> activities[3].toggle();
                case NativeKeyEvent.VC_F6 -> activities[4].toggle();
                case NativeKeyEvent.VC_INSERT -> activities[5].toggle();
                case NativeKeyEvent.VC_DELETE -> activities[6].toggle();
                case NativeKeyEvent.VC_HOME -> activities[7].toggle();
                case NativeKeyEvent.VC_END -> activities[8].toggle();
                case NativeKeyEvent.VC_PAGE_UP -> activities[9].toggle();
                case NativeKeyEvent.VC_PAGE_DOWN -> activities[10].toggle();
            }
        }
        else if (!control && shift) {
            switch (e.getKeyCode()) {
                case NativeKeyEvent.VC_F2 -> activities[11].toggle();
                case NativeKeyEvent.VC_F3 -> activities[12].toggle();
                case NativeKeyEvent.VC_F4 -> activities[13].toggle();
                case NativeKeyEvent.VC_F5 -> activities[14].toggle();
                case NativeKeyEvent.VC_F6 -> activities[15].toggle();
                case NativeKeyEvent.VC_INSERT -> activities[16].toggle();
                case NativeKeyEvent.VC_DELETE -> activities[17].toggle();
                case NativeKeyEvent.VC_HOME -> activities[18].toggle();
                case NativeKeyEvent.VC_END -> activities[19].toggle();
                case NativeKeyEvent.VC_PAGE_UP -> activities[20].toggle();
                case NativeKeyEvent.VC_PAGE_DOWN -> activities[21].toggle();
            }
        }
        else if (control && shift) {
            switch (e.getKeyCode()) {
                case NativeKeyEvent.VC_F2 -> activities[22].toggle();
                case NativeKeyEvent.VC_F3 -> activities[23].toggle();
                case NativeKeyEvent.VC_F4 -> activities[24].toggle();
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_CONTROL -> control = false;
            case NativeKeyEvent.VC_SHIFT -> shift = false;
        }
    }

}
