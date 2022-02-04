package cinema.entity;

public class CinemaStats {

    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;

    public CinemaStats(int currentIncome, int numberOfAvailableSeats, int numberOfPurchasedTickets) {
        this.currentIncome = currentIncome;
        this.numberOfAvailableSeats = numberOfAvailableSeats;
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    public CinemaStats(){}

    public int getCurrentIncome() {
        return currentIncome;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome += currentIncome;
    }

    public void decrementCurrentIncome(int currentIncome) {
        this.currentIncome -= currentIncome;
    }

    public void setNumberOfPurchasedTickets(int numberOfPurchasedTickets) {
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    public void setNumberOfAvailableSeats(int numberOfAvailableSeats) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    public void incrementPurchasedTickets() {
        this.numberOfPurchasedTickets++;
    }

    public void decrementPurchasedTickets() {
        this.numberOfPurchasedTickets--;
    }
}
