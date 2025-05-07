package com.example.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class WhoisLookupService {

    private static final String API_KEY = "at_p9oMvlyBm5T9xfClFR2M9e4uQq48b";
    private static final String API_URL = "https://www.whoisxmlapi.com/whoisserver/WhoisService";

    public String fetchWhoisData(String domain) throws Exception {
        String urlStr = API_URL + "?apiKey=" + API_KEY + "&domainName=" +
                URLEncoder.encode(domain, StandardCharsets.UTF_8) + "&outputFormat=JSON";

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            return in.lines().collect(Collectors.joining());
        }
    }

    public Map<String, Object> parseDomainInfo(String json) {
        JSONObject obj = new JSONObject(json).getJSONObject("WhoisRecord");
       
        Map<String, Object> result = new HashMap<>();
        result.put("domainName", obj.optString("domainName"));
        result.put("registrar", obj.optString("registrarName"));
        result.put("registrationDate", obj.optString("createdDate"));
        result.put("expirationDate", obj.optString("expiresDate"));
        result.put("estimatedDomainAge", obj.optInt("estimatedDomainAge"));

        JSONArray hostArr =  obj.getJSONObject("nameServers").optJSONArray("hostNames");

        String hostnames = "";
        for (int i = 0; i < hostArr.length(); i++) {
            hostnames += hostArr.getString(i) + (i < hostArr.length() - 1 ? ", " : "");
        }

        if (hostnames.length() > 25) {
            hostnames = hostnames.substring(0, 25) + "...";
        }
        System.out.println(hostnames);
        result.put("hostnames", hostnames);
        return result;
    }

    public Map<String, Object> parseContactInfo(String json) {
        JSONObject obj = new JSONObject(json).getJSONObject("WhoisRecord");

        Map<String, Object> result = new HashMap<>();
        result.put("registrantName", obj.optJSONObject("registrant") != null ?
                obj.getJSONObject("registrant").optString("name") : "");
        result.put("technicalContactName", obj.optJSONObject("technicalContact") != null ?
                obj.getJSONObject("technicalContact").optString("name") : "");
        result.put("administrativeContactName", obj.optJSONObject("administrativeContact") != null ?
                obj.getJSONObject("administrativeContact").optString("name") : "");
        result.put("contactEmail", obj.optString("contactEmail"));

        return result;
    }
}
