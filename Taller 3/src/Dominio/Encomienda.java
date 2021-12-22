
package Dominio;


public class Encomienda extends Entrega
{
    private int largo;
    private int ancho;
    private int profundidad;

    public Encomienda(int codigo, int peso, int largo, int ancho, int profundidad) {
        super(codigo, peso);
        this.largo = largo; 
        this.ancho = ancho;
        this.profundidad = profundidad;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }
    
    @Override
    public double getValor() 
    {
        double valor = (this.getPeso()/1000) * ((largo /100)*(ancho/100)*(profundidad /100)) * 50;   // Se hace el cambio del peso de gr a kg y se pasa de centimetros a metros en todas las longitudes
        return valor;
    }
    
}
