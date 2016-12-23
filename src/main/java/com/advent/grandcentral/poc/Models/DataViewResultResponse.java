package com.advent.grandcentral.poc.Models;

import java.util.*;

/**
 * Created by smehdi on 10/6/2016.
 */
public class DataViewResultResponse {

    public long Count;
    public ArrayList<Map<String, Object>> Data;

    public DataViewResultResponse()
    {
        Data = new ArrayList<Map<String, Object>>();
    }
}
