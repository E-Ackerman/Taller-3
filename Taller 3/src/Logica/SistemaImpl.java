
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
    public boolean ingresarDocumento(int codigo, double peso, double grosor) 
    {
        Entrega documento = new Documento(codigo,peso,grosor);
        boolean ingreso = listaentregas.ingresarEntrega(documento);
        return ingreso;
    }

    @Override
    public boolean ingresarEncomienda(int codigo, double peso, double largo, double ancho, double profundidad) 
    {
        Entrega encomienda = new Encomienda(codigo,peso,largo,ancho,profundidad);
        boolean ingreso = listaentregas.ingresarEntrega(encomienda);
        return ingreso;
    }

    @Override
    public boolean ingresarValija(int codigo, String material, double peso) 
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
                    Oficina oficina1 = listaoficinas.buscarNombre(remitente.getCiudad());
                    Oficina oficina2 = listaoficinas.buscarNombre(destinatario.getCiudad());
                    
                    if(oficina1 !=null && oficina2!=null) 
                    {
                        oficina1.getLe().ingresarEntrega(entrega);
                        oficina2.getLe().ingresarEntrega(entrega);
                    }
                    else 
                    {
                        throw new NullPointerException("No se encontro la/las oficinas");
                    }
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
    public boolean verificarSesion(String rut) 
    {
        return (listaclientes.buscarRut(rut) != null);
    }

    @Override
    public String realizarPago(String rut, int codigo, String remitente, String destinatario) 
    {
        String salida = "";
        Cliente cliente = listaclientes.buscarRut(rut);
        if(cliente!=null) 
        {
            Entrega entrega = listaentregas.buscarCodigo(codigo);
            if(entrega!=null) 
            {
                if(cliente.getSaldo() < entrega.getValor()) 
                {
                    salida = "1";
                }
                else 
                {
                    cliente.setSaldo(cliente.getSaldo() - (int)entrega.getValor()); 
                    Cliente remitente1 = listaclientes.buscarRut(remitente);
                    Cliente destinatario1 = listaclientes.buscarRut(destinatario);
                    entrega.setDestinatario(destinatario1);
                    entrega.setRemitente(remitente1);
                    remitente1.getLe().ingresarEntrega(entrega);
                    destinatario1.getLe().ingresarEntrega(entrega);   
                    Oficina oficina1 = listaoficinas.buscarNombre(remitente1.getCiudad());
                    Oficina oficina2 = listaoficinas.buscarNombre(destinatario1.getCiudad());
                    
                    if(oficina1 !=null && oficina2!=null) 
                    {
                        oficina1.getLe().ingresarEntrega(entrega);
                        oficina2.getLe().ingresarEntrega(entrega);
                    }
                    else 
                    {
                        throw new NullPointerException("No se encontro la/las oficinas");
                    }
                    salida = "2";
                }
            }
            else 
            {
                System.out.println("No se encontro la entrega");
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro el cliente");
        }
        return salida;
    }

    @Override
    public boolean verificarCodigo( int codigo) 
    {
        Entrega entrega = listaentregas.buscarCodigo(codigo);
        
        if(entrega!=null) 
        {
            return true;
        }
        else 
        {
            return false;
        }    
    }

    @Override
    public void recargarSaldo(String rut, int monto) 
    {
        Cliente cliente = listaclientes.buscarRut(rut);
        
        if(cliente!=null) 
        {
            cliente.setSaldo(cliente.getSaldo()+monto); 
        }
        else 
        {
            throw new NullPointerException("No se encontro el cliente");
        }
    }

    @Override
    public String obtenerEntregas(String rut) 
    {
        String salida = "";
        Cliente cliente = listaclientes.buscarRut(rut);
        if(cliente!=null) 
        {
            for(int i=0;i<cliente.getLe().getCant();i++) 
            {
                Entrega entrega = cliente.getLe().getI(i); 
                if(entrega instanceof Documento) 
                {
                    Documento documento = (Documento) entrega;
                    if(documento.getRemitente().getRut().equals(rut))
                    {
                        salida += "\n- Entrega REALIZADA de Documento de codigo " + documento.getCodigo() + " con un peso de " + documento.getPeso() + " gramos";
                    }
                    else 
                    {
                        salida += "\n- Entrega RECIBIDA de Documento de codigo " + documento.getCodigo() + " con un peso de " + documento.getPeso() + " gramos";
                    }
                }
                else if (entrega instanceof Encomienda) 
                {
                    Encomienda encomienda = (Encomienda) entrega;
                    if(encomienda.getRemitente().getRut().equals(rut))
                    {
                        salida += "\n- Entrega REALIZADA de Encomienda de codigo " + encomienda.getCodigo() + " con un peso de " + encomienda.getPeso() + " gramos";
                    }
                    else 
                    {
                        salida += "\n- Entrega RECIBIDA de Encomienda de codigo " + encomienda.getCodigo() + " con un peso de " + encomienda.getPeso() + " gramos";
                    }
                }
                else 
                {
                    Valija valija = (Valija) entrega;
                    if(valija.getRemitente().getRut().equals(rut))
                    {
                        salida += "\n- Entrega REALIZADA de Valija de codigo " + valija.getCodigo() + " con un peso de " + valija.getPeso() + " gramos";
                    }
                    else 
                    {
                        salida += "\n- Entrega RECIBIDA de Valija de codigo " + valija.getCodigo() + " con un peso de " + valija.getPeso() + " gramos";
                    }
                }
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro el cliente");
        }
        return salida;
    }

    @Override
    public String obtenerEntregasTipo() 
    {
        String salida = "";
        
        for(int i=0;i<listaentregas.getCant();i++) 
        {
            Entrega entrega = listaentregas.getI(i);
            
            if(entrega instanceof Documento) 
            {
                Documento documento = (Documento) entrega;
                salida += "\n Documento - valor: $" + documento.getValor();
            }
            else if(entrega instanceof Encomienda) 
            {
                Encomienda encomienda = (Encomienda) entrega;
                salida += "\n Encomienda - valor: $" + encomienda.getValor();
            }
            else 
            {
                Valija valija = (Valija) entrega;
                salida += "\n Valija - valor: $" + valija.getValor();
            }
        }
        
        return salida;
    }

    @Override
    public String obtenerEntregasLocalizacion() 
    {
        String salida = "";
        for(int a=0;a<listaoficinas.getCant();a++) 
        {
            Oficina oficina = listaoficinas.getI(a);
            int recibidos = 0;
            int realizados = 0;
            salida += "\n"+ oficina.getNombre();
            for(int b=0;b<oficina.getLe().getCant();b++) 
            {
                Entrega entrega = oficina.getLe().getI(b);
                if(entrega.getDestinatario().getCiudad().equals(oficina.getNombre())) 
                {
                    recibidos++;
                }
                else 
                {
                    realizados++;
                }
            }
            salida += " realizo " + realizados + " envíos y recibió " + recibidos + " envíos";
        }
        
        return salida;
    }

    @Override
    public String obtenerEntregasCliente() 
    {
        String salida = "";
        for(int a=0;a<listaclientes.getCant();a++) 
        {
            Cliente cliente = listaclientes.getI(a);
            salida += "\n -"+ cliente.getNombre() + " " + cliente.getApellido() + " tiene:";
            
            for(int b=0;b<cliente.getLe().getCant();b++) 
            {
                Entrega entrega = cliente.getLe().getI(b);
                if(entrega instanceof Documento) 
                {
                    Documento documento = (Documento) entrega;
                    salida += "\n   - Documento de codigo "+ documento.getCodigo();
                }
                else if(entrega instanceof Encomienda) 
                {
                    Encomienda encomienda = (Encomienda) entrega;
                    salida += "\n   - Encomienda de codigo " + encomienda.getCodigo();
                }
                else 
                {
                    Valija valija = (Valija) entrega;
                    salida += "\n   - Valija de codigo " + valija.getCodigo();
                }
            }
        }
        
        return salida;
    }

    @Override
    public String obtenerRegistro() 
    {
        String salida ="";
        double total = 0;
        for(int a=0;a<listaoficinas.getCant();a++) 
        {
            Oficina oficina = listaoficinas.getI(a);
            double ganancias = 0;
            for(int b=0;b<oficina.getLe().getCant();b++) 
            {
                Entrega entrega = oficina.getLe().getI(b);
                if(entrega instanceof Documento) 
                {
                    Documento documento = (Documento) entrega;
                    if(documento.getRemitente().getCiudad().equals(oficina.getNombre())) 
                    {
                        ganancias += documento.getValor();
                    }                    
                }
                else if(entrega instanceof Encomienda) 
                {
                    Encomienda encomienda = (Encomienda) entrega;
                    if(encomienda.getRemitente().getCiudad().equals(oficina.getNombre())) 
                    {
                        ganancias += encomienda.getValor();
                    }
                }
                else 
                {
                    Valija valija =(Valija) entrega;
                    if(valija.getRemitente().getCiudad().equals(oficina.getNombre())) 
                    {
                        ganancias += valija.getValor();
                    }
                }
            }
            salida += "\n - Oficina de " + oficina.getNombre() + " obtuvo ganancias de $" + ganancias;
            total += ganancias;
        }
        salida += "\nEl balance total de ganancias fue de $" + total;
        
        return salida;
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
