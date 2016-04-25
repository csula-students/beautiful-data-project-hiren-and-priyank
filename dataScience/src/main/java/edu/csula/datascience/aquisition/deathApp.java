package edu.csula.datascience.aquisition;

import java.io.IOException;

public class deathApp {

	public static void main(String[] args) throws IOException {
		deathSource obj = new deathSource();
		int count = 0;
		obj.downloadDeathRecords(
				"https://kaggle2.blob.core.windows.net/datasets/28/32/DeathRecords.zip?sv=2012-02-12&se=2016-04-28T01%3A50%3A20Z&sr=b&sp=r&sig=FgiH2w1GRFkylc6VHS8uN4ULuTKKbZnNz8w0X6KJhac%3D");
		obj.downloadDeathRecords("https://data.hartford.gov/api/views/vj8v-7qz9/rows.csv?accessType=DOWNLOAD",
				"../DeathSource",count);
		count++;
		obj.downloadDeathRecords("https://data.illinois.gov/api/views/t224-vrp2/rows.csv?accessType=DOWNLOAD",
				"../DeathSource",count);
		count = 0;
		// obj.downloadDeathRecords("https://data.hartford.gov/api/views/vj8v-7qz9/rows.csv?accessType=DOWNLOAD","../download/Deathrecords");
	}

}
