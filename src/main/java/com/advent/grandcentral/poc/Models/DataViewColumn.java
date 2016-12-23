package com.advent.grandcentral.poc.Models;

/**
 * Created by smehdi on 9/23/2016.
 */
public class DataViewColumn {

    public String Name;
    public String DataType;
    public DataViewColumnLink Link;

    public DataViewColumn()
    {

    }

    public DataViewColumn(String Name, String DataType)
    {
        this.Name = Name;
        this.DataType = DataType;
    }

    public DataViewColumn(String Name, String DataType, DataViewColumnLink Link)
    {
        this.Name = Name;
        this.DataType = DataType;
        this.Link = Link;
    }

}
