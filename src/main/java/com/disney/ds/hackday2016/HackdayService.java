package com.disney.ds.hackday2016;

import com.disney.ds.hackday2016.model.FaceDetectionUnit;
import com.disney.ds.hackday2016.model.ImageDataExtraction;
import com.disney.ds.hackday2016.model.ImageDataUnit;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

/**
 * Created by peichen on 3/2/16.
 */

@Component
public class HackdayService {
    private static Logger log = Logger.getLogger(HackdayService.class);

    private Queue<ImageDataExtraction> imageExtraction;//= new CircularFifoQueue<>(size);
    private LinkedList<String> imageUrls = new LinkedList<>();
    private List<String> imageList= new ArrayList<String>();
    private String googleVisionApi;
    private String postJsonString;
    private RestTemplate restTemplate;
    private List<FaceDetectionUnit>faceArray;
    private List<ImageDataUnit>labelArray;
    private List<String>textArray;
    private List<ImageDataUnit>landmarkArray;
    private List<ImageDataUnit>logoArray;
    final private String [] tags = {"disney","disneyworld","disneyland","disneystore","disneyparks"};
    private String instagramBaseUrl;

    private List<String> cookies;
    private final String USER_AGENT = "Mozilla/5.0";


    @Autowired
    public HackdayService(@Value("${image.queue.size}") int size,@Value("${google.vision.api}") String googleVisionApi,@Value("${post.json.string}") String postJsonString,@Value("${instagram.base.url}")String instagramBaseUrl){
        imageExtraction = new CircularFifoQueue<>(size);
        this.googleVisionApi = googleVisionApi;
        this.postJsonString = postJsonString;
        restTemplate = new RestTemplate();
        this.instagramBaseUrl = instagramBaseUrl;
    }

    public String getImageQueue() throws Exception {
        faceArray = new ArrayList<>();
        labelArray = new ArrayList<>();
        textArray = new ArrayList<>();
        landmarkArray = new ArrayList<>();
        logoArray = new ArrayList<>();

        String url = getNextImageUrl();//"http://vignette2.wikia.nocookie.net/disney/images/b/b3/Walt-Disney-Logo.jpg/revision/latest?cb=20130721170547";//"https://scontent-lax3-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.134.1080.1080/12797960_1537628549869846_1129145049_n.jpg?ig_cache_key=MTE5NzM0OTQ3NDEwODQ2NzE3Ng%3D%3D.2.c";//getNextImageUrl();
        byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
        String request=postJsonString.replace("<img_base64>", Base64.encodeBase64String(imageBytes));
        HttpHeaders headers=new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE,"application/json");
        HttpEntity<String> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response=
                restTemplate.exchange(googleVisionApi, HttpMethod.POST,entity,  String.class);

