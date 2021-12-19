
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
            
        }
        nuevo.setNext(first);
        first = nuevo;
        cant++;
        return true;
    }
    
//    public Cliente getI (int index) 
//    {
//        Nodo<Cliente> current = first;
//        int i;
//        for(i=0;i<cant;i++) 
//        {
//            if(index == i) 
//            {
//                break;
//            }
//            else 
//            {
//                current = current.getNext();
//            }
//        }
//        
//        if(i==cant) 
//        {
//            return null;
//        }
//        else 
//        {
//            return current.getDato();
//        }
//    }
//    
//    public Cliente buscarRut (String rut) 
//    {
//        Nodo<Cliente> current = first;
//        int i;
//        for(i=0;i<cant;i++) 
//        {
//            if(current.getDato().getRut().equals(rut)) 
//            {
//                break;
//            }
//            else 
//            {
//                current = current.getNext();
//            }
//        }
//        
//        if(i==cant) 
//        {
//            return null;
//        }
//        else 
//        {
//            return current.getDato();
//        }
//    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
    
    
}
