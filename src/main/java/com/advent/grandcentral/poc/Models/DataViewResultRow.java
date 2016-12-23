package com.advent.grandcentral.poc.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by smehdi on 10/3/2016.
 */
public class DataViewResultRow {
    public String DataRequestId;
    public Map<String, Object> Data;

    public DataViewResultRow()
    {
        Data = new HashMap<String,Object>();
    }
}
