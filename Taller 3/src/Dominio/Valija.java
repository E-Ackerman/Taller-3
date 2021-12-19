
package Dominio;


public class Valija extends Entrega
{
    private String material;
    
    public Valija(int codigo, int peso, String material) {
        super(codigo, peso);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public double getValor() 
    {
        double valor;
        if(material.equals("Cuero")) 
        {
            valor = 200 * (this.getPeso()/1000) * 150;
        }
        else if (material.equals("Tela")) 
        {
            valor = 100 * (this.getPeso()/1000) * 150;
        }
        else 
        {
            valor = 150 * (this.getPeso()/1000) * 150;
        }
        
        return valor;
    }
    
}
