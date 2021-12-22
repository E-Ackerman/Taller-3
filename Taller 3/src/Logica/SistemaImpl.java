
package Logica;
import Dominio.*;

public class SistemaImpl implements Sistema
{
    private ListaOficinas listaoficinas;
    private ListaClientes listaclientes;
    private ListaEntregas listaentregas;
    
    public SistemaImpl ()
    {
        listaoficinas = new ListaOficinas(100);
        listaclientes = new ListaClientes();
        listaentregas = new ListaEntregas();
    }

    @Override
    public boolean ingresarLocalizacion(String ciudad) 
    {
        Oficina oficina = new Oficina(ciudad);
        boolean ingreso = listaoficinas.ingresarOficina(oficina);
        return ingreso;
    }

    @Override
    public boolean ingresarCliente(String rut, String nombre, String apellido, int saldo, String ciudad) 
    {
        Cliente cliente = new Cliente(rut,nombre,apellido,saldo,ciudad);
        boolean ingreso = listaclientes.ingresarCliente(cliente);
        return ingreso;
    }

    @Override
    public boolean ingresarDocumento(int codigo, int peso, int grosor) 
    {
        Entrega documento = new Documento(codigo,peso,grosor);
        boolean ingreso = listaentregas.ingresarEntrega(documento);
        return ingreso;
    }

    @Override
    public boolean ingresarEncomienda(int codigo, int peso, int largo, int ancho, int profundidad) 
    {
        Entrega encomienda = new Encomienda(codigo,peso,largo,ancho,profundidad);
        boolean ingreso = listaentregas.ingresarEntrega(encomienda);
        return ingreso;
    }

    @Override
    public boolean ingresarValija(int codigo, String material, int peso) 
    {
        Entrega valija = new Valija(codigo,peso,material);
        boolean ingreso = listaentregas.ingresarEntrega(valija);
        return ingreso;
    }

    @Override
    public void asociarClienteOficina(String rut, String ciudad) 
    {
        Cliente c = listaclientes.buscarRut(rut);
        Oficina o = listaoficinas.buscarNombre(ciudad);
        
        if(c!=null) 
        {
            if(o!=null) 
            {
                o.getLc().ingresarCliente(c); // Cliente ingresado a la lista particular de la oficina
            }
            else 
            {
                c.setCiudad("No disponible"); // Al no encontrarse esa ciudad en el registro no tiene oficinas disponible, por lo tanto se cambia el nombre de la ciudad guardado por el estado no disponible
            }
        }
        
        else 
        {
            throw new NullPointerException("No se encontro el cliente");
        }
    }

    @Override
    public void asociarEntregaCliente(int codigo, String rutRemitente, String rutDestinatario) 
    {
        Entrega entrega = listaentregas.buscarCodigo(codigo);
        if(entrega!=null) 
        {
            Cliente remitente = listaclientes.buscarRut(rutRemitente);
            Cliente destinatario = listaclientes.buscarRut(rutDestinatario);
            if(remitente!=null) 
            {
                if(destinatario!=null)
                {
                    entrega.setRemitente(remitente);
                    entrega.setDestinatario(destinatario);
                    remitente.getLe().ingresarEntrega(entrega);
                    destinatario.getLe().ingresarEntrega(entrega);        
                }
                else 
                {
                    throw new NullPointerException("No se encontro el cliente");
                }
            }
            else
            {
                throw new NullPointerException("No se encontro el cliente");
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro la entrega");
        }
    }

    @Override
    public boolean verificarSesion(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void realizarPago(String rut, int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean anularEntrega(String rut, int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean recargarSaldo(String rut, int monto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean obtenerEntregas(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerEntregasTipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerEntregasLocalizacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerEntregasCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerClientes() 
    {
        String salida = "";
        for(int i=0;i<listaclientes.getCant();i++) 
        {
            Cliente cliente = listaclientes.getI(i);
            salida += cliente.getRut() + ","+ cliente.getNombre() + ","+ cliente.getApellido() + ","+ cliente.getSaldo() + ","+ cliente.getCiudad() + "\n";
        }
        return salida;
    }

    @Override
    public String obtenerEntregas() 
    {
        String salida = "";
        
        for(int i=0;i<listaentregas.getCant();i++) 
        {
            Entrega entrega = listaentregas.getI(i);
            if(entrega instanceof Documento) 
            {
                Documento documento = (Documento) entrega;
                salida += documento.getCodigo() + ",D," + documento.getRemitente().getRut() + "," + documento.getDestinatario().getRut() + "," + documento.getPeso() + "," + documento.getGrosor()+ "\n";
            }
            else if(entrega instanceof Encomienda) 
            {
                Encomienda encomienda = (Encomienda) entrega;
                salida += encomienda.getCodigo() + ",E," + encomienda.getRemitente().getRut() + "," + encomienda.getDestinatario().getRut() + "," + encomienda.getPeso() + "," + encomienda.getLargo() + "," +
                        encomienda.getAncho() + "," + encomienda.getProfundidad() + "\n";
            }
            else 
            {
                Valija valija = (Valija) entrega;
                salida += valija.getCodigo() + ",V," + valija.getRemitente().getRut() + "," + valija.getDestinatario().getRut() + "," + valija.getMaterial() + "," + valija.getPeso() + "\n";
            }
        }
        
        return salida;
    }
}
