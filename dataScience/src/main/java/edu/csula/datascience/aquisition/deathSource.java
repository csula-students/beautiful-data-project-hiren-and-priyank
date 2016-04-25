package edu.csula.datascience.aquisition;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.mongodb.BasicDBObject;

public class deathSource extends mongoDB implements Source<String> {

	private static final int BUFFER_SIZE = 4096;

	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	public Collection<String> next() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Download Resources From Kaggle */
	public void downloadDeathRecords(String path) throws IOException {
		URL url = new URL(path);

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// Check for errors
		int responseCode = con.getResponseCode();
		InputStream inputStream;
		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("OK");
			inputStream = con.getInputStream();
		} else {
			inputStream = con.getErrorStream();
		}

		OutputStream output = new FileOutputStream("Source.zip");
		// Process the response
		BufferedReader reader;
		String line = null;

		byte[] buffer = new byte[8 * 1024]; // Or whatever
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) > 0) {
			output.write(buffer, 0, bytesRead);
		}

		output.close();
		inputStream.close();
		String zipFilePath = "Source.zip";
		String destDirectory = "../DeathSource";

		unzip(zipFilePath, destDirectory);
		deathCollectorRecord collectorObj = new deathCollectorRecord();
		collectorObj.save("../DeathSource/DeathRecords.csv", "Collections");
	}

	public static void unzip(String zipFilePath, String destDirectory) throws IOException {
		File destDir = new File(destDirectory);
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry entry = zipIn.getNextEntry();
		// iterates over entries in the zip file
		while (entry != null) {
			String filePath = destDirectory + File.separator + entry.getName();
			if (!entry.isDirectory()) {
				// if the entry is a file, extracts it
				extractFile(zipIn, filePath);
			} else {
				// if the entry is a directory, make the directory
				File dir = new File(filePath);
				dir.mkdir();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
	}

	private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}

	public void downloadDeathRecords() {
		// TODO Auto-generated method stub

	}

	public void downloadDeathRecords(String path, String destination, int count) throws IOException {
		// TODO Auto-generated method stub
		String downloadedFileName = "data" + count + ".csv";

		// Open connection to the file
		URL url = new URL(path);
		InputStream is = url.openStream();
		// Stream to the destionation file
		FileOutputStream fos = new FileOutputStream(destination + "/" + downloadedFileName);

		// Read bytes from URL to the local file
		byte[] buffer = new byte[4096];
		int bytesRead = 0;

		System.out.print("Downloading " + downloadedFileName);
		while ((bytesRead = is.read(buffer)) != -1) {
			System.out.print("."); // Progress bar :)
			fos.write(buffer, 0, bytesRead);
		}
		System.out.println("done!");

		// Close destination stream
		fos.close();
		// Close URL stream
		is.close();
		deathCollectorRecord collectorObj = new deathCollectorRecord();
		collectorObj.save("../DeathSource/data" + count + ".csv", "Collections");
	}

}
