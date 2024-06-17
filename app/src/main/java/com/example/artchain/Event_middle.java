package com.example.artchain;

public class Event_middle {
    String event, rules;

    public Event_middle(String event, String rules) {
        this.event = event;
        this.rules = rules;
    }

    public Event_middle(){}

    public String getEvent(){

        return event;
    }

    public String getRules(){

        return rules;
    }

    public void setEvent(String event){

        this.event = event;
    }

    public void setRules(String rules){
        this.rules = rules;
    }
}
