package com.youmait.lib.hub.actions;


import com.youmait.lib.hub.Hub;

public class FinishedAction extends SimpleAction {


    public FinishedAction(Hub hub) {
        super(hub);
    }

    @Override
    public void run() {
        hub.log("Hub Progress finished");
        
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
}
