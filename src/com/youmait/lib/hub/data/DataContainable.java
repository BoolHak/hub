package com.youmait.lib.hub.data;

import java.util.List;

public interface DataContainable {
    void setData(String key, DataObject object);
    DataObject getData(String key);
    List<String> getAllKey();
}
