package cinema.entity;

import java.util.ArrayList;
import java.util.List;


public class Cinema {

    public int totalRows;
    public int totalColumns;
    public List<AvailableSeats> availableSeats;

    public Cinema(int totalRows, int totalColumns, List<AvailableSeats> availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = availableSeats;
    }

    public int getTotalRows() {
        return this.totalRows;
    }

    public int getTotalColumns() {
        return this.totalColumns;
    }

    public List<AvailableSeats> getAvailableSeats() {
        return this.availableSeats;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public void setAvailableSeats(List<AvailableSeats> availableSeats) {
        this.availableSeats = availableSeats;
    }

}
