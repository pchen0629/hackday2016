package com.disney.ds.hackday2016;

import com.disney.ds.hackday2016.model.ImageDataExtraction;
import com.google.gson.Gson;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Queue;

/**
 * Created by peichen on 3/2/16.
 */

@Component
public class HackdayService {
    private Queue<ImageDataExtraction> imageExtraction;//= new CircularFifoQueue<>(size);
    private Queue<String> imageUrls;

    @Autowired
    public HackdayService(@Value("${image.queue.size}") int size){
        imageExtraction = new CircularFifoQueue<>(size);
        //imageUrls = new
    }

    public String getImageQueue() throws Exception {
        String [] a = {"a","b","c"};
        ImageDataExtraction sample = new ImageDataExtraction("www.google.com",Arrays.asList(a),Arrays.asList(a),Arrays.asList(a),Arrays.asList(a));
        imageExtraction.add(sample);
        return new Gson().toJson(imageExtraction);
    }
}
