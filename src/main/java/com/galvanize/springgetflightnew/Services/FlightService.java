package com.galvanize.springgetflightnew.Services;

import com.galvanize.springgetflightnew.Flight;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightService {

    public static ArrayList<Flight> flightsList;

    public static ArrayList<Flight> getFlightsList() {
        return flightsList;
    }

    public static void setFlightsList(ArrayList<Flight> flightsList) {
        FlightService.flightsList = flightsList;
    }

    public static int calculateTotals() {
        int total = 0;
        for (Flight flight : flightsList) {
            for (int j = 0; j < flight.getTicketList().size(); j++) {
                total += flight.getTicketList().get(j).getPrice();
            }
        }
        return total;
    }
}
