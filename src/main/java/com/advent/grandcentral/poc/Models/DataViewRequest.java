package com.advent.grandcentral.poc.Models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by smehdi on 9/28/2016.
 */

@Document(collection = "DataViewRequest")
public class DataViewRequest {

    @Id
    public String _id;
    public String Status; //Submitted, Processing, Complete

    public String DataViewId;
    public String DataViewName;
    public Date CreatedDate;
    public Date ProcessingEnd;
    public Date ProcessingStart;
    public Parameters Parameters;

    public DataViewRequest() {
        Parameters = new Parameters();
    }
}