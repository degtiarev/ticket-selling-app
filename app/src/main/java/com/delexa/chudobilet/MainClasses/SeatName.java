package com.delexa.chudobilet.MainClasses;

/**
 * Created by delexa on 25.05.16.
 */
public class SeatName {

    String sector;
    String row;
    String seat;


    public SeatName() {
    }

    public SeatName(String sector, String row, String seat) {
        this.sector = sector;
        this.row = row;
        this.seat = seat;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
