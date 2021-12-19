
package Logica;

import Dominio.*;


public class ListaClientes 
{
    private Nodo<Cliente> first;
    private int cant;
    
    public ListaClientes () 
    {
        this.first = null;
        this.cant = 0;
    }
    
    public boolean ingresarCliente(Cliente c) 
    {
        Nodo<Cliente> nuevo = new Nodo(c);
        nuevo.setNext(first);
        first = nuevo;
        cant++;
        return true;
    }
    
    public Cliente getI (int index) 
    {
        Nodo<Cliente> current = first;
        int i;
        for(i=0;i<cant;i++) 
        {
            if(index == i) 
            {
                break;
            }
            else 
            {
                current = current.getNext();
            }
        }
        
        if(i==cant) 
        {
            return null;
        }
        else 
        {
            return current.getDato();
        }
    }
    
    public Cliente buscarRut (String rut) 
    {
        Nodo<Cliente> current = first;
        int i;
        for(i=0;i<cant;i++) 
        {
            if(current.getDato().getRut().equals(rut)) 
            {
                break;
            }
            else 
            {
                current = current.getNext();
            }
        }
        
        if(i==cant) 
        {
            return null;
        }
        else 
        {
            return current.getDato();
        }
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
    
}
