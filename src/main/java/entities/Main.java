package entities;

import com.github.javafaker.Faker;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Faker faker = new Faker();

        // Creo 3 autori FISSI
        Autore autore1 = new Autore("Mario", "Rossi");
        Autore autore2 = new Autore("Carla", "Verdi");
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

        // Creazione di 5 riviste
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
        archivio.rimuoviElementoPerISBN(isbnDaRimuovere);


    }
}
