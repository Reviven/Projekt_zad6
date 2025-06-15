package com.medapp.model;

import java.time.LocalDateTime;

public class Wizyta {
    private final String peselPacjenta;
    private final RodzajBadania rodzajBadania;
    private final LocalDateTime termin;
    private final String opis;

    public Wizyta(String peselPacjenta, RodzajBadania rodzajBadania, LocalDateTime termin, String opis) {
        this.peselPacjenta = peselPacjenta;
        this.rodzajBadania = rodzajBadania; //
        this.termin = termin; //
        this.opis = opis; //
    }

    // Getters
    public String getPeselPacjenta() {
        return peselPacjenta;
    }

    public RodzajBadania getRodzajBadania() {
        return rodzajBadania;
    }

    public LocalDateTime getTermin() {
        return termin;
    }
    
    public String getOpis() {
        return opis;
    }
}