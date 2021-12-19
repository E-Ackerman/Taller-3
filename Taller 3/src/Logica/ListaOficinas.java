
package Logica;
import Dominio.*;

public class ListaOficinas
{
    private Oficina[] lista;
    private int cant;
    private int max;
    
    public ListaOficinas (int max) 
    {
        lista = new Oficina[max];
        this.max = max;
        this.cant = 0;    
    }
    
    public boolean ingresarOficina(Oficina o) 
    {
        if(cant<max) 
        {
            lista[cant] = o;
            cant++;
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public Oficina getI(int i) 
    {
        if(i < cant) 
        {
            return lista[i];
        }
        else 
        {
            return null;
        }
    }
    
    public Oficina buscarNombre (String nombre) 
    {
        int i;
        for(i=0;i<cant;i++) 
        {
            if(lista[i].getNombre().equals(nombre)) 
            {
                break;
            }
        }
        if(i==cant) 
        {
            return null;
        }
        else 
        {
            return lista[i];
        }
    }

    public Oficina[] getLista() {
        return lista;
    }

    public void setLista(Oficina[] lista) {
        this.lista = lista;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
}
