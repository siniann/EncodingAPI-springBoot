package com.bitmovin.trial.encodingapi;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * User: sini_ann
 * Date: 17/10/20 10:42 am
 */

//Task 1
@Component
public class Encoding {
    private String id;
    private int priority;

    public Encoding(String id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    public Encoding() {
    }

    public String getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<Encoding> get100EncodingList() {
        System.out.println("Creating list of 100 encodings.......");
        List<Encoding> list = new ArrayList<>();
        int i = 1;
        Random r = new Random();
        int low = 1;
        int high = 100;
        while (i <= 100) {
            Encoding encoding = new Encoding(UUID.randomUUID().toString(), r.nextInt(high - low) + low);
            list.add(encoding);
            i++;
        }
        for (Encoding e : list) {
            System.out.println(" Id: " + e.getId() + "    Priority: " + e.getPriority());
        }
        return list;
    }

    public List<Encoding> sortEncodingsByPriority(List<Encoding> list) {
        if (list.size() == 0) {
            throw new IllegalArgumentException("List has no content.");
        }

        System.out.println("Sorting list by descending priority of encodings.......");
        Collections.sort(list, (e1, e2) -> e2.getPriority() - e1.getPriority());
        for (Encoding e : list) {
            System.out.println(" Id: " + e.getId() + "      Priority: " + e.getPriority());
        }
        return list;
    }



}
