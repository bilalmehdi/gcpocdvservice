package com.advent.grandcentral.poc.Models;

import java.util.ArrayList;

/**
 * Created by smehdi on 10/5/2016.
 */
public class DataViewResultRequest {
    public String DataRequestId;
    public int Skip;
    public int Take;
    public ArrayList<String> Select;
    public ArrayList<FilterEntry> Filter;
    public ArrayList<SortEntry> Sort;

    public DataViewResultRequest()
    {
        Select = new ArrayList<String>();
        Filter = new ArrayList<>();
        Sort = new ArrayList<>();

    }

}
