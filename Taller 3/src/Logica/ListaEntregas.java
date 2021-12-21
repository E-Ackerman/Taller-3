
package Logica;
import Dominio.*;

public class ListaEntregas 
{
    private Nodo<Entrega> first;
    private Nodo<Entrega> last;
    private int cant;
    
    public ListaEntregas () 
    {
        first = null;
        last = null;
        cant = 0;
    }
    
    public boolean ingresarEntrega(Entrega e) 
    {
        Nodo<Entrega> nuevo = new Nodo(e);
        if(cant == 0) 
        {
            last = nuevo;
            nuevo.setPrevio(null);
        }
        else 
        {
            last.setNext(nuevo);
            first.setPrevio(nuevo);
            nuevo.setPrevio(null);
        }
        nuevo.setNext(first);
        first = nuevo;
        cant++;
        return true;
    }
    
    public Entrega getI (int index) 
    {
        Nodo<Entrega> current = first;
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
    
    public Entrega buscarCodigo (int codigo) 
    {
        Nodo<Entrega> current = first;
        int i;
        for(i=0;i<cant;i++) 
        {
            if(current.getDato().getCodigo() == codigo) 
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
    
    public boolean eliminarEntrega (int codigo) 
    {
        Nodo<Entrega> current = first;
        int i;
        for(i=0;i<cant;i++) 
        {
            if(current.getDato().getCodigo() == codigo) 
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
            return false;
        }
        else 
        {
            if(cant == 1)
            {
                first = null;
                last = null;
            }
            else if(current == first) 
            {
                first = current.getNext();
                last.setNext(first);
                first.setPrevio(null);
            }
            else if(current == last) 
            {
                last = current.getPrevio();
                last.setNext(first);
            }
            else 
            {
                current.getNext().setPrevio(current.getPrevio());
                current.getPrevio().setNext(current.getNext());
            }
            cant--;
            return true;
        }
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
    
    
}
