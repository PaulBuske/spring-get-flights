package com.galvanize.springgetflightnew;

import com.galvanize.springgetflightnew.Controller.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlightController.class)
class DemoApplicationTests {

    @Autowired
    private MockMvc mvc;
    private final String date = "2017-04-21 14:34";
    private final String date1 = "2017-04-21 12:34";


    @BeforeAll
    static void setup() {
        com.galvanize.springgetflightnew.DemoApplication.generateFakeFlights();
    }

    @Test
    void flightReturnsJSONFlight() throws Exception {
        MockHttpServletRequestBuilder req = get("/flights/flight")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Departs", is(this.date1)));
    }

    @Test
    void flightReturnsJSONFlights() throws Exception {
        MockHttpServletRequestBuilder req = get("/flights")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Departs", is(this.date)));
    }

    @Test
    void nullNamesAreNotRendered() throws Exception {
        MockHttpServletRequestBuilder req = get("/flights")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].Tickets[0].Passenger.FirstName").doesNotExist());
    }


    @Test
    void postAStringLiteral() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                        "tickets": [
                        		  {
                        				"passenger": {
                        			  "firstName": "Some name",
                        			  "lastName": "Some other name"
                        			},
                        			"price": 200
                        		  },
                        		  {
                        			"passenger": {
                        			  "firstName": "Name B",
                        			  "lastName": "Name C"
                        			},
                        			"price": 150
                          		}
                        ]
                         }
                        				""");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    @Test
    void POSTsJSONDataBySerializingWithJackson() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights/flight")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"Departs\" : \"2017-04-21 12:34\",\"Ticket\":[{\"passenger\" : null,\"price\": " +
                        "0},{\"passenger\" : null, \"price\" : 0}]}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Departs", is("2017-04-21 12:34")));
    }

    @Test
    void sendsJSONDataByPullingFromAFIleFixture() throws  Exception{
        String json = getJSON("/data.json");

        MockHttpServletRequestBuilder request = post("/flights/flight")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }



}