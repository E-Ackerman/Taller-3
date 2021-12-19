
package Logica;


public class Nodo <T> 
{
    private Nodo next;
    private Nodo previo;
    private T dato;
    
    public Nodo(T dato) 
    {
        this.dato = dato;
        this.next = null;
        this.previo = null;
    }

    public Nodo getPrevio() {
        return previo;
    }

    public void setPrevio(Nodo last) {
        this.previo = last;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }
    
}
