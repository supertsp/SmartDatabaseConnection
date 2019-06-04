package smartdb;

/**
 *
 * @author tiago penha pedroso
 */
public class FilmeFavorito {
    
    private int idFilmeFavorito;
    private String titulo;
    private String genero;

    public FilmeFavorito() {
        super();
    }

    public FilmeFavorito(int idFilmeFavorito, String titulo, String genero) {
        this.idFilmeFavorito = idFilmeFavorito;
        this.titulo = titulo;
        this.genero = genero;
    }
    
    public int getIdFilmeFavorito() {
        return idFilmeFavorito;
    }

    public void setIdFilmeFavorito(int idFilmeFavorito) {
        this.idFilmeFavorito = idFilmeFavorito;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "[" + idFilmeFavorito + ", \"" + titulo + "\", \"" + genero + "\"]";
    }
    
    
    
}
