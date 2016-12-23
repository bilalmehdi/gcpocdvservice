package com.advent.grandcentral.poc.Models;

/**
 * Created by smehdi on 11/28/2016.
 */
public class DataViewColumnLink {
    public String ClassName;
    public String KeyName;

    public DataViewColumnLink()
    {

    }

    public DataViewColumnLink(String ClassName, String KeyName)
    {
        this.ClassName = ClassName;
        this.KeyName = KeyName;
    }

}
