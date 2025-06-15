package com.medapp;

import com.medapp.model.Pacjent;
import com.medapp.model.RodzajBadania;
import com.medapp.model.Wizyta;
import com.medapp.service.HarmonogramService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HarmonogramServiceTest {

    private HarmonogramService harmonogramService;
    private Pacjent pacjent1;
    private Pacjent pacjent2;
    private LocalDateTime termin1;
    private LocalDateTime termin2;

    @BeforeEach
    void setUp() {
        harmonogramService = new HarmonogramService();
        pacjent1 = new Pacjent("Anna", "Nowak", "85050567890", "ul. Kwiatowa 5", "anna.nowak@test.pl", "pass");
        pacjent2 = new Pacjent("Piotr", "Zieliński", "88080811223", "ul. Leśna 10", "piotr.zielinski@test.pl", "pass");

        termin1 = LocalDateTime.of(2025, 10, 20, 10, 0);
        termin2 = LocalDateTime.of(2025, 10, 20, 11, 0);
    }

    @Test
    void testUmowWizyteWwolnymTerminie() {
        // Pacjent planuje badanie i umawia wizytę 
        boolean czyUmowiono = harmonogramService.umowWizyte(pacjent1, RodzajBadania.LABORATORYJNE, termin1, "Morfologia krwi");
        assertTrue(czyUmowiono, "Powinno udać się umówić wizytę w wolnym terminie.");

        List<Wizyta> wizyty = harmonogramService.getWizytyPacjenta(pacjent1);
        assertEquals(1, wizyty.size(), "Pacjent powinien mieć jedną umówioną wizytę.");
        assertEquals(termin1, wizyty.get(0).getTermin());
    }

    @Test
    void testUmowWizyteWzajetymTerminie() {
        // Pierwszy pacjent zajmuje termin
        harmonogramService.umowWizyte(pacjent1, RodzajBadania.LABORATORYJNE, termin1, "Morfologia krwi");

        // Drugi pacjent próbuje umówić wizytę w tym samym terminie
        boolean czyUmowiono = harmonogramService.umowWizyte(pacjent2, RodzajBadania.MEDYCZNE, termin1, "Konsultacja");
        assertFalse(czyUmowiono, "Nie powinno udać się umówić wizyty w już zajętym terminie.");
    }

    @Test
    void testPobieraniaWizytDlaKonkretnegoPacjenta() {
        harmonogramService.umowWizyte(pacjent1, RodzajBadania.LABORATORYJNE, termin1, "Morfologia");
        harmonogramService.umowWizyte(pacjent2, RodzajBadania.MEDYCZNE, termin2, "RTG");

        List<Wizyta> wizytyPacjenta1 = harmonogramService.getWizytyPacjenta(pacjent1);
        assertEquals(1, wizytyPacjenta1.size(), "Pacjent 1 powinien mieć tylko jedną wizytę.");
        assertEquals(termin1, wizytyPacjenta1.get(0).getTermin());

        List<Wizyta> wizytyPacjenta2 = harmonogramService.getWizytyPacjenta(pacjent2);
        assertEquals(1, wizytyPacjenta2.size(), "Pacjent 2 powinien mieć tylko jedną wizytę.");
        assertEquals(termin2, wizytyPacjenta2.get(0).getTermin());
    }

    @Test
    void testAnulowaniaWizyty() {
        harmonogramService.umowWizyte(pacjent1, RodzajBadania.INNE, termin1, "Test alergiczny");
        
        boolean czyAnulowano = harmonogramService.anulujWizyte(pacjent1, termin1);
        assertTrue(czyAnulowano, "Anulowanie istniejącej wizyty pacjenta powinno się powieść.");
        
        List<Wizyta> wizyty = harmonogramService.getWizytyPacjenta(pacjent1);
        assertTrue(wizyty.isEmpty(), "Pacjent nie powinien mieć żadnych wizyt po anulowaniu.");
    }

    @Test
    void testAnulowaniaWizytyInnegoPacjenta() {
        harmonogramService.umowWizyte(pacjent1, RodzajBadania.LABORATORYJNE, termin1, "Morfologia");

        boolean czyAnulowano = harmonogramService.anulujWizyte(pacjent2, termin1);
        assertFalse(czyAnulowano, "Nie powinno się udać anulować wizyty należącej do innego pacjenta.");

        List<Wizyta> wizyty = harmonogramService.getWizytyPacjenta(pacjent1);
        assertEquals(1, wizyty.size(), "Wizyta pacjenta 1 powinna nadal istnieć.");
    }

    @Test
    void testAnulowaniaNieistniejacejWizyty() {
        boolean czyAnulowano = harmonogramService.anulujWizyte(pacjent1, termin1);
        assertFalse(czyAnulowano, "Próba anulowania wizyty w terminie, w którym nic nie jest zaplanowane, powinna zwrócić false.");
    }

    @Test
    void testPonownegoUzyciaZwolnionegoTerminu() {
        harmonogramService.umowWizyte(pacjent1, RodzajBadania.LABORATORYJNE, termin1, "Morfologia");
        harmonogramService.anulujWizyte(pacjent1, termin1);

        boolean czyUmowionoPonownie = harmonogramService.umowWizyte(pacjent2, RodzajBadania.MEDYCZNE, termin1, "Konsultacja");
        assertTrue(czyUmowionoPonownie, "Powinno udać się zająć termin, który został zwolniony.");

        List<Wizyta> wizytyPacjenta2 = harmonogramService.getWizytyPacjenta(pacjent2);
        assertEquals(1, wizytyPacjenta2.size());
        assertEquals(termin1, wizytyPacjenta2.get(0).getTermin());
    }
}