package com.galvanize.springgetflightnew.Controller;

import com.galvanize.springgetflightnew.Flight;
import com.galvanize.springgetflightnew.Services.FlightService;

import com.galvanize.springgetflightnew.Ticket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
public class FlightController {

    @GetMapping("/flights")
    public Object getFlights() {
        return FlightService.getFlightsList();
    }

    @GetMapping("/flights/flight")
    public Object getFlight() {

        return FlightService.getFlightsList().get(1);
    }

    @PostMapping("/flights/tickets/total")
    public Object getTotalCost(@RequestBody  FlightService flightService) throws Exception {

        HashMap<String, Object> data = new HashMap<>();
        data.put("result", FlightService.calculateTotals());
        return data;
    }

    @PostMapping("/flights/flight")
    public Object returnPostJSONLiteral(@RequestBody Flight flight) throws Exception{

    return flight;
    }
}
