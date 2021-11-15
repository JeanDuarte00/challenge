package com.pnia.challenge.domain.entities;

import java.io.Serializable;
import java.util.Objects;

public final class  BusinessSector implements Serializable {

    private String number = "";
    private String sector = "";

    public BusinessSector(String number, String sector) {
        if (!Objects.isNull(number) && !Objects.isNull(sector)) {
            this.number = number;
            this.sector = sector;
        }
    }

    public String getNumber() {
        return number;
    }

    public String getSector() {
        return sector;
    }
}
