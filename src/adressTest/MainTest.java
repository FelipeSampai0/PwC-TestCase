package adressTest;

import java.util.ArrayList;
import java.util.regex.*;

public class MainTest {
	
	static ArrayList<String> Streets = new ArrayList<String>();
	static ArrayList<String> Numbers = new ArrayList<String>();

	public static void main(String[] args) {
		
		String[] testAdresses1 = new String[] {"Miritiba 339", "Babaçu 500", "Cambuí 804B"};		
		String[] testAdresses2 = new String[] {"Rio Branco 23", "Quirino dos Santos 23 b"};		
		String[] testAdresses3 = new String[] {"4, Rue de la République", "100 Broadway Av", "Calle Sagasta, 26", "Calle 44 No 1991"};
		
		for (String e : testAdresses1) {			
			separateAdressData(e);			
		}
		
		
		System.out.println(Streets);
		System.out.println(Numbers);
	}
	
	public static void separateAdressData(String input) {	
		
		String streetFound = new String();		
		String numberFound = new String();

		Pattern adressPattern = Pattern.compile("\\b[a-zA-Z]+\\b");
		Pattern numberPattern = Pattern.compile("\\b(?:\\d+\\w|No)\\b");
		
		Matcher streetMatcher = adressPattern.matcher(input);
		while(streetMatcher.find()) {
			streetFound += streetMatcher.group();			
		}	
		
		Matcher numberMatcher = numberPattern.matcher(input);		
		while(numberMatcher.find()) {
			numberFound += numberMatcher.group();
		}
		
		Streets.add(streetFound);
		Numbers.add(numberFound);
	
	}

}
