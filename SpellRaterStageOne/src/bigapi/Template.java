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
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Template {

    public static void main(String[] args) {
        HttpClient httpclient = HttpClients.createDefault();

        try {
            URIBuilder builder = new URIBuilder("https://api.cognitive.microsoft.com/bing/v5.0/spellcheck/");

            builder.setParameter("mode", "{string}");
            builder.setParameter("mkt", "{string}");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");
            request.setHeader("Ocp-Apim-Subscription-Key", "{subscription key}");

            // Request body
            StringEntity reqEntity = new StringEntity("{body}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
