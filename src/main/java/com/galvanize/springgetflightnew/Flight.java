package com.galvanize.springgetflightnew;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Flight {
    @JsonProperty("Departs")
    private String departs;
    @JsonProperty("Ticket")
    private ArrayList<Ticket> ticketList;


    void setFormattedDate(LocalDateTime date){
        this.departs =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH).format(date);
    }

    public String getDeparts() {
        return departs;
    }

    public ArrayList<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(ArrayList<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}

