package source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StationCsv {

	public static ArrayList<Station> readCsv() {
		// Gebaseerd op
		// https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
		String csvFile = "NMBS/lib/stations.csv";
		String line = "";
		String cvsSplitBy = ",";

		ArrayList<Station> lijst = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] stationNaam = line.split(cvsSplitBy);
				Station station = new Station(0, stationNaam[0]);
				lijst.add(station);
				// System.out.println(stationNaam[0]);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return lijst;

	}

	/*
	 * public static void main(String[] args) { readCsv(); }
	 */

}