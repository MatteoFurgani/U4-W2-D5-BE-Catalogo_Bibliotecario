package entities;

import com.github.javafaker.Faker;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Faker faker = new Faker();

        // Creo 3 autori FISSI
        Autore autore1 = new Autore("Mario", "Verdi");
        Autore autore2 = new Autore("Carla", "Rossi");
        Autore autore3 = new Autore("Paola", "Bianchi");
        Autore autore = null;

        // Creo 5 libri
        for (int i = 0; i < 5; i++) {
            String isbn = "ISBN_Libro_" + (i + 1);
            String titolo = faker.book().title();
            int annoPubblicazione = 2024 - i;
            int numeroPagine = 300 + i;
            String genere = faker.book().genre();
            autore = (i % 3 == 0) ? autore1 : ((i % 3 == 1) ? autore2 : autore3);

            Libri libro = new Libri(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);
            archivio.aggiungiElemento(libro);
        }

        // Creo di 5 riviste
        for (int i = 0; i < 5; i++) {
            String isbn = "ISBN_Rivista_" + (i + 1);
            String titolo = faker.book().title();
            int annoPubblicazione = 2020 - i;
            int numeroPagine = 50 + i;
            Periodicita periodicita = Periodicita.values()[i % Periodicita.values().length];

            Riviste rivista = new Riviste(isbn, titolo, annoPubblicazione, numeroPagine, periodicita);
            archivio.aggiungiElemento(rivista);
        }


        //RICERCA TRAMITE ISBN
        System.out.println("----RICERCA PER ISBN----");
        String isbnDaRicercare = "ISBN_Libro_3";
        Optional<Pubblicazione> pubblicazioneByISBN = archivio.ricercaPerISBN(isbnDaRicercare);
        if (pubblicazioneByISBN.isPresent()) {
            System.out.println("Pubblicazione trovata: " + pubblicazioneByISBN.get().getTitolo());
        } else {
            System.out.println("Nessuna pubblicazione trovata con l'ISBN: " + isbnDaRicercare);
        }

        //RIMUOVI TRAMITE ISBN
        System.out.println("       ");
        System.out.println("----RIMUOVI PER ISBN----");
        String isbnDaRimuovere = "ISBN_Libro_3";
        System.out.println("Rimuovendo l'elemento con ISBN: " + isbnDaRimuovere);
        Pubblicazione rimozioneAvvenuta = archivio.rimuoviElementoPerISBN(isbnDaRimuovere);
        if (rimozioneAvvenuta!= null) {
            System.out.println("Cancellazione avvenuta!");
        } else {
            System.out.println("Errore durante la cancellazione.");
        }

        //RICERCA TRAMITE ANNO PUBBLICAZIONE
        System.out.println("       ");
        System.out.println("----RICERCA PER ANNO DI PUBBLICAZIONE----");
        int annoDaRicercare = 2023;
        System.out.println("Ricerca per anno di pubblicazione: " + annoDaRicercare);
        List<Pubblicazione> pubblicazioniAnno = archivio.ricercaPerAnnoPubblicazione(annoDaRicercare);
        System.out.println("Pubblicazioni dell'anno " + annoDaRicercare + ":");
        for (Pubblicazione pubblicazione : pubblicazioniAnno) {
            System.out.println(pubblicazione.getTitolo());
        }

        //RICERCA TRAMITE AUTORE
        System.out.println("       ");
        System.out.println("----RICERCA PER AUTORE----");
        List<Pubblicazione> pubblicazioniAutore = archivio.ricercaPerAutore(autore.getCognome());
        System.out.println("Pubblicazioni dell'autore " + autore.getCognome() + ":");
        for (Pubblicazione pubblicazione : pubblicazioniAutore) {
            if (pubblicazione instanceof Libri) {
                Libri libro = (Libri) pubblicazione;
                System.out.println("Titolo del libro: " + libro.getTitolo());
            }
        }

        //SALVATAGGIO SU DISCO
        System.out.println("       ");
        System.out.println("----SALVATAGGIO SU DISCO----");
        try {
            archivio.salvataggioSuDisco("archivio.txt");
            System.out.println("Archivio salvato su disco.");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio su disco: " + e.getMessage());
        }

        //CARICAMENTO DA DISCO
        System.out.println("       ");
        System.out.println("----CARICAMENTO DA DISCO----");
        try {
            archivio.caricamentoDalDisco("archivio.txt");
            System.out.println("Archivio caricato da disco.");
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento da disco: " + e.getMessage());
        }



    }
}
