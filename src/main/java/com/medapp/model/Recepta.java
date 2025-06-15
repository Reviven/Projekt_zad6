package com.medapp.model;

import java.time.LocalDate;
import java.util.List;

public class Recepta extends DokumentMedyczny {
    private LocalDate terminWaznosci;
    private boolean czyWykorzystana;
    private List<Lek> przypisaneLeki;

    public Recepta(String nazwa, LocalDate dataUtworzenia, LocalDate terminWaznosci, List<Lek> leki) {
        super(nazwa, dataUtworzenia);
        this.terminWaznosci = terminWaznosci;
        this.czyWykorzystana = false; // 
        this.przypisaneLeki = leki; // 
    }

    public boolean isCzyWykorzystana() { return czyWykorzystana; }
    public void setCzyWykorzystana(boolean czyWykorzystana) { this.czyWykorzystana = czyWykorzystana; }
}