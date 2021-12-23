
package Logica;


public interface Sistema 
{
    public boolean ingresarLocalizacion (String ciudad);
    public boolean ingresarCliente (String rut, String nombre, String apellido, int saldo, String ciudad);
    public boolean ingresarDocumento (int codigo, double peso, double grosor);
    public boolean ingresarEncomienda (int codigo, double peso, double largo, double ancho, double profundidad);
    public boolean ingresarValija (int codigo, String material, double peso);
    public void asociarClienteOficina (String rut, String ciudad);
    public void asociarEntregaCliente (int codigo, String rutRemitente, String rutDestinatario);
    public boolean verificarSesion (String rut);
    public String realizarPago (String rut, int codigo,String remitente, String destinatario);
    public boolean verificarCodigo (int codigo);
    public void recargarSaldo (String rut, int monto);
    public String obtenerEntregas (String rut);
    public String obtenerEntregasTipo ();
    public String obtenerEntregasLocalizacion ();
    public String obtenerEntregasCliente ();
    public String obtenerRegistro ();
    public String obtenerClientes ();
    public String obtenerEntregas ();  
}
