package com.youmait.lib.hub.actions;

import com.youmait.lib.hub.Hub;

public abstract class RetryAction extends AbstractAction {

    private int retryCount = 0;
    private int maxRetry = 1;

    public RetryAction(Hub hub){
        super(hub);
    }

    public RetryAction retry(int times){
        this.maxRetry = times;
        return this;
    }

    public void retry(){
        retryCount++;
        if(canRetry()){
            logRetry();
            hub.setNextAction(this);
        }else{
            failure();
        }

    }

    private void logRetry() {
            hub.log(
                    "Retrying Action " + getClass().getName() +
                            " " + retryCount + " / " + maxRetry
            );
    }

    private boolean canRetry() {
        return retryCount < maxRetry;
    }

    public int getMaxRetry() {
        return maxRetry;
    }

    public void setMaxRetry(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    @Override
    public void stop() {
        retryCount = maxRetry;
    }


    public void init(){
        retryCount = 0;
    }

    @Override
    public void success() {
        init();
        super.success();

    }

    @Override
    public void failure() {
        init();
        super.failure();
    }
}
