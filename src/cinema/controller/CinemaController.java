package cinema.controller;

import cinema.entity.AvailableSeats;
import cinema.entity.Cinema;
import cinema.entity.CinemaStats;
import cinema.entity.Ticket;
import cinema.response.PasswordNotFoundException;
import cinema.response.SeatsNotFoundException;
import cinema.response.TokenNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CinemaController {

    private CinemaStats cinemaStats;

    private final List<AvailableSeats> availableSeats;
    private final ConcurrentHashMap<String, Map<String, AvailableSeats>> generatedTokensWithTicket;

    public CinemaController() {
        availableSeats = Collections.synchronizedList(new LinkedList<>());
        generatedTokensWithTicket = new ConcurrentHashMap<>();

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                availableSeats.add(new AvailableSeats(i, j));
            }
        }

        cinemaStats = new CinemaStats(0, 0, 0);
    }

    @GetMapping("/seats")
    public ResponseEntity<Cinema> getSeats() {
        return new ResponseEntity<>(new Cinema(9, 9, availableSeats), HttpStatus.OK);
    }
    
    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseTicket(@RequestBody AvailableSeats seat) {

        if (seat.getRow() < 0 || seat.getRow() > 9 || seat.getColumn() < 0 || seat.getColumn() > 9) {
            return new ResponseEntity<>(new SeatsNotFoundException("The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }

        boolean isSeatPresent = availableSeats.removeIf(posto ->
                posto.getRow().equals(seat.getRow()) && posto.getColumn().equals(seat.getColumn()));

        if (isSeatPresent) {
            Ticket ticket = new Ticket(seat);

            generatedTokensWithTicket.put(ticket.getToken(), Collections.singletonMap("returned_ticket", ticket.getTicket()));

            cinemaStats.setCurrentIncome(ticket.getTicket().getPrice());
            cinemaStats.incrementPurchasedTickets();

            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }

        return new ResponseEntity<>(new SeatsNotFoundException("The ticket has been already purchased!"),
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/return")
    public ResponseEntity<Object> refundTicketByToken(@RequestBody Ticket token) {



        if (generatedTokensWithTicket.containsKey(token.getToken())) {

            Map<String, AvailableSeats> seatToRefundFromToken = generatedTokensWithTicket.get(token.getToken());
            AvailableSeats returnedTicket = seatToRefundFromToken.get("returned_ticket");

            cinemaStats.decrementCurrentIncome(returnedTicket.getPrice());
            cinemaStats.decrementPurchasedTickets();
            availableSeats.add(returnedTicket.getRow() * returnedTicket.getColumn() - 1,
                    new AvailableSeats(returnedTicket.getRow(), returnedTicket.getColumn()));

            return new ResponseEntity<>(generatedTokensWithTicket.get(token.getToken()), HttpStatus.OK);
        }

        return new ResponseEntity<>(new TokenNotFoundException("Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<Object> returnStatsFromCinema(@RequestParam(value = "password", required = false) String password) {
        if ("super_secret".equals(password)) {

            cinemaStats = new CinemaStats(cinemaStats.getCurrentIncome(), availableSeats.size(), cinemaStats.getNumberOfPurchasedTickets());

            return new ResponseEntity<>(cinemaStats,
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(new PasswordNotFoundException("The password is wrong!"), HttpStatus.valueOf(401));
    }
}