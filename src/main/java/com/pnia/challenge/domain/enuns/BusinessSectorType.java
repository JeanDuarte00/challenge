package com.pnia.challenge.domain.enuns;

public enum BusinessSectorType {

    TECHNOLOGY("Technology"),
    BANKING("Banking"),
    CLOTHING("Clothing");

    private final String value;

    BusinessSectorType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
