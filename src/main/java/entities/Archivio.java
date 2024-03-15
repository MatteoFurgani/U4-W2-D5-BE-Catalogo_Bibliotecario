package entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Archivio {

    private List<Pubblicazione> pubblicazioni;
    public Archivio() {

        pubblicazioni = new ArrayList<>();
    }

    public void aggiungiElemento(Pubblicazione elemento) {

        pubblicazioni.add(elemento);
    }

    public Pubblicazione rimuoviElementoPerISBN(String isbn) throws IOException {
        Optional<Pubblicazione> elementoDaRimuovere = pubblicazioni.stream()
                .filter(p -> p.getIsbn().equals(isbn)).findFirst();

        elementoDaRimuovere.ifPresent(p -> pubblicazioni.remove(p));

        return elementoDaRimuovere.orElse(null);
    }

    public Optional<Pubblicazione> ricercaPerISBN(String isbn) throws IOException {
        return pubblicazioni.stream().filter(p -> p.getIsbn().equals(isbn)).findFirst();
    }

    public List<Pubblicazione> ricercaPerAnnoPubblicazione(int anno)  throws IOException {
        return pubblicazioni.stream().filter(p -> p.getAnnoPubblicazione() == anno).toList();
    }

    public List<Pubblicazione> ricercaPerAutore(String cognome) throws IOException {
        return pubblicazioni.stream().filter(p -> p instanceof Libri).filter(p -> {
            Libri libro = (Libri) p;
            return libro.getAutore().getCognome().equalsIgnoreCase(cognome);
                })
                .toList();
    }

    public void salvataggioSuDisco(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Pubblicazione pubblicazione : pubblicazioni) {
                writer.println(pubblicazione.toString());
            }
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio su disco: " + e.getMessage());
            throw e;
        }
    }

    public void caricamentoDalDisco(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento dal disco: " + e.getMessage());
            throw e;
        }
    }

    public List<Pubblicazione> getPubblicazioni() {
        return pubblicazioni;
    }



}
