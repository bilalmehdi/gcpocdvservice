package com.advent.grandcentral.poc.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smehdi on 10/3/2016.
 */
public class DataResult
{
    public List<DataViewResultRow> Data;
    public DataResult()
    {
        Data = new ArrayList<DataViewResultRow>();
    }
}
