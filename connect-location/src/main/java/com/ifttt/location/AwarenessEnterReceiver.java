package com.ifttt.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.awareness.fence.FenceState;

/**
 * BroadcastReceiver that listens to all "enter" geo-fence events.
 */
public final class AwarenessEnterReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        FenceState fenceState = FenceState.extract(intent);
        if (fenceState.getCurrentState() != FenceState.TRUE) {
            return;
        }

        String stepId = LocationEventUploadHelper.extractStepId(fenceState.getFenceKey());
        LocationEventUploader.schedule(context, LocationEventUploader.EventType.Entry, stepId);
    }
}
