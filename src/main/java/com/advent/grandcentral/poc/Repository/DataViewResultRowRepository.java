package com.advent.grandcentral.poc.Repository;

/**
 * Created by smehdi on 10/5/2016.
 */

import com.advent.grandcentral.poc.Models.DataViewResultRow;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataViewResultRowRepository extends MongoRepository<DataViewResultRow, String> {

}
