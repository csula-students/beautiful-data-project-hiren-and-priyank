package edu.csula.datascience.aquisition;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.mongodb.BasicDBObject;

public class deathCollectorRecord extends mongoDB implements Collector<List<String>, List<String>> {

	public Collection<List<String>> mungee(Collection<List<String>> src) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(String path, String mongoDBCollection) {
		// TODO Auto-generated method stub

		String csvFile = path;
		BufferedReader br = null;
		String line1 = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line1 = br.readLine()) != null) {

				BasicDBObject document = new BasicDBObject();

				String[] Agetype = line1.split(cvsSplitBy);
				if (csvFile == "DeathRecords.csv") {
					if (Agetype[7] == "") {
						Agetype[7] = "N/A";
					}
					if (Agetype[8] == "") {
						Agetype[8] = "N/A";
					}
					if (Agetype[10] == "") {
						Agetype[10] = "N/A";
					}
					if (Agetype[14] == "") {
						Agetype[14] = "N/A";
					}
					if (Agetype[15] == "") {
						Agetype[15] = "N/A";
					}
					if (Agetype[17] == "") {
						Agetype[17] = "N/A";
					}
					if (Agetype[19] == "") {
						Agetype[19] = "N/A";
					}
					document.put("Month of Date", Agetype[7]);
					document.put("Sex ", Agetype[8]);
					document.put("Age", Agetype[10]);
					document.put("Place of Death", Agetype[14]);
					document.put("Maratile Status", Agetype[15]);
					document.put("Year", Agetype[17]);
					document.put("Mannar of Death", Agetype[19]);
				}
				if (csvFile == "data0.csv") {
					if (Agetype[1] == "") {
						Agetype[1] = "N/A";
					}
					if (Agetype[2] == "") {
						Agetype[2] = "N/A";
					}
					if (Agetype[3] == "") {
						Agetype[3] = "N/A";
					}
					if (Agetype[5] == "") {
						Agetype[5] = "N/A";
					}
					document.put("Year of Death", Agetype[1]);
					document.put("Age ", Agetype[2]);
					document.put("Gender", Agetype[3]);
					document.put("Cause of Death", Agetype[5]);
				}
				if (csvFile == "data1.csv") {
					if (Agetype[1] == "") {
						Agetype[1] = "N/A";
					}
					if (Agetype[2] == "") {
						Agetype[2] = "N/A";
					}
					if (Agetype[5] == "") {
						Agetype[5] = "N/A";
					}
					document.put("Year of Death", Agetype[1]);
					document.put("Age ", Agetype[2]);
					document.put("Cause of Death", Agetype[5]);
				}
				collection.insert(document);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}

	public List<SimpleModel> mungee(Object next) {
		// TODO Auto-generated method stub
		return null;
	}
}
