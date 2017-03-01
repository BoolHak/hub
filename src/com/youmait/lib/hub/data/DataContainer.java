package com.youmait.lib.hub.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataContainer implements DataContainable {

    private Map<String, DataObject> map = new HashMap<String, DataObject>();


    @Override
    public void setData(String key, DataObject object) {
        map.put(key,object);
    }

    @Override
    public DataObject getData(String key) {
        if(map.containsKey(key))
            if(map.get(key) != null) return map.get(key);
        return new EmptyObject();
    }

    @Override
    public List<String> getAllKey() {
        return new ArrayList<String>(map.keySet());
    }
}
