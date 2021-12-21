
package Logica;


public interface Sistema 
{
    public boolean ingresarLocalizacion (String ciudad);
    public boolean ingresarCliente (String rut, String nombre, String apellido, int saldo, String ciudad);
    public boolean ingresarDocumento (int codigo, int peso, int grosor);
    public boolean ingresarEncomienda (int codigo, int peso, int largo, int ancho, int profundidad);
    public boolean ingresarValija (int codigo, int peso, String material);
    public void asociarClienteOficina (String rut, String ciudad);
    public void asociarEntregaCliente (int codigo, String rutRemitente, String rutDestinatario);
    public boolean verificarSesion (String rut);
    public void realizarPago (String rut, int codigo);
    public boolean anularEntrega (String rut, int codigo);
    public boolean recargarSaldo (String rut, int monto);
    public boolean obtenerEntregas (String rut);
    public String obtenerEntregasTipo ();
    public String obtenerEntregasLocalizacion ();
    public String obtenerEntregasCliente ();
    public String obtenerRegistro ();
    public String obtenerClientes ();
    public String obtenerEntregas ();  
}
