package com.main.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.main.entity.Product;

public class CSVHelper {
	public static String TYPE = "text/csv";
	static String[] HEADERs = { "productId", "productName", "productDescription", "productDiscountedPrice","productActualPrice","productCategory","productStock","productImageLink" };

	public static boolean hasCSVFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<Product> csvToTutorials(InputStream is) {

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Product> products = new ArrayList<Product>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord csvRecord : csvRecords) {
				Product product = new Product(

						Integer.parseInt(csvRecord.get("productId")), csvRecord.get("productName"),
						csvRecord.get("productDescription"),
						Double.parseDouble(csvRecord.get("productDiscountedPrice")),
						Double.parseDouble(csvRecord.get("productActualPrice")), csvRecord.get("productCategory"), 
						Integer.parseInt(csvRecord.get("productStock")),
						csvRecord.get("productImageLink")

				);
				products.add(product);

			}
			return products;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file :" + e.getMessage());
		}

	}

}
