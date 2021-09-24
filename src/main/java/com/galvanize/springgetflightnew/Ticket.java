package com.galvanize.springgetflightnew;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {

    @JsonProperty("passenger")
    private com.galvanize.springgetflightnew.Passenger passenger;
    @JsonProperty("price")
    private int price;

    public  Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
