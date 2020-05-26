package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExtractor {

	public static void main(String[] args) {
		String website = "https://www.hft-stuttgart.de/";
		String content = readContents(website);

		extractEmail(content);
	}

	public static void extractEmail(String contents) {
		String regex = "[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+\\.(?!ai|qt)([a-z]{2}|com)\\b";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(contents);

		while (matcher.find()) {
			System.out.println("Gefundene Email Addresse: " + matcher.group());
		}
	}

	public static String readContents(String website) {
		try {
			URL url = new URL(website);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			String contents = "";
			String line;

			while ((line = reader.readLine()) != null) {
				contents += line + "\n";
			}
			System.out.println(contents);
			reader.close();

			return contents;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}