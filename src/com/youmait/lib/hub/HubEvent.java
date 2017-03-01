package com.youmait.lib.hub;

public enum HubEvent {
    STARTED,
    FINISHED,
    FAILED;

    private String message = "No Message Set";

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }

    private Object data = null;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void resetEvent(){
        message = "No Message Set";
        data = null;
    }


}
