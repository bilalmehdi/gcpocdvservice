package com.advent.grandcentral.poc.controllers;

import com.advent.grandcentral.poc.Models.DataView;
import com.advent.grandcentral.poc.Repository.DataViewRepository;

import static com.mongodb.client.model.Sorts.ascending;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by smehdi on 9/23/2016.
 */
@RestController
@RequestMapping("/api/DataView")
public class DataViewController {

    private static final Logger log = LoggerFactory.getLogger(DataViewController.class);

    @Autowired
    private DataViewRepository dataViewRepository;


    @RequestMapping(method = RequestMethod.GET)
    public List<DataView>  getDataViewsPerType(@RequestParam(name="type",required = false) String dataViewType)
    {
        log.info("Get All DataView");
        List<DataView> list = null;
        if(dataViewType != null && dataViewType.length() > 0)
        {
            list = dataViewRepository.findByType(dataViewType);
        }
        else {
          list =   dataViewRepository.findAll();
        }
        return list;
    }

    @RequestMapping(value = "/{dataViewId}", method = RequestMethod.GET)
    public DataView  getDataViews(@PathVariable String dataViewId)
    {
        log.info(String.format("Get DataView for DataViewId=%s ",dataViewId));
        DataView dv;
        dv = dataViewRepository.findOne(dataViewId);
        return dv;


    }


    @RequestMapping( method = RequestMethod.POST)
    public DataView  insertDataView(@RequestBody DataView request) {
        DataView dvr;
        dvr = dataViewRepository.insert(request);
        return dvr;
    }

    @RequestMapping(value = "/{dataViewId}", method = RequestMethod.DELETE)
    public void  deleteDataView(@PathVariable String dataViewId) {

        //Delete is not support;
        //Simulating Kill Switch
        System.exit(0);

    }

}
