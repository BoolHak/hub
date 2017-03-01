package com.youmait.lib.hub;

import com.youmait.lib.hub.actions.Action;
import com.youmait.lib.hub.actions.FailedAction;
import com.youmait.lib.hub.actions.FinishedAction;
import com.youmait.lib.hub.data.*;

public abstract class AbstractHub implements Hub {

    protected Action currentAction;
    protected Logger logger = new HubLogger();
    protected boolean debug;
    protected OnProgressListener progressListener;
    private DataContainer dataContainer = new DataContainer();

    @Override
    public Action getCurrentAction() {
        return currentAction;
    }

    public void StartHub(Action action){
        broadcastEvent(HubEvent.STARTED);
        broadcastProgress(0);
        setNextAction(action);
    }

    @Override
    public void broadcastProgress(int i) {
        if(progressListener != null){
            progressListener.onProgress(i);
        }
    }

    private void broadcastEvent(HubEvent event){
        if(progressListener != null){
            progressListener.onEvent(event);
        }
    }

    @Override
    public void setNextAction(Action action) {

        if(action instanceof FailedAction){
            broadCastFailedEvent((FailedAction) action);
            broadcastProgress(0);
            return;
        }

        if(action instanceof FinishedAction){
            broadcastEvent(HubEvent.FINISHED);
            broadcastProgress(100);
            return;
        }

        this.currentAction = null;
        this.currentAction = action;
        action.run();
    }

    private void broadCastFailedEvent(FailedAction action) {
        Exception exp = action.getExp();
        String message = exp.getMessage();

        HubEvent event = HubEvent.FAILED;
        event.resetEvent();

        event.setData(exp);
        event.setMessage(message);

        broadcastEvent(event);
    }

    @Override
    public void stopProcess() {
        if(currentAction != null){
            currentAction.stop();
            log("Progress Stopped");
        }else{
            log("Current Action is null");
        }
    }


    @Override
    public void setProgressListener(OnProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    public void removeProgressListener() {
        progressListener = null;
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public boolean getDebug() {
        return debug;
    }

    @Override
    public void log(String message) {
        if(debug)
            logger.log(message);
    }

    @Override
    public void pauseProcess() {
        //TODO: Pause Thread then save current state
    }

    @Override
    public void setData(DataObject dataObject) {
        log("Data set key:" + dataObject.getKey());
        dataContainer.setData(dataObject.getKey(),dataObject);
    }

    @Override
    public DataObject getData(String key) {
        log("Data requested key:" + key);
        return dataContainer.getData(key);
    }

}
