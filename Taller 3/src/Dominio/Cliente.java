
package Dominio;
import Logica.*;

public class Cliente 
{
    private String rut;
    private String nombre;
    private String apellido;
    private int saldo;
    private String ciudad;
    private ListaEntregas le;

    public Cliente(String rut, String nombre, String apellido, int saldo, String ciudad) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.saldo = saldo;
        this.ciudad = ciudad;
        le = new ListaEntregas ();
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ListaEntregas getLe() {
        return le;
    }

    public void setLe(ListaEntregas le) {
        this.le = le;
    }
 
}
