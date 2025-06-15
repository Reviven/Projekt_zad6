package com.medapp.model;

import java.util.Objects;

public class Pacjent {
    private String imie;
    private String nazwisko;
    private String pesel;
    private String adres;
    private String email; // Serves as the login 
    private String haslo;

    public Pacjent(String imie, String nazwisko, String pesel, String adres, String email, String haslo) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.adres = adres;
        this.email = email;
        this.haslo = haslo;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public String getHaslo() { return haslo; }
    public String getPesel() { return pesel; }
    public String getImie() { return imie; }
    public String getNazwisko() { return nazwisko; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pacjent pacjent = (Pacjent) o;
        return Objects.equals(email, pacjent.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}