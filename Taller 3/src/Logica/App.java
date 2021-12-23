
package Logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class App 
{
    /**
     * Method that saves the data from the text file
     * @param sistema 
     */
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
    
    /**
     * Method that saves the data from the text file
     * @param sistema 
     */
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
    
    /**
     * Method that saves the data from the text file
     * @param sistema 
     */
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
                    sistema.ingresarDocumento(Integer.valueOf(partes[0]), Double.valueOf(partes[4]),Double.valueOf(partes[5]));
                }
                else if(partes[1].equals("E")) 
                {
                    sistema.ingresarEncomienda(Integer.valueOf(partes[0]), Double.valueOf(partes[4]), Double.valueOf(partes[5]), Double.valueOf(partes[6]), Double.valueOf(partes[7]));
                }
                else 
                {
                    sistema.ingresarValija(Integer.valueOf(partes[0]), partes[4], Double.valueOf(partes[5]));
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
        
        Scanner read = new Scanner(System.in);
        
        System.out.print("Desea cerrar el sistema? (SI) (NO): ");
        String respuesta = "";
        respuesta = read.next().toUpperCase();
        
        while (!respuesta.equals("SI") && !respuesta.equals("NO")) 
        {
            System.out.println("\n--- El texto ingresado es incorrecto ---");
            System.out.print("\nDesea cerrar el sistema? (SI) (NO): ");
            respuesta = read.next().toUpperCase();
        }
        while (respuesta.equals("NO")) 
        {
            System.out.println("\n  INICIO DE SESION\n");
            System.out.print("\nIngrese su rut (Con puntos y guion) : ");
            String rut = read.next();
            String resp = "";
            while(!sistema.verificarSesion(rut) && !rut.equals("Admin"))  
            {
                System.out.print("\n--- El rut ingresado no se encontro en el registro ---\nDesea ingresarla otra vez (si/no) : ");
                resp = read.next().toLowerCase();
                
                while(!resp.equals("si") && !resp.equals("no")) 
                {
                    System.out.println("\n--- Ingrese una respuesta valida ---");
                    System.out.print("\nDesea ingresarla otra vez (si/no) : ");
                    resp = read.next().toLowerCase();
                }
                if(resp.equals("no")) 
                {
                    System.out.println("\n  REGISTRO\n");
                    System.out.print("\nIngrese su rut (Con puntos y guion) : ");
                    rut = read.next();
                    System.out.print("\nIngrese su nombre: ");
                    String nombre = read.next();
                    System.out.print("\nIngrese su apellido: ");
                    String apellido = read.next();
                    System.out.print("\nIngrese su ciudad: ");
                    String ciudad = read.next();
                    sistema.ingresarCliente(rut, nombre, apellido, 0, ciudad);
                }

                System.out.print("\nIngrese su rut (Con puntos y guion) : ");
                rut = read.next();    
    
            }
            
            if(rut.equals("Admin")) 
            {
                System.out.print("\nIngrese la contraseña: ");
                String contra = read.next();
                int cant =0;
                
                while(!contra.equals("choripan123") && cant!= 3) 
                {
                    System.out.println("\n--- Contraseña incorrecta ---\n");
                    System.out.print("\nIngrese la contraseña: ");
                    contra = read.next();
                    cant++;
                }
                if(cant==3) 
                {
                    System.out.println("\n--- Limite de intentos alcanzados ---\n");
                    break;
                }
                else 
                {
                    String op = "";
                    while(!op.equals("5"))
                    {
                        System.out.println("\n    MENU DE ADMIN\n");
                        System.out.println("\n 1)Entregas por tipo \n 2)Entregas por localización \n 3)Entregas por cliente \n 4)Registro de ganancias \n 5)Cerrar sesión");
                        System.out.print("\nIngrese la opcion: ");
                        op = read.next();

                        while(!op.equals("1") && !op.equals("2") && !op.equals("3") && !op.equals("4") && !op.equals("5")) 
                        {
                            System.out.println("\n--- Opcion ingresada incorrecta --- \n");
                            System.out.print("\nIngrese la opcion: ");
                            op = read.next();
                        }

                        if(op.equals("1")) 
                        {
                            System.out.println("\n  ENTREGAS POR TIPO\n" + sistema.obtenerEntregasTipo());                        
                        }
                        else if(op.equals("2")) 
                        {
                            System.out.println("\n  ENTREGAS POR LOCALIZACIÓN\n" + sistema.obtenerEntregasLocalizacion());
                        }
                        else if(op.equals("3")) 
                        {
                            System.out.println("\n  ENTREGAS POR CLIENTE\n" + sistema.obtenerEntregasCliente());
                        }
                        else if(op.equals("4")) 
                        {
                            System.out.println("\n  REGISTRO DE GANANCIAS\n" + sistema.obtenerRegistro());
                        }
                        else 
                        {
                            break;
                        }
                    }
                }
            }
            else 
            {
                String op2 = "";
                while(!op2.equals("4"))
                {
                    System.out.println("\n    MENU DE CLIENTE\n");
                    System.out.println("\n 1)Realizar entrega \n 2)Recargar saldo \n 3)Ver mis entregas \n 4)Cerrar sesión");
                    System.out.print("\nIngrese la opcion: ");
                    op2 = read.next();

                    while(!op2.equals("1") && !op2.equals("2") && !op2.equals("3") && !op2.equals("4")) 
                    {
                        System.out.println("\n--- Opcion ingresada incorrecta --- \n");
                        System.out.print("\nIngrese la opcion: ");
                        op2 = read.next();
                    }
                    if(op2.equals("1")) 
                    {
                        System.out.println("\n  REALIZAR ENTREGA\n");
                        System.out.print("Ingrese el tipo de entrega a realizar"); 
                        String tipo = read.next().toLowerCase();
                        
                        while(!tipo.equals("documento") && !tipo.equals("encomienda") && !tipo.equals("valija") ) 
                        {
                            System.out.println("\n--- El tipo de entrega ingresada no esta en el registro ---\n");
                            System.out.print("Ingrese el tipo de entrega a realizar"); 
                            tipo = read.next().toLowerCase();
                        }
                        double peso;
                        if(tipo.equals("documento")) 
                        {
                            System.out.print("\nIngrese el peso del documento (Kg): ");
                            peso = read.nextDouble();
                            System.out.print("\nIngrese el grosor (cm): ");
                            double grosor = read.nextDouble();
                            if(peso<= 1.5 && grosor<=5) 
                            {
                                System.out.print("\nIngrese el rut del destinatario (Con puntos y guion) : ");
                                String destinatario = read.next();
                                if(sistema.verificarSesion(destinatario)) 
                                {
                                    int codigo = (int) (Math.random() * 1000000); 
                                    while (sistema.verificarCodigo(codigo)) 
                                    {
                                        codigo = (int) (Math.random() * 1000000);
                                    }
                                    sistema.ingresarDocumento(codigo, (peso*1000), (grosor*10));
                                    if(sistema.realizarPago(rut, codigo, rut, destinatario).equals("1")) 
                                    {
                                        System.out.println("\n--- No posee saldo suficiente para ealizar la entrega ---");
                                    }
                                    else 
                                    {
                                        System.out.println("\n  La entrega se ha completado con extio!");
                                    } 
                                }
                                else 
                                {
                                    System.out.println("\n--- El rut del destinatario no se encuentra registrado, la entrega queda anulada ---\n");
                                }
                                
                            }
                            else 
                            {
                                System.out.println("\n--- No cumple con los limites establecidos --- \n");
                            }
                            
                        }
                        else if(tipo.equals("encomienda")) 
                        {
                            System.out.print("\nIngrese el peso de la encomienda (Kg): ");
                            peso = read.nextDouble();
                            System.out.print("\nIngrese el largo (m): ");
                            double largo = read.nextDouble();
                            System.out.print("\nIngrese el ancho (m): ");
                            double ancho = read.nextDouble();
                            System.out.print("\nIngrese el profundidad (m): ");
                            double profundidad = read.nextDouble();
                            if(peso<= 50 && largo<=1.2 && ancho<= 0.8 && profundidad <= 0.8) 
                            {
                                System.out.print("\nIngrese el rut del destinatario (Con puntos y guion) : ");
                                String destinatario = read.next();
                                if(sistema.verificarSesion(destinatario)) 
                                {
                                    int codigo = (int) (Math.random() * 1000000); 
                                    while (sistema.verificarCodigo(codigo)) 
                                    {
                                        codigo = (int) (Math.random() * 1000000);
                                    }
                                    sistema.ingresarEncomienda(codigo, (peso*1000), (largo*100), (ancho*100), (profundidad*100));
                                    if(sistema.realizarPago(rut, codigo, rut, destinatario).equals("1")) 
                                    {
                                        System.out.println("\n--- No posee saldo suficiente para ealizar la entrega ---");
                                    }
                                    else 
                                    {
                                        System.out.println("\n  La entrega se ha completado con extio!");
                                    } 
                                }
                                else 
                                {
                                    System.out.println("\n--- El rut del destinatario no se encuentra registrado, la entrega queda anulada ---\n");
                                }
                                
                            }
                            else 
                            {
                                System.out.println("\n--- No cumple con los limites establecidos --- \n");
                            }
                        }
                        else 
                        {
                            System.out.print("\nIngrese el peso de la encomienda (Kg): ");
                            peso = read.nextDouble();
                            System.out.print("\nIngrese el material (Cuero,Plástico,Plástico): ");
                            String material = read.next();
                            if(peso<= 2 && !material.equals("Cuero") && !material.equals("Plástico") && !material.equals("Plástico")) 
                            {
                                System.out.print("\nIngrese el rut del destinatario (Con puntos y guion) : ");
                                String destinatario = read.next();
                                if(sistema.verificarSesion(destinatario)) 
                                {
                                    int codigo = (int) (Math.random() * 1000000); 
                                    while (sistema.verificarCodigo(codigo)) 
                                    {
                                        codigo = (int) (Math.random() * 1000000);
                                    }
                                    sistema.ingresarValija(codigo, material, (peso*1000));
                                    if(sistema.realizarPago(rut, codigo, rut, destinatario).equals("1")) 
                                    {
                                        System.out.println("\n--- No posee saldo suficiente para ealizar la entrega ---");
                                    }
                                    else 
                                    {
                                        System.out.println("\n  La entrega se ha completado con extio!");
                                    } 
                                }
                                else 
                                {
                                    System.out.println("\n--- El rut del destinatario no se encuentra registrado, la entrega queda anulada ---\n");
                                }
                                
                            }
                            else 
                            {
                                System.out.println("\n--- No cumple con los limites establecidos --- \n");
                            }
                        }
                    }
                    else if(op2.equals("2")) 
                    {
                        try 
                        {
                            System.out.println("\n  RECARGAR SALDO\n");
                            System.out.print("Ingrese el monto a recargar: ");
                            int monto = read.nextInt();
                            sistema.recargarSaldo(rut, monto);
                            System.out.println("    Saldo recargado exitosamente!");
                        }
                        catch (Exception e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    else if(op2.equals("3")) 
                    {
                        try
                        {
                            System.out.println("\n  MIS ENTREGAS\n" + sistema.obtenerEntregas(rut)); 
                        }
                        catch (Exception e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    else 
                    {
                        
                    }
                }        
            }
            
            System.out.println("\nHa cerrado sesion con exito!\n");
            System.out.print("Desea cerrar el sistema? (SI) (NO): ");
            respuesta = read.next().toUpperCase();
        
            while (!respuesta.equals("SI") && !respuesta.equals("NO")) 
            {
                System.out.println("\n--- El texto ingresado es incorrecto ---");
                System.out.print("\nDesea cerrar el sistema? (SI) (NO): ");
                respuesta = read.next().toUpperCase();
            }

        } // TERMINO DEL SISTEMA    
        System.out.println("\n Sistema Cerrado Correctamente :)");
        
        SobrescribirClientes(sistema);
        SobrescribirEnvios (sistema);
    }
    
    /**
     * Method that overwrites the system data in the text file
     * @param sistema 
     */
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
    
    /**
     * Method that overwrites the system data in the text file
     * @param sistema 
     */
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
