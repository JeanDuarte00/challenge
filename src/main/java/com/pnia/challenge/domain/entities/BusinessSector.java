package com.pnia.challenge.domain.entities;

import java.io.Serializable;

public final class  BusinessSector implements Serializable {

    private final String number;
    private final String sector;

    public BusinessSector(String number, String sector) {
        this.number = number;
        this.sector = sector;
    }

    public String getNumber() {
        return number;
    }

    public String getSector() {
        return sector;
    }
}
