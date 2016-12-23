package com.advent.grandcentral.poc.Repository;

/**
 * Created by smehdi on 9/28/2016.
 *
 *
 */

import com.advent.grandcentral.poc.Models.DataView;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DataViewRepository extends MongoRepository<DataView, String> {

    @Query(value="{ 'Type' : ?0 }")
    List<DataView> findByType(String dataType);
}
