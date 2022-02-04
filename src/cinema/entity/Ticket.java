package cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

    private String token;
    private AvailableSeats ticket;

    public Ticket(AvailableSeats seat) {
        this.token = UUID.randomUUID().toString();
        this.ticket = seat;
    }

    public Ticket(){}

    public String getToken() {
        return token;
    }

    public AvailableSeats getTicket() {
        return ticket;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTicket(AvailableSeats ticket) {
        this.ticket = ticket;
    }
}
