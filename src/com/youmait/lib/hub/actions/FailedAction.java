package com.youmait.lib.hub.actions;

import com.youmait.lib.hub.Hub;

public class FailedAction extends SimpleAction {
    private Exception exp;
    public FailedAction(Hub hub,Exception exp) {
        super(hub);
        this.exp = exp;
    }

    @Override
    public void run() {
        hub.log("Hub finished with Exception : " + exp.getMessage());
    }

    @Override
    public void success() {

    }

    @Override
    public void failure() {

    }

    @Override
    public void stop() {

    }

    public Exception getExp() {
        return exp;
    }
}