        String responseBody=response.getBody();
        //System.out.println(responseBody);
        JSONObject obj = new JSONObject(responseBody).getJSONArray("responses").getJSONObject(0);
        Iterator<String> itr= obj.keys();
        while(itr.hasNext()){
            String key = itr.next();
            switch (key){
                case "faceAnnotations": {
                    JSONArray faces = obj.getJSONArray("faceAnnotations");
                    for (int j = 0; j < faces.length(); j++) {
                        JSONObject f = faces.getJSONObject(j);
                        FaceDetectionUnit fdu = new FaceDetectionUnit(String.valueOf(f.getDouble("detectionConfidence")), String.valueOf(f.getDouble("landmarkingConfidence")), f.getString("joyLikelihood"),
                                f.getString("sorrowLikelihood"), f.getString("angerLikelihood"), f.getString("surpriseLikelihood"), f.getString("underExposedLikelihood"),
                                f.getString("blurredLikelihood"), f.getString("headwearLikelihood"));
                        faceArray.add(fdu);
                    }
                    break;
                }
                case "labelAnnotations":
                    JSONArray labels = obj.getJSONArray("labelAnnotations");
                    for(int j=0;j<labels.length();j++){
                        JSONObject label = labels.getJSONObject(j);
                        ImageDataUnit idu = new ImageDataUnit(!label.has("description")?"":label.getString("description"),String.valueOf(label.getDouble("score")));
                        labelArray.add(idu);
                    }
                    break;
                case "textAnnotations":
                    JSONArray texts = obj.getJSONArray("textAnnotations");
                    for(int j=0;j<texts.length();j++){
                        JSONObject text = texts.getJSONObject(j);
                        textArray.add(text.getString("description"));
                    }
                    break;
                case "landmarkAnnotations":
                    JSONArray landmarks = obj.getJSONArray("landmarkAnnotations");
                    for(int j=0;j<landmarks.length();j++){
                        JSONObject landmark = landmarks.getJSONObject(j);
                        ImageDataUnit idu = new ImageDataUnit(!landmark.has("description")?"":landmark.getString("description"),String.valueOf(landmark.getDouble("score")));
                        landmarkArray.add(idu);
                    }
                    break;
                case "logoAnnotations":
                    JSONArray logos = obj.getJSONArray("logoAnnotations");
                    for(int j=0;j<logos.length();j++){
                        JSONObject logo = logos.getJSONObject(j);
                        ImageDataUnit idu = new ImageDataUnit(!logo.has("description")?"":logo.getString("description"),String.valueOf(logo.getDouble("score")));
                        logoArray.add(idu);
                    }
                    break;
                default:
                    break;

            }
        }


        //ImageDataExtraction sample = new ImageDataExtraction(url,Arrays.asList(a),Arrays.asList(a),Arrays.asList(a),Arrays.asList(a));
        //imageExtraction.add(sample);
        //imageId++;
        imageExtraction.add(new ImageDataExtraction(url,faceArray,labelArray,textArray,landmarkArray,logoArray));

        //System.out.println(respJson.getJSONObject("logoAnnotations") ==null);

        return new Gson().toJson(imageExtraction);

    }

    private String getNextImageUrl() throws Exception {
        String url;
        url = imageUrls.poll();
        if(url == null){
            imageUrls = refillInstagramData();
            url = imageUrls.poll();
        }
        return url;
    }

    //Cannot scrape Instagram with Jsoup
    private LinkedList<String> refillInstagramData() throws Exception{
        log.info("Refilling Instagram Data...");
        LinkedList<String> refill = new LinkedList<>();

        String response = GetPageContent(instagramBaseUrl+ tags[(int)(Math.random() * 50)%tags.length]+"/");
        String [] resp = response.split("\"display_src\":\"");
        System.out.println(resp.length);

        for(int i=1; i<resp.length; i++){
            String imageUrl = resp[i].split("\"")[0].replace("\\","");
            System.out.println(imageUrl);
            refill.add(imageUrl);
            if(refill.size() >= 200){
                break;
            }
        }

        //Document doc = Jsoup.connect(instagramBaseUrl+ tags[(int)(Math.random() * 50)%tags.length]+"/?hl=zh-cn").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").followRedirects(true).get();
//        Document doc = new Document(response);
//        System.out.println(doc.toString().split("img").length);
//        Elements images = doc.getElementsByTag("img");
//        System.out.println(images.size());
//
//        for(Element image:images){
//            System.out.println(image.absUrl("src"));
//            refill.add(image.absUrl("src"));
//            if(refill.size() >= 200){
//                break;
//            }
//        }
        return refill;
    }

    private String GetPageContent(String url) throws Exception {

        URL obj = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        conn.setUseCaches(false);

        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        if (cookies != null) {
            for (String cookie : cookies) {
                conn.addRequestProperty("Cookie", cookie);
            }
        }
        int responseCode = conn.getResponseCode();
        log.info("Sending 'GET' request to URL : {}" + url);
        log.info("Response Code : " + responseCode);

        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Get the response cookies
        this.cookies = conn.getHeaderFields().get("Set-Cookie");
        return response.toString();
    }
}

