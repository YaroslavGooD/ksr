package com.classification;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Reut {
    private int id;
    private boolean isTopic;
    private Date date;
    private String topic;
    private String people;
    private String orgs;
    private String exchanges;
    private String company;
    private String unknown;
    private String text;


    public Reut(int id, boolean isTopic, Date date,
                String topic, String people, String orgs,
                String exchanges, String company, String unknown,
                String text) {
        this.id = id;
        this.isTopic = isTopic;
        this.date = date;
        this.topic = topic;
        this.people = people;
        this.orgs = orgs;
        this.exchanges = exchanges;
        this.company = company;
        this.unknown = unknown;
        this.text = text;
    }
}
