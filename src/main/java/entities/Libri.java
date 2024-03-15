package entities;

public class Libri extends Pubblicazione{

    private String autore;
    private String genere;

    public Libri(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Autore autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore.getNome() + " " + autore.getCognome();
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(Autore autore) {
        this.autore = autore.getNome() + " " + autore.getCognome();
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
