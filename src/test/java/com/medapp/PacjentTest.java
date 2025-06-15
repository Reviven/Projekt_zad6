package com.medapp;

import com.medapp.model.Pacjent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PacjentTest {

    private Pacjent pacjent1;
    private Pacjent pacjent2; // Taki sam email jak pacjent1
    private Pacjent pacjent3; // Inny email

    @BeforeEach
    void setUp() {
        // Dane na podstawie diagramu klas 
        pacjent1 = new Pacjent("Jan", "Kowalski", "90010112345", "ul. Zdrowa 1", "jan.kowalski@test.pl", "password123");
        pacjent2 = new Pacjent("Adam", "Nowak", "91020223456", "ul. Inna 2", "jan.kowalski@test.pl", "password456");
        pacjent3 = new Pacjent("Jan", "Kowalski", "90010112345", "ul. Zdrowa 1", "inny.email@test.pl", "password123");
    }

    @Test
    void testKonstruktoraIGetters() {
        assertAll("Weryfikacja konstruktora i getterów",
                () -> assertEquals("Jan", pacjent1.getImie()),
                () -> assertEquals("Kowalski", pacjent1.getNazwisko()),
                () -> assertEquals("90010112345", pacjent1.getPesel()),
                () -> assertEquals("jan.kowalski@test.pl", pacjent1.getEmail()), // Login/Mail 
                () -> assertEquals("password123", pacjent1.getHaslo()) // Hasło 
        );
    }

    @Test
    void testEqualsReflexive() {
        assertEquals(pacjent1, pacjent1, "Obiekt powinien być równy samemu sobie.");
    }

    @Test
    void testEqualsSymmetric() {
        // Pacjenci są uznawani za równych na podstawie adresu e-mail
        assertEquals(pacjent1, pacjent2, "Dwa obiekty Pacjent z tym samym adresem e-mail powinny być równe.");
        assertEquals(pacjent2, pacjent1, "Równość powinna być symetryczna.");
    }
    
    @Test
    void testNotEquals() {
        assertNotEquals(pacjent1, pacjent3, "Dwa obiekty Pacjent z różnymi adresami e-mail nie powinny być równe.");
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, pacjent1, "Obiekt nie powinien być równy null.");
    }
    
    @Test
    void testHashCodeConsistency() {
        // Pacjenci z tym samym e-mailem powinni mieć ten sam hashCode
        assertEquals(pacjent1.hashCode(), pacjent2.hashCode(), "Równe obiekty powinny mieć ten sam hashCode.");
        assertNotEquals(pacjent1.hashCode(), pacjent3.hashCode(), "Nierówne obiekty powinny mieć różne hashCode.");
    }
}