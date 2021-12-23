
package Dominio;


public class Documento extends Entrega
{
    private double grosor;

    public Documento(int codigo, double peso, double grosor) {
        super(codigo, peso);
        this.grosor = grosor; 
    }

    public double getGrosor() {
        return grosor;
    }

    public void setGrosor(double grosor) {
        this.grosor = grosor;
    }

    @Override
    public double getValor() 
    {
        double newPeso = this.getPeso()/1000;
        double newGrosor = (grosor/10);
        double valor = newPeso* newGrosor * 100;   // El peso se pasa de gramos a kilogramos y el grosor se pasa de milimetros a centimetros.
        return valor;
    }
    
}
