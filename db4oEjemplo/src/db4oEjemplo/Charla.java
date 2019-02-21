package db4oEjemplo;

/**
 *
 * @author windiurno
 */
public class Charla {
    private String titulo;
    private float duracion;
    private Ponente p;

    public Charla(String titulo, float duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.p = null;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public Ponente getP() {
        return p;
    }

    public void setP(Ponente p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "Charla{" + "titulo=" + titulo + ", duracion=" + duracion + ", p=" + p + '}';
    }
    
    
    
}
