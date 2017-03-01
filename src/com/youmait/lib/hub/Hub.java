package com.youmait.lib.hub;

import com.youmait.lib.hub.actions.Action;
import com.youmait.lib.hub.data.DataObject;

public interface Hub {

    interface OnProgressListener{
        void onProgress(int progress);
        void onEvent(HubEvent event);
    }

    Action getCurrentAction();
    void setNextAction(Action action);
    void startProcess();
    void stopProcess();
    void pauseProcess();
    void setProgressListener(OnProgressListener progressListener);
    void removeProgressListener();
    void broadcastProgress(int i);
    void setData(DataObject dataObject);
    DataObject getData(String key);
    void setDebug(boolean debug);
    boolean getDebug();
    void log(String message);
}
