package com.advent.grandcentral.poc.Repository;

import com.advent.grandcentral.poc.Models.DataViewRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by smehdi on 9/28/2016.
 */
public interface DataViewRequestRepository extends MongoRepository<DataViewRequest, String>{
}
