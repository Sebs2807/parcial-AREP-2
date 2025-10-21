
package escuelaing.edu.co.proxy.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class RedirectController {

    private static final String USER_AGENT = "Mozilla/5.0";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/proxy")
    public String handleRequest(@RequestParam String path) {
        try {
            return redirectRequest(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\":\"Error al procesar la solicitud\"}";
        }
    }

    private String redirectRequest(String path) throws IOException {
        String targetUrl = "";

        if (path.startsWith("/collatzsequence")) {
            if(counter.get() % 2 == 0){
                targetUrl = "http://35.175.176.16:8081/collatzsequence?value=" + path.split("=")[1];
                counter.incrementAndGet();
                System.out.println("Entro al primer servidor");
            }else{
                targetUrl = "http://23.20.118.119:8082/collatzsequence?value=" + path.split("=")[1];
                counter.incrementAndGet();
                System.out.println("Entro al segundo servidor");
            }
        } 
        URL url = new URL(targetUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            }
        } else {
            return "{\"error\":\"Error al consultar el servidor\"}";
        }
    }
}
