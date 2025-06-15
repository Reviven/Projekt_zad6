package com.medapp.service;

import com.medapp.model.DokumentMedyczny;
import com.medapp.model.Pacjent;
import java.util.*;
import java.util.stream.Collectors;

public class DokumentyService {
    // Simulates a database, mapping patient's PESEL to their list of documents
    private final Map<String, List<DokumentMedyczny>> dokumentyPacjentow = new HashMap<>();

    public void dodajDokument(Pacjent pacjent, DokumentMedyczny dokument) {
        dokumentyPacjentow
            .computeIfAbsent(pacjent.getPesel(), k -> new ArrayList<>())
            .add(dokument);
    }

    public List<DokumentMedyczny> getDokumenty(Pacjent pacjent) {
        return dokumentyPacjentow.getOrDefault(pacjent.getPesel(), Collections.emptyList());
    }
    
    public <T extends DokumentMedyczny> List<T> getDokumentyFiltrowane(Pacjent pacjent, Class<T> typ) {
        return getDokumenty(pacjent).stream()
            .filter(typ::isInstance)
            .map(typ::cast)
            .collect(Collectors.toList());
    }

    public List<DokumentMedyczny> sortuj(Pacjent pacjent, String kryterium) { // 
        List<DokumentMedyczny> dokumenty = new ArrayList<>(getDokumenty(pacjent));
        switch (kryterium.toLowerCase()) {
            case "data": // 
                dokumenty.sort(Comparator.comparing(DokumentMedyczny::getDataUtworzenia).reversed());
                break;
            case "nazwa": // 
                dokumenty.sort(Comparator.comparing(DokumentMedyczny::getNazwa));
                break;
        }
        return dokumenty;
    }
}