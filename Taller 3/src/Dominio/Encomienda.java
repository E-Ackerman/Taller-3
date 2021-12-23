
package Dominio;


public class Encomienda extends Entrega
{
    private double largo;
    private double ancho;
    private double profundidad;

    public Encomienda(int codigo, double peso, double largo, double ancho, double profundidad) {
        super(codigo, peso);
        this.largo = largo; 
        this.ancho = ancho;
        this.profundidad = profundidad;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }
    
    @Override
    public double getValor() 
    {
        double valor = (this.getPeso()/1000) * ((largo /100)*(ancho/100)*(profundidad /100)) * 50;   // Se hace el cambio del peso de gr a kg y se pasa de centimetros a metros en todas las longitudes
        return valor;
    }
    
}
