package edu.csula.datascience.aquisition;

import org.junit.Before;
import org.junit.Test;

public class deathTest {

	private deathSource deathSource;

	@Before
	public void setup() {
		deathSource = new deathSource();
	}

	@Test
	public void testdeathRecord() throws Exception {
		deathSource.downloadDeathRecords();
	}

	//@Test
	/*public void testdeathData() throws Exception {
		deathSource.downloadDeathRecords(
				"https://kaggle2.blob.core.windows.net/datasets/28/32/DeathRecords.zip?sv=2012-02-12&se=2016-04-24T22%3A59%3A33Z&sr=b&sp=r&sig=0MJiLWzfuwHCNHaRPZN4imtJwYVL%2FSGzAVGt7eVVcdU%3D");
		deathSource.downloadDeathRecords("https://data.hartford.gov/api/views/vj8v-7qz9/rows.csv?accessType=DOWNLOAD",
				"https://data.illinois.gov/api/views/t224-vrp2/rows.csv?accessType=DOWNLOAD",
				"https://raw.githubusercontent.com/mihi-tr/list-of-deaths-dpkg/master/data/listofdeath.csv");
	}*/

}
