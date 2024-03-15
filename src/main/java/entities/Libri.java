package entities;

public class Libri extends Pubblicazione{

    private String autore;
    private String genere;

    public Libri(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Autore autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = String.valueOf(autore);
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(Autore autore) {
        this.autore = String.valueOf(autore);
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
