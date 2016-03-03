package com.disney.ds.hackday2016;

import com.disney.ds.hackday2016.model.ImageDataExtraction;
import com.google.gson.Gson;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Created by peichen on 3/2/16.
 */

@Component
public class HackdayService {
    private Queue<ImageDataExtraction> imageExtraction;//= new CircularFifoQueue<>(size);
    private Queue<String> imageUrls;
    private static int imageId=0;
    private List<String> imageList= new ArrayList<String>();

    @Autowired
    public HackdayService(@Value("${image.queue.size}") int size){
        imageExtraction = new CircularFifoQueue<>(size);
        imageList.add("http://images.clipartpanda.com/mickey-mouse-clubhouse-clipart-mickey-mouse-clubhouse-clipart-mickey-clip-art-clipart-panda---free-clipart-images.jpg");
        imageList.add("http://orig15.deviantart.net/ebda/f/2012/124/9/c/mickey_mouse_trace_by_deepfry3-d4yhnpz.png");
        imageList.add("http://i131.photobucket.com/albums/p314/martiuks/MICKEY.jpg");
        imageList.add("http://blog.dedoles.sk/wp-content/uploads/mickey-mouse1.jpg");
        imageList.add("http://i.dailymail.co.uk/i/pix/2008/10/15/article-1077707-0070560500000258-199_233x423.jpg");
        imageList.add("http://www.cartoonbucket.com/wp-content/uploads/2015/04/Image-of-Micky-Mouse-On-Skate-Board.gif");
        //imageUrls = new
    }

    public String getImageQueue() throws Exception {
        imageId++;
        String [] a = {"a","b","c"};
        ImageDataExtraction sample = new ImageDataExtraction(imageList.get(imageId%6),Arrays.asList(a),Arrays.asList(a),Arrays.asList(a),Arrays.asList(a));
        imageExtraction.add(sample);
        imageId++;

        return new Gson().toJson(imageExtraction);

    }
}
