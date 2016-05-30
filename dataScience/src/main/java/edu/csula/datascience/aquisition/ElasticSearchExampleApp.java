package edu.csula.datascience.aquisition;

import com.google.gson.Gson;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * A quick elastic search example app
 *
 * It will parse the csv file from the resource folder under main and send these
 * data to elastic search instance running locally
 *
 * After that we will be using elastic search to do full text search
 *
 * gradle command to run this app `gradle esExample`
 */
public class ElasticSearchExampleApp {
    private final static String indexName = "homework-data";
    private final static String typeName = "homework1";

    public static void main(String[] args) throws URISyntaxException, IOException {
        Node node = nodeBuilder().settings(Settings.Builder()
            .put("cluster.name", "real-real-priyank")
            .put("path.home", "elasticsearch-data")).node();
        Client client = node.client();

        /**
         *
         *
         * INSERT data to elastic search
         */

        // as usual process to connect to data source, we will need to set up
        // node and client// to read CSV file from the resource folder
        File csv = new File(
            ClassLoader.getSystemResource("Deaths.csv")
                .toURI()
        );

        // create bulk processor
        BulkProcessor bulkProcessor = BulkProcessor.builder(
            client,
            new BulkProcessor.Listener() {
                @Override
                public void beforeBulk(long executionId,
                                       BulkRequest request) {
                }

                @Override
                public void afterBulk(long executionId,
                                      BulkRequest request,
                                      BulkResponse response) {
                }

                @Override
                public void afterBulk(long executionId,
                                      BulkRequest request,
                                      Throwable failure) {
                    System.out.println("Facing error while importing data to elastic search");
                    failure.printStackTrace();
                }
            })
            .setBulkActions(10000)
            .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
            .setFlushInterval(TimeValue.timeValueSeconds(5))
            .setConcurrentRequests(1)
            .setBackoffPolicy(
                BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
            .build();

        // Gson library for sending json to elastic search
        Gson gson = new Gson();

		try {
			// after reading the csv file, we will use CSVParser to parse
			// through
			// the csv files
			CSVParser parser = CSVParser.parse(csv, Charset.defaultCharset(), CSVFormat.EXCEL.withHeader());

			// for each record, we will insert data into Elastic Search
			parser.forEach(record -> {
				Temperature deathobj = new Temperature(record.get("Death_year"), record.get("Age_years"), record.get("Gender"),
						record.get("Death_cause"));
				bulkProcessor.add(new IndexRequest().source(gson.toJson(deathobj)));

				System.out.println((record.get("Death_year") +" "+ record.get("Age_years") + record.get("Gender")
				+ record.get("Death_cause")));

			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	
    /**
     * AGGREGATION
     */
 //   SearchResponse sr = node.client().prepareSearch(indexName)
   //     .setTypes(typeName)
     //   .setQuery(QueryBuilders.matchAllQuery())
        //.execute().actionGet();

    // Get your facet results
    //Terms agg1 = sr.getAggregations().get("stateAgg");

    //for (Terms.Bucket bucket: agg1.getBuckets()) {
      //  System.out.println(bucket.getKey() + ": " + bucket.getDocCount());
    //}
}
}

    class Temperature {
    	private String death_year;
		private String age_year;
		private String gender;
		private String death_cause;

		public Temperature(String death_year, String age_year, String gender, String death_cause) {
			super();
			this.death_year = death_year;
			this.age_year = age_year;
			this.gender = gender;
			this.death_cause = death_cause;
		}

    	
    	/*    final String date;
        final double averageTemperature;
        final String state;
        final String country;

        public Temperature(String date, double averageTemperature, String state, String country) {
            this.date = date;
            this.averageTemperature = averageTemperature;
            this.state = state;
            this.country = country;
        }
    }*/
}

