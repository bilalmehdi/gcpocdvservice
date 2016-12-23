package com.advent.grandcentral.poc.controllers;


import com.advent.grandcentral.poc.Models.DataView;
import com.advent.grandcentral.poc.Models.DataViewRequest;

import com.advent.grandcentral.poc.Repository.DataViewRepository;
import com.advent.grandcentral.poc.Repository.DataViewRequestRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.WebApplicationException;
import java.util.Date;
import java.util.List;

/**
 * Created by smehdi on 9/28/2016.
 */

@RestController
@RequestMapping("/api/DataViewRequest")
public class DataViewRequestController {

    private static final Logger log = LoggerFactory.getLogger(DataViewRequestController.class);

    @Autowired
    private DataViewRequestRepository  dataViewRequestRepository;

    @Autowired
    private DataViewRepository dataViewRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<DataViewRequest> getAllDataViewRequests()
    {
        log.info("Get All DataView Request");
        List<DataViewRequest> list = dataViewRequestRepository.findAll(new Sort(Sort.Direction.DESC, "CreatedDate"));
        return list;
    }

    @RequestMapping(value = "/{dataViewRequestId}", method = RequestMethod.GET)
    public DataViewRequest  getDataViewRequest(@PathVariable String dataViewRequestId) {

        log.info(String.format("Get DataViewRequest for DataViewRequestId=%s",dataViewRequestId));
        DataViewRequest dv = dataViewRequestRepository.findOne(dataViewRequestId);
        return dv;
    }

    @RequestMapping( method = RequestMethod.POST)
    public DataViewRequest  insertDataViewRequest(@RequestBody DataViewRequest request) {

        log.info("Insert DataView Request");

        if( request.DataViewId == null)
        {
            throw new BadRequestException("Missing DataView Id");
        }

        DataView dataView = dataViewRepository.findOne(request.DataViewId);
        if( dataView == null)
        {
            throw  new BadRequestException("Invalid data view id; DataView not found");
        }

        request.DataViewName = dataView.Name;
        request.Status = "Submitted";
        request.CreatedDate = new Date();
        DataViewRequest dvr = dataViewRequestRepository.insert(request);
        return dvr;
    }

    @RequestMapping( method = RequestMethod.DELETE)
    public void  deleteDataViewRequest(@RequestBody DataViewRequest request) {
       dataViewRequestRepository.delete(request);
    }
}
