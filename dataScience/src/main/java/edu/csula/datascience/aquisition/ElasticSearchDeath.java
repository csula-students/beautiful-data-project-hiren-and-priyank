package edu.csula.datascience.aquisition;

import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.node.Node;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * */
public class ElasticSearchDeath {
	private final static String indexName = "homework-data";
	private final static String typeName = "homework";

	public static void main(String[] args) throws URISyntaxException, IOException {
		Node node = nodeBuilder().settings(
				Settings.Builder().put("cluster.name", "real-real-priyank").put("path.home", "elasticsearch-data"))
				.node();
		Client client = node.client();

		/**
		 *
		 *
		 * INSERT data to elastic search
		 */

		
		File csv = new File(ClassLoader.getSystemResource("test.csv").toURI());

		// create bulk processor
		BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
			@Override
			public void beforeBulk(long executionId, BulkRequest request) {
			}

			@Override
			public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
			}

			@Override
			public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
				System.out.println("Facing error while importing data to elastic search");
				failure.printStackTrace();
			}
		}).setBulkActions(10000).setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
				.setFlushInterval(TimeValue.timeValueSeconds(5)).setConcurrentRequests(1)
				.setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3)).build();

		Gson gson = new Gson();

		try {
			CSVParser parser = CSVParser.parse(csv, Charset.defaultCharset(), CSVFormat.EXCEL.withHeader());

			parser.forEach(record -> {
				Death deathobj = new Death(record.get("Death_year"), record.get("Age_years"), record.get("Gender"),
						record.get("Death_cause"));
				 bulkProcessor.add(new IndexRequest().source(gson.toJson(deathobj)));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class Death {

		private String Death_year;
		private String Age_year;
		private String Gender;
		private String Death_cause;

		public Death(String death_year, String age_year, String gender, String death_cause) {
			super();
			this.Death_year = death_year;
			this.Age_year = age_year;
			this.Gender = gender;
			this.Death_cause = death_cause;
		}

		public String getDeath_year() {
			return Death_year;
		}

		public void setDeath_year(String death_year) {
			this.Death_year = death_year;
		}

		public String getAge_year() {
			return Age_year;
		}

		public void setAge_year(String age_year) {
			this.Age_year = age_year;
		}

		public String getGender() {
			return Gender;
		}

		public void setGender(String gender) {
			this.Gender = gender;
		}

		public String getDeath_cause() {
			return Death_cause;
		}

		public void setDeath_cause(String death_cause) {
			this.Death_cause = death_cause;
		}

	}
}
