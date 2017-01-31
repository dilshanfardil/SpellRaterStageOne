/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigapi;

/**
 *
 * @author Dilshan
 */
// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestA {

    public TestA() {
    }

    public static void main(String[] args) {
        String checkString = "Hi whats up guys i im the prsindt of Sri lnaka My name is Namal Appuhami and i from Narammala";
        checkSpell(checkString, "proof");
    }

    // Test String
    /*
        Mode --  proof  and  spell
        Poof:     Meant to provide Office Word like spelling corrections. It can correct long queries, provide 
                        casing corrections and suppresses aggressive corrections.
        Spell:      Meant to provide Search engine like spelling corrections. It will correct small 
                        queries(up to length 9 tokens) without any casing changes and will be more 
                    optimized (perf and relevance) towards search like queries.
     */
    // 
    public static ArrayList checkSpell(String checkString, String mode) {
        HttpClient httpclient = HttpClients.createDefault();
        ArrayList errosArylt = new ArrayList();
        try {
            URIBuilder builder = new URIBuilder("https://api.cognitive.microsoft.com/bing/v5.0/spellcheck/");

            builder.setParameter("text", checkString);
            builder.setParameter("mode", mode);
            builder.setParameter("mkt", "en-us");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", "4d49365255f3483e8ec5cab53c873570");

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String toString = EntityUtils.toString(entity);
                System.out.println(toString);

                JSONObject root = new JSONObject(toString);

                JSONArray sportsArray = root.getJSONArray("flaggedTokens");

                // Array Lenth Cotains the Error Count --0
                for (int i = 0; i < sportsArray.length(); i++) {
                    JSONObject firstSport = sportsArray.getJSONObject(i);
                    String name = firstSport.getString("token");
                    errosArylt.add(name);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception :  " + e.getMessage());
        }
        return errosArylt;
    }

}
