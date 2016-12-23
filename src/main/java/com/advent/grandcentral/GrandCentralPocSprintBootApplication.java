package com.advent.grandcentral;

import com.advent.grandcentral.poc.Models.DataView;
import com.advent.grandcentral.poc.Repository.DataViewRepository;
import com.advent.grandcentral.poc.controllers.DataViewRequestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import sun.misc.IOUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GrandCentralPocSprintBootApplication {

	private static final Logger log = LoggerFactory.getLogger(GrandCentralPocSprintBootApplication.class);

	@Value(value = "classpath:seeddata/dataview.json")
	private Resource dataViewJSON;

	@Autowired
	private DataViewRepository dataViewRepository;
	public static void main(String[] args) {
		SpringApplication.run(GrandCentralPocSprintBootApplication.class, args);
	}

	@PostConstruct
	private void SeedData()
	{
		log.info("All Components Wired");

		long count  = dataViewRepository.count();

		if(count == 0) {
			log.info("Inserting Seed Data");
			ObjectMapper objectMapper = new ObjectMapper();

			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();

			String line;
			try {

				br = new BufferedReader(new InputStreamReader(dataViewJSON.getInputStream()));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				String dataViewJSONString = sb.toString();

				DataView[] dataViews = objectMapper.readValue(dataViewJSONString, DataView[].class);

				List<DataView> lstDataViews = new ArrayList<>();
				for(DataView dv : dataViews)
				{
					lstDataViews.add(dv);
				}
				dataViewRepository.insert(lstDataViews);

				log.info(dataViewJSONString);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			log.info("Seed Data Exist");
		}

	}
}
