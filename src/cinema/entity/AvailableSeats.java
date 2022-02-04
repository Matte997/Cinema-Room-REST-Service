package cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailableSeats {

    private Integer row;
    private Integer column;
    private Integer price;

    public AvailableSeats(Integer row, Integer column) {
        this.row = row;
        this.column = column;
        this.price = getPrice();
    }

    public AvailableSeats(){}

    public Integer getRow() {
        return this.row;
    }

    public Integer getColumn() {
        return this.column;
    }

    public Integer getPrice() {
        return row <= 4 ? 10 : 8;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
