package star_reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class StarReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "https://www.astronomynotes.com/tables/tablesc.htm";
		HashMap<String, Integer> stars = new HashMap<>();
		ArrayList<Double> distance = new ArrayList<Double>();
		ArrayList<Double> brightness = new ArrayList<Double>();
		Scanner scnr = new Scanner(System.in);
		int index = 0;
		try {
			Document doc = Jsoup.connect(url).get();
			for (Element row: doc.select("tr")) {
				if (row.select("td:nth-of-type(2)").text().equals("")) {
					continue;
				}
				else {
					stars.put(row.select("td:nth-of-type(2)").text(), index);
					distance.add(Double.parseDouble(row.select("td:nth-of-type(5)").text()) / 3.26);
					brightness.add(Double.parseDouble(row.select("td:nth-of-type(3)").text()));
					index++;
				}
			}
			System.out.println("Hey there! I can tell you about stars!");
			System.out.println("What star do you want to know about? Enter Q to quit!");
			String input = scnr.nextLine();
			while (!input.equals("q") || !input.equals("Q")) {
				System.out.println();
				if (stars.containsKey(input)) {
					int pos = stars.get(input);
					if (pos < 100) {
						System.out.println(input + " is among the 100 nearest stars to our solar system!");
					}
					else {
						System.out.println(input + " is among the 100 brightest stars as seen from earth!");
					}
					System.out.println("It is " + distance.get(pos) + " light years away from us!");
					System.out.print("It's V mag value is " + brightness.get(pos) + " which is ");
					if (brightness.get(pos) < 6.5) {
						System.out.println("visible to the human eye.");
					}
					else {
						System.out.println("not visible to the human eye.");
					}
				}
				else {
					System.out.println("I don't seem to have any information about that star!");
				}
				System.out.println("\nDo you want to know about any other stars? Enter Q to quit!");
				input = scnr.nextLine();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
