package com.advent.grandcentral.poc.Models;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by smehdi on 9/23/2016.
 */
@Document(collection = "DataView")
public class DataView
{

    @Id
    public  String _id;
    public  String Name;
    @Field("Type")
    public  String Type;
    public  String Text;
    public List<DataViewColumn> Columns;

    public DataView()
    {
        Columns = new ArrayList<DataViewColumn>();
    }

    public DataView(String _id, String Name, String Type)
    {
        this._id = _id;
        this.Name = Name;
        this.Type = Type;

        Columns = new ArrayList<DataViewColumn>();
    }


    @Override
    public String toString() {
        return String.format(
                "DataView[Id=%s, Name='%s', Type='%s']",       _id, Name, Type);
    }
}
