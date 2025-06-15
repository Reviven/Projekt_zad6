package com.medapp.service;

import com.medapp.model.Pacjent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AuthService {
    private final Map<String, Pacjent> uzytkownicy = new HashMap<>(); // Simulates a database table

    public boolean zarejestruj(Pacjent pacjent) { // 
        if (uzytkownicy.containsKey(pacjent.getEmail())) {
            return false; // User already exists
        }
        uzytkownicy.put(pacjent.getEmail(), pacjent);
        return true;
    }

    public Optional<Pacjent> zaloguj(String email, String haslo) { // 
        Pacjent pacjent = uzytkownicy.get(email);
        if (pacjent != null && pacjent.getHaslo().equals(haslo)) {
            return Optional.of(pacjent);
        }
        return Optional.empty();
    }
}