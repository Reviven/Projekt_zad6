package com.medapp;

import com.medapp.model.Pacjent;
import com.medapp.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private AuthService authService;
    private Pacjent pacjent;

    @BeforeEach
    void setUp() {
        authService = new AuthService();
        pacjent = new Pacjent("Jan", "Kowalski", "90010112345", "ul. Zdrowa 1", "jan.kowalski@test.pl", "password123");
    }

    @Test
    void testUdanejRejestracji() {
        assertTrue(authService.zarejestruj(pacjent), "Rejestracja nowego pacjenta powinna się udać.");
    }

    @Test
    void testRejestracjiIstniejacegoUzytkownika() {
        authService.zarejestruj(pacjent);
        assertFalse(authService.zarejestruj(pacjent), "Ponowna rejestracja tego samego pacjenta nie powinna być możliwa.");
    }

    @Test
    void testUdanegoLogowania() {
        authService.zarejestruj(pacjent);
        var zalogowany = authService.zaloguj("jan.kowalski@test.pl", "password123");
        assertTrue(zalogowany.isPresent(), "Logowanie z poprawnymi danymi powinno się udać.");
        assertEquals("Jan", zalogowany.get().getImie());
    }

    @Test
    void testLogowaniaNiepoprawnymHaslem() {
        authService.zarejestruj(pacjent);
        var zalogowany = authService.zaloguj("jan.kowalski@test.pl", "wrongpassword");
        assertFalse(zalogowany.isPresent(), "Logowanie z błędnym hasłem powinno się nie udać.");
    }

    @Test
    void testLogowaniaNieistniejacegoUzytkownika() {
        var zalogowany = authService.zaloguj("nie.istnieje@test.pl", "password123");
        assertFalse(zalogowany.isPresent(), "Logowanie na nieistniejące konto powinno się nie udać.");
    }
}