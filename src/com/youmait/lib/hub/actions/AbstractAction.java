package com.youmait.lib.hub.actions;


import com.youmait.lib.hub.Hub;
import com.youmait.lib.hub.data.DataObject;

public abstract class AbstractAction implements Action {

    protected Hub hub;
    private Action nextAction;
    private Action failedAction;
    private int progress = -1;

    public AbstractAction(Hub hub){
        this.hub = hub;
    }

    @Override
    public void setNextAction(Action action) {
        this.nextAction = action;
    }

    @Override
    public void setFailedAction(Action action) {
        this.failedAction = action;
    }

    @Override
    public void success() {

        if(nextAction != null){
            if(progress != -1)
                hub.broadcastProgress(progress);
            hub.setNextAction(nextAction);
        }
        else
            hub.setNextAction(
                    new FailedAction(hub,new Exception("Next action not set"))
            );
    }

    @Override
    public void failure() {
        if(failedAction != null)
            hub.setNextAction(failedAction);
        else
            hub.setNextAction(
                    new FailedAction(hub,new Exception("Default failed action")
                    )
            );
    }

    public Action getNextAction() {
        return nextAction;
    }

    public Action getFailedAction() {
        return failedAction;
    }


    public AbstractAction next(Action action){
        this.nextAction = action;
        return this;
    }

    public AbstractAction progress(int value){
        this.progress = value;
        return this;
    }

    public AbstractAction failed(Action action){
        this.failedAction = action;
        return this;
    }

    public AbstractAction failed(String message){
        this.failedAction = new FailedAction(hub,new Exception(message));
        return this;
    }

    public AbstractAction isFinal(){
        this.nextAction = new FinishedAction(hub);
        return this;
    }

    public <T> T getData(String key){
        DataObject data = hub.getData(key);
        Object object = data.getObject();
        try {
            return (T) object;
        }catch (ClassCastException exp){
            return null;
        }

    }

    public void sendData(String key, Object value){
        hub.setData(new DataObject(key,value));
    }
}
