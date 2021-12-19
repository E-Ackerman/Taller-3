
package Dominio;
import Logica.*;


public class Oficina 
{
    private String nombre;
    private ListaClientes lc;
    private ListaEntregas le;

    public Oficina(String nombre) {
        this.nombre = nombre;
        lc = new ListaClientes();
        le = new ListaEntregas ();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public ListaClientes getLc() {
        return lc;
    }

    public void setLc(ListaClientes lc) {
        this.lc = lc;
    }

    public ListaEntregas getLe() {
        return le;
    }

    public void setLe(ListaEntregas le) {
        this.le = le;
    }
    
}
