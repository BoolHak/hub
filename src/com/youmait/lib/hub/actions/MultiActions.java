package com.youmait.lib.hub.actions;

import com.youmait.lib.hub.Hub;

public abstract class MultiActions extends AbstractAction {

    public interface OnResultListener{
        void onResult(int option);
    }

    public MultiActions(Hub hub) {
        super(hub);
    }

    public abstract void setOnResultListener(OnResultListener listener);



}
