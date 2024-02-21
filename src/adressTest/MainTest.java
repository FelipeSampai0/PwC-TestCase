package adressTest;

import java.util.ArrayList;
import java.util.regex.*;
import java.util.Scanner;

public class MainTest {

	static ArrayList<String> Streets = new ArrayList<String>();
	static ArrayList<String> Numbers = new ArrayList<String>();

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);

		String[] testAdresses1 = new String[] { "Miritiba 339", "Babaçu 500", "Cambuí 804B", "Rio Branco 23",
				"Quirino dos Santos 23 b", "4, Rue de la République", "100 Broadway Av", "Calle Sagasta, 26",
				"Calle 44 No 1991" };

		for (String e : testAdresses1) {
			separateAdressData(e);
		}
		
		// print test

		for (int i = 0; i < Streets.size(); i++) {
			System.out.printf("| %-30s | %-20s | %6s |%n", testAdresses1[i], Streets.get(i), Numbers.get(i));
		}		
		
	}	

	public static void separateAdressData(String input) {

		String streetFound = new String();
		String numberFound = new String();

		// Regex for adress:(Verify boundary or ",")((Ignore "No" and "b")(Look for N
		// size word | N size word after space after another word | Look for digits
		// preceding "No"))(Verify boundary or
		// ",")

		Pattern adressPattern = Pattern
				.compile("(?<=\\b|,)((?!No|b)[\\p{L}{2,}]+|(?<=\\p{L})\\s[\\p{L}{2,}]+|\\s\\d+(?=\\s+No))(?:\\b|,)");

		// Regex for numb er:(Ignore numbers before "No")(Verify boundary or ",")(Look
		// for: number before space before word | "No" | Number+letter sequence )(Verify
		// boundary or ",");

		Pattern numberPattern = Pattern.compile("(?!(?:.*\\bNo\\b.*\\d))(?:\\b|,)(\\d+\\s\\w|No\\s|no\\s|\\d+\\w*)(?:\\b|,)");

		Matcher streetMatcher = adressPattern.matcher(input);
		while (streetMatcher.find()) {
			streetFound += streetMatcher.group();
		}

		Matcher numberMatcher = numberPattern.matcher(input);
		while (numberMatcher.find()) {
			numberFound += numberMatcher.group();
		}

		Streets.add(streetFound);
		Numbers.add(numberFound);

	}

}
