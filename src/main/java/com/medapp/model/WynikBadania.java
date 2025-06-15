package com.medapp.model;

import java.time.LocalDate;

public class WynikBadania extends DokumentMedyczny {
    private RodzajBadania rodzajBadania;
    private LocalDate dataNastepnejWizyty;

    public WynikBadania(String nazwa, LocalDate dataUtworzenia, RodzajBadania rodzaj, LocalDate dataNastepnejWizyty) {
        super(nazwa, dataUtworzenia);
        this.rodzajBadania = rodzaj;
        this.dataNastepnejWizyty = dataNastepnejWizyty; // 
    }
    
    public RodzajBadania getRodzajBadania() { return rodzajBadania; }
}