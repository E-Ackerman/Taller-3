
package Logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class App 
{
    public static void LeerLocalizaciones(SistemaImpl sistema) 
    {
        try
        {
            Scanner scanner = new Scanner(new File("localización.txt"));
            
            while(scanner.hasNextLine()) 
            {
                String linea = scanner.nextLine();
                sistema.ingresarLocalizacion(linea);
            }
        }
        catch (Exception ex) 
        {
            System.out.println("No se encontro el archivo de texto");
        }
    }
    
    public static void LeerClientes(SistemaImpl sistema) 
    {
        try
        {
            Scanner scanner = new Scanner(new File("Cliente.txt"));
            
            while(scanner.hasNextLine()) 
            {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                sistema.ingresarCliente(partes[0], partes[1], partes[2], Integer.valueOf(partes[3]), partes[4]);
                try
                {
                    sistema.asociarClienteOficina(partes[0], partes[4]);
                }
                catch (Exception e) 
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (Exception ex) 
        {
            System.out.println("No se encontro el archivo de texto");
        }
    }
    
    public static void LeerEntregas (SistemaImpl sistema) 
    {
        try
        {
            Scanner scanner = new Scanner(new File("Entregas.txt"));
            
            while(scanner.hasNextLine()) 
            {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                if(partes[1].equals("D")) 
                {
                    sistema.ingresarDocumento(Integer.valueOf(partes[0]), Integer.valueOf(partes[4]),Integer.valueOf(partes[5]));
                }
                else if(partes[1].equals("E")) 
                {
                    sistema.ingresarEncomienda(Integer.valueOf(partes[0]), Integer.valueOf(partes[4]), Integer.valueOf(partes[5]), Integer.valueOf(partes[6]), Integer.valueOf(partes[7]));
                }
                else 
                {
                    sistema.ingresarValija(Integer.valueOf(partes[0]), partes[4], Integer.valueOf(partes[5]));
                }
                try
                {
                    sistema.asociarEntregaCliente(Integer.valueOf(partes[0]), partes[2], partes[3]);
                }
                catch (Exception e) 
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (Exception ex) 
        {
            System.out.println("No se encontro el archivo de texto");
        }
    }


    public static void main(String[] args)
    {
        SistemaImpl sistema = new SistemaImpl();
        LeerLocalizaciones(sistema);
        LeerClientes(sistema);
        LeerEntregas (sistema);
        
        SobrescribirClientes(sistema);
        SobrescribirEnvios (sistema);
    }
    
    public static void SobrescribirClientes(SistemaImpl sistema) 
    {
        try
        {
            FileWriter fw = new FileWriter(new File("Cliente.txt"));
            PrintWriter write = new PrintWriter(new BufferedWriter(fw));
            write.write(sistema.obtenerClientes());   
            write.close();       
        }
        catch (Exception ex) 
        {
            System.out.println("No se encontro el archivo de texto");
        }
    }
    
    public static void SobrescribirEnvios (SistemaImpl sistema) 
    {
        try
        {
            FileWriter fw = new FileWriter(new File("Entregas.txt"));
            PrintWriter write = new PrintWriter(new BufferedWriter(fw));
            write.write(sistema.obtenerEntregas());   
            write.close();       
        }
        catch (Exception ex) 
        {
            System.out.println("No se encontro el archivo de texto");
        }
    }
}
