package com.medapp.model;

import java.time.LocalDate;

public abstract class DokumentMedyczny {
    private String nazwa;
    private LocalDate dataUtworzenia;

    public DokumentMedyczny(String nazwa, LocalDate dataUtworzenia) {
        this.nazwa = nazwa;
        this.dataUtworzenia = dataUtworzenia;
    }

    public String getNazwa() { return nazwa; }
    public LocalDate getDataUtworzenia() { return dataUtworzenia; }

    public void drukuj(String format) {
        System.out.println("Drukowanie dokumentu: " + nazwa + " w formacie " + format); // 
    }

    public void pobierz(String format) {
        System.out.println("Pobieranie dokumentu: " + nazwa + " jako " + format); // 
    }
}