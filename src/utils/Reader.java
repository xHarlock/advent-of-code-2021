package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
	
	/**
	 * Reads all the lines from a txt file and returns them as String[] array.
	 * 
	 * @param input = File with lines.
	 * @return String[] array that contains the lines from the file.
	 */
	public static String[] readLines(String file_path) {
		Scanner scanner = null;

		try {
			scanner = new Scanner(new File(file_path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		List<String> lines = new ArrayList<>();

		while (scanner.hasNextLine())
			lines.add(scanner.nextLine());

		scanner.close();
		return lines.toArray(new String[0]);
	}

	/**
	 * Reads all integers from a txt file and returns them as int[] array.
	 * 
	 * @param input = File with integers.
	 * @return int[] array containing the integers from the file.
	 */
	public static int[] readIntegers(String file_path) {
		Scanner scanner = null;

		try {
			scanner = new Scanner(new File(file_path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		List<Integer> ints = new ArrayList<>();

		while (scanner.hasNextInt())
			ints.add(scanner.nextInt());

		scanner.close();
		return ints.stream().mapToInt(k -> k).toArray();
	}
	
	/**
	 * Method that reads the input from the Advent of Code website and returns it as a String[] array.
	 * 
	 * @param day Day of advent.
	 * @param year Year of the event.
	 * @return String[] array with the input.
	 */
	public static String[] getInput(int day, int year) {
		String session = readLine("cookie.txt");
		
		CookieHandler.setDefault(new CookieManager());

		HttpCookie cookie = new HttpCookie("session", session);
		cookie.setPath("/");
		cookie.setVersion(0);

		String body = "Error";
		
		try {
			((CookieManager) CookieHandler.getDefault()).getCookieStore().add(new URI("https://adventofcode.com"), cookie);
			HttpClient client = HttpClient.newBuilder().cookieHandler(CookieHandler.getDefault()).connectTimeout(Duration.ofSeconds(10)).build();
			HttpRequest req = HttpRequest.newBuilder().uri(URI.create("https://adventofcode.com/" + year + "/day/" + day + "/input")).GET().build();
			body = client.send(req, HttpResponse.BodyHandlers.ofString()).body();
		} catch (URISyntaxException | IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return body.split("\n");
	}
	
	private static String readLine(String file_path) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(file_path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String s = scanner.nextLine();
		scanner.close();
		return s;
	}
}
