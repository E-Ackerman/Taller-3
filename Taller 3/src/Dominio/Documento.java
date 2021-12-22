
package Dominio;


public class Documento extends Entrega
{
    private int grosor;

    public Documento(int codigo, int peso, int grosor) {
        super(codigo, peso);
        this.grosor = grosor; 
    }

    public int getGrosor() {
        return grosor;
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    @Override
    public double getValor() 
    {
        double valor = (this.getPeso()/1000) * (grosor/10) * 100;   // El peso se pasa de gramos a kilogramos y el grosor se pasa de milimetros a centimetros.
        return valor;
    }
    
}
