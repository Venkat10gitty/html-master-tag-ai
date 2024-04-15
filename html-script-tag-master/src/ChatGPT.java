import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.json.JSONObject;

public class ChatGPT {
	public static String chatGPT(String prompt) throws Exception {
		String url = "https://api.openai.com/v1/completions";
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Authorization", "Bearer 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");

		JSONObject data = new JSONObject();
		data.put("model", "text-davinci-003");
//		data.put("model", "gpt-3.5-turbo");

//		data.put("model", "davinci:ft-personal-2023-04-07-17-43-30");
		data.put("prompt", prompt);
		data.put("max_tokens", 4000);
		data.put("temperature", 1.0);

		con.setDoOutput(true);
		con.getOutputStream().write(data.toString().getBytes());

//		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		String line = br.readLine();
//		while (line!=null) {
//			System.out.println("... " + line);
//			line = br.readLine();
//		}
		String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
				.reduce((a, b) -> a + b).get();

		// System.out.println(new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text"));
		JSONObject ret = new JSONObject(output);
		return ret.getJSONArray("choices").getJSONObject(0).getString("text");
	}

	public static void main(String[] args) throws Exception {
		SortedMap<String,String> items = new TreeMap<String,String>();
		items.put("RAR1 Loss of market share","Loss of market share in both the local and international markets due to competition with lower prices, three-year degree programs, expansion of the public education sector (especially at the graduate level), and delays in obtaining residence permits for international students from non-EU countries (which may discourage them from applying to the university or even force them to withdraw). ");
//		items.put("RAR11 Ineffective career services","Ineffective career services");
//		items.put("RAR12 Unfunded liability related to the safety and security of students","Unfunded liability related to the safety and security of students living on the Schoolâs campus, particularly related to increased medical issues of study abroad students: a) from students with pre-existing serious health and mental issues, and b) serious accidents during their stay at the School.");
//		items.put("RAR14 Completeness of Code of Conduct","Completeness of Code of Conduct and sexual harassment, and all policies included in Student Handbook");
//		items.put("RAR17 Student Services do not meet needs of students","Student Services do not meet needs of students (low quality, high price) ");
//		items.put("RAR18 Change in the mix of population","Change in the mix of population of Deree students resulting from a drop in Greek degree students");
//		items.put("RAR19 Enrollment will be affected by the cease of Open University validation","Enrollment will be affected by the cease of Open University validation");
//		items.put("RAR2 Inefficient use of resources in the admissions and international students department","Inefficient use of resources in the admissions and international students department, which slows turnaround time");
//		items.put("RAR20 Reputational risk for SA and International students","Reputational risk for SA and International students if students are unhappy with their program or housing");
//		items.put("RAR22 Inefficient admissions office and recruiting","Inefficient admissions office and recruiting");
//		items.put("RAR22 Long-term, continuing emotional and psychological effects of COVID on students","Long-term, continuing emotional and psychological effects of COVID on students' motivation to learn");
//		items.put("RAR23 Rising amounts of mental health issues","Rising amounts of mental health issues particularly from the US students");
//		items.put("RAR3 Compromise quality by increasing the quantity of students admitted.","Compromise quality by increasing the quantity of students admitted.");
//		items.put("RAR4 Unexpected deviations in number and qualitative characteristics of potential students","Unexpected deviations in number and qualitative characteristics of potential students");
//		items.put("RAR5 Competition has more attractive programs","Competition has more attractive programs (in terms of content and/ or scheduling) that address chage in the market for skills and current market needs");
//		items.put("RAR7 Ineffective enrollment/recruitment approach","Ineffective enrollment/recruitment approach");
//		items.put("RAR9 Problems with institutions","Problems with institutions housing facilities and incidents occurring from violation of student housing rules and other inappropriate and/or aggressive/violent behaviour (Consumption of alcohol or illegal substances, accidents, threats to other students by physiologically disturbed students etc.)");

		String query = "Which Controls should be set up for: ";
		for (Map.Entry<String,String> e: items.entrySet()) {
			System.out.println("\n\n\n" + e.getKey());
			System.out.println(chatGPT(query + "\"" + e.getValue() + "\""));
		}
//		chatGPT("Which Controls should be set up for: \"Loss of market share in both the local and international markets due to competition with lower prices, three-year degree programs, expansion of the public education sector (especially at the graduate level), and delays in obtaining residence permits for international students from non-EU countries (which may discourage them from applying to the university or even force them to withdraw).\""); //Risk Dependency of Intakes to SPJain, Inherent Risk: Medium, Residual Risk: Low, Propose controls");
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String line = "";
//		while (true) {
//			System.out.print("> ");
//			line = br.readLine();
//			if (line.equals("x")) {
//				break;
//			}
//			chatGPT(line);
//		}
	}
}