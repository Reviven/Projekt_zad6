package com.medapp;

import com.medapp.model.*;
import com.medapp.service.DokumentyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DokumentyServiceTest {

    private DokumentyService dokumentyService;
    private Pacjent pacjent1;
    private WynikBadania wynikBadaniaA;
    private WynikBadania wynikBadaniaB;
    private Recepta recepta;

    @BeforeEach
    void setUp() {
        dokumentyService = new DokumentyService();
        pacjent1 = new Pacjent("Anna", "Nowak", "85050567890", "ul. Kwiatowa 5", "anna.nowak@test.pl", "pass");

        wynikBadaniaA = new WynikBadania("Morfologia", LocalDate.of(2025, 5, 20), RodzajBadania.LABORATORYJNE, null);
        wynikBadaniaB = new WynikBadania("RTG płuc", LocalDate.of(2025, 3, 15), RodzajBadania.MEDYCZNE, null);
        recepta = new Recepta("Recepta na antybiotyk", LocalDate.of(2025, 5, 21), LocalDate.now().plusMonths(1), Collections.emptyList());

        dokumentyService.dodajDokument(pacjent1, wynikBadaniaA);
        dokumentyService.dodajDokument(pacjent1, wynikBadaniaB);
        dokumentyService.dodajDokument(pacjent1, recepta);
    }

    @Test
    void testPobieraniaWszystkichDokumentow() {
        List<DokumentMedyczny> dokumenty = dokumentyService.getDokumenty(pacjent1);
        assertEquals(3, dokumenty.size(), "Pacjent powinien mieć 3 dokumenty medyczne.");
    }

    @Test
    void testFiltrowaniaDokumentowPoTypie() {
        List<WynikBadania> wyniki = dokumentyService.getDokumentyFiltrowane(pacjent1, WynikBadania.class);
        assertEquals(2, wyniki.size(), "Pacjent powinien mieć 2 wyniki badań.");
        assertTrue(wyniki.contains(wynikBadaniaA));
    }
    
    @Test
    void testSortowaniaPoDacie() {
        List<DokumentMedyczny> posortowane = dokumentyService.sortuj(pacjent1, "data");
        assertEquals("Recepta na antybiotyk", posortowane.get(0).getNazwa(), "Pierwszy dokument po sortowaniu datą malejąco powinien być najnowszy.");
        assertEquals("RTG płuc", posortowane.get(2).getNazwa(), "Ostatni dokument powinien być najstarszy.");
    }
    
    @Test
    void testSortowaniaPoNazwie() {
        List<DokumentMedyczny> posortowane = dokumentyService.sortuj(pacjent1, "nazwa");
        assertEquals("Morfologia", posortowane.get(0).getNazwa(), "Pierwszy dokument po sortowaniu nazwą alfabetycznie powinien być 'Morfologia'.");
        assertEquals("RTG płuc", posortowane.get(2).getNazwa(), "Ostatni dokument powinien być 'RTG płuc'.");
    }
}