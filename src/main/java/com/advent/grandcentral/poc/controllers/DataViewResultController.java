package com.advent.grandcentral.poc.controllers;

import com.advent.grandcentral.poc.Models.*;
import com.advent.grandcentral.poc.Repository.DataViewRequestRepository;
import com.advent.grandcentral.poc.Repository.DataViewResultRowRepository;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by smehdi on 10/5/2016.
 */
@RestController
@RequestMapping("/api/DataViewResult")
public class DataViewResultController {

    private static final Logger log = LoggerFactory.getLogger(DataViewResultController.class);

    @Autowired
    private DataViewResultRowRepository dataViewResultRowRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping(value = "/{dataViewRequestId}", method = RequestMethod.GET)
    public DataViewResultResponse getDataViewRequest(@PathVariable String dataViewRequestId) {
        DataViewResultResponse dr = new DataViewResultResponse();

        Criteria criteria = new Criteria();
        criteria.and("DataRequestId").is(dataViewRequestId);
        Query query = new Query(criteria);

        List<DataViewResultRow> rows = mongoTemplate.find(query,DataViewResultRow.class,"dataViewResultRow");

        dr.Count  = rows.size();
        for(DataViewResultRow row : rows)
        {
            dr.Data.add(row.Data);
        }

        return dr;
    }

    @RequestMapping( method = RequestMethod.POST)
    public DataViewResultResponse GetDataViewResult(@RequestBody DataViewResultRequest request)
    {
        DataViewResultResponse dr = new DataViewResultResponse();
        Query query;

        Criteria criteria = getFilterCriteria(request);
        query = new Query(criteria);

        dr.Count = mongoTemplate.count(query,DataViewResultRow.class,"dataViewResultRow");

        if(request.Select != null)
        {
            for(String fieldName : request.Select) {

             query.fields().include("Data."+fieldName);
            }
        }

        if(request.Sort != null )
        {
            if(request.Sort.size() > 0) {
                for(SortEntry sortEntry : request.Sort) {
                    String fieldName = "Data." + sortEntry.Field;
                    if (sortEntry.Dir.equalsIgnoreCase("Dsc")) {
                        query.with(new Sort(Sort.Direction.DESC, fieldName));
                    } else {
                        query.with(new Sort(Sort.Direction.ASC, fieldName));
                    }
                }
            }
        }



        if(request.Take != 0)
        {
            int pageIndex =  request.Skip / request.Take;
            query.with(new PageRequest(pageIndex,request.Take));
        }

        log.info(query.toString());
        List<DataViewResultRow> rows = mongoTemplate.find(query,DataViewResultRow.class,"dataViewResultRow");


        for(DataViewResultRow row : rows)
        {
            dr.Data.add(row.Data);
        }

        return dr;
    }


    private Criteria getFilterCriteria(DataViewResultRequest request)
    {
        List<Criteria> listCriteria = new ArrayList<>();
        Criteria criteria = new Criteria();

        criteria.and("DataRequestId").is(request.DataRequestId);

        listCriteria.add(criteria);


        if(request.Filter != null) {


            for (FilterEntry entry : request.Filter) {


                if(entry.Field != null ) {
                    criteria = new Criteria();
                    String fieldName = "Data." + entry.Field;

                    if(entry.Operator == null)
                    {
                        entry.Operator = "eq";
                    }

                    switch (entry.Operator) {
                        case "eq":
                            criteria.and(fieldName).is(entry.Value);
                            break;
                        case "contains":
                            criteria.and(fieldName).regex(entry.Value.toString());
                            break;
                        case "gt":
                            criteria.and(fieldName).gt(entry.Value);
                            break;
                        case "lt":
                            criteria.and(fieldName).lt(entry.Value);
                            break;
                        case "gte":
                            criteria.and(fieldName).gte(entry.Value);
                            break;
                        case "lte":
                            criteria.and(fieldName).lte(entry.Value);
                            break;
                        default:
                            criteria.and(fieldName).is(entry.Value);
                            break;
                    }
                    listCriteria.add(criteria);
                }

            }
        }

        Criteria composedCriteria = new Criteria();
        composedCriteria = composedCriteria.andOperator(listCriteria.toArray(new Criteria[listCriteria.size()]));

        return composedCriteria;
    }


}
