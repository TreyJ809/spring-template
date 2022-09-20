package io.lightfeather.springtemplate;

import java.lang.Exception;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/api/supervisors")
    public String getSupervisors() {
        String supervisors = calloutForManagersList();
        return supervisors;
    }

    @PostMapping(value = "/api/submit", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"} )
    public HttpStatus submit(@RequestBody PersonalInfo personalInfo) {
        
        if ( personalInfo.getFirstName() != null && personalInfo.getLastName() != null && personalInfo.getSupervisor() != null ) {
            return HttpStatus.ACCEPTED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }

    }

    //Callout to https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers
    private String calloutForManagersList() {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers"))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();

            HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, BodyHandlers.ofString());

            String managers = (String)response.body();

            JSONArray jArray = new JSONArray(managers);
            List<Manager> managerList = new ArrayList<Manager>();
            for (int i=0; i < jArray.length(); i++) {
                JSONObject obj = jArray.getJSONObject(i);

                String j = obj.getString("jurisdiction");
                if (!isNumeric(j)) { //Exclude Managers with numeric juridictions
                    String l = obj.getString("lastName");
                    String f = obj.getString("firstName");

                    managerList.add(new Manager(j, l, f));
                }
            }

            Collections.sort(managerList, new ManagerComparator());

            StringBuffer sortedManagers = new StringBuffer();
            for(Manager m : managerList) {
                sortedManagers.append(m.toString() + "  |  ");
            }

	        return sortedManagers.toString();
        } catch (Exception e) {
            //TODO: Handle/Log Exception - likely to be URISyntaxException, IOEception, or InterruptedException
        }

        return "ERROR";
    }

    //helper method to determine if a String is numeric or not
    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
}

}