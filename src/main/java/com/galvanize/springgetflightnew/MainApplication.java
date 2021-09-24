package com.galvanize.springgetflightnew;

import com.galvanize.springgetflightnew.Services.FlightService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

@SpringBootApplication
class DemoApplication {

    public static void generateFakeFlights() {
        LocalDateTime ldt = LocalDateTime.of(2017, 4, 21, 14, 34);
        LocalDateTime ldt1 = LocalDateTime.of(2017, 4, 21, 12, 34);

        //creates the first flight and ticket listing
        Passenger passenger = new Passenger();
        passenger.setFirstName("Trevor");
        passenger.setLastName("Nova");

        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setPrice(200);

        ArrayList<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);

        Flight flight = new Flight();
        flight.setFormattedDate(ldt);
        flight.setTicketList(ticketList);

        //creates the second flight and ticket listing

        Passenger passenger1 = new Passenger();
        passenger1.setFirstName("Bill");
        passenger1.setLastName("Mill");

        Ticket ticket1 = new Ticket();
        ticket.setPassenger(passenger1);
        ticket.setPrice(350);

        ArrayList<Ticket> ticketList1 = new ArrayList<>();
        ticketList1.add(ticket1);

        Flight flight1 = new Flight();
        flight1.setFormattedDate(ldt1);
        flight1.setTicketList(ticketList1);

        //creates the third flight and ticket listing
        Passenger passenger3 = new Passenger();
        passenger1.setFirstName("Pauly");
        passenger1.setLastName("Shore");

        Ticket ticket3 = new Ticket();
        ticket3.setPassenger(passenger3);
        ticket3.setPrice(200);

        ArrayList<Ticket> ticketList3 = new ArrayList<>();
        ticketList1.add(ticket1);

        Flight flight3 = new Flight();
        flight3.setFormattedDate(ldt);
        flight3.setTicketList(ticketList3);

        ArrayList<Flight> flightList = new ArrayList<>();
        flightList.add(flight);
        flightList.add(flight1);
        flightList.add(flight3);

        FlightService.setFlightsList(flightList);


    }

    public static void main(String[] args) {
        generateFakeFlights();
        SpringApplication.run(DemoApplication.class, args);
    }

}
