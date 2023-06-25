package entity;

import java.io.*;
import java.net.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class API_Getter {

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null;) {
                result.append(line);
            }
        }
        return result.toString();
    }

    public static String[] getHTTP() throws MalformedURLException, ProtocolException, IOException, ParseException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://www.boredapi.com/api/activity");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null;) {
                result.append(line);
            }
        }

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result.toString());

        String bored_activity = (String) json.get("activity");
        String bored_type = (String) json.get("type");
        String bored_participants = String.valueOf(json.get("participants"));
        String bored_price = String.valueOf(json.get("price"));
        String bored_link = (String) json.get("link");
        String bored_key = (String) json.get("key");
        String bored_access = String.valueOf(json.get("accessibility"));

        String[] bored_return = {bored_activity, bored_type, bored_participants, bored_price, bored_link, bored_key, bored_access};
        return bored_return;
    }

    public static void main(String[] args) throws Exception {
        String api_link = "https://www.boredapi.com/api/activity";
        System.out.println(getHTML(api_link));
        
        for (String acts : getHTTP()) {
            System.out.println(acts);
        }
    }
}
