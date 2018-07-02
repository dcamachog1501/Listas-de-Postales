/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Estructuras.ListList;
import Estructuras.Lista;
import Estructuras.Nodo;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Daniel Camacho
 */
public class Interface 
{
    private ListList mainList;
    private Lista ownerList;
    
    public Interface() throws FileNotFoundException, IOException
    {
        this.mainList=new ListList();
        this.ownerList=new Lista();
        this.ownerList.setName("ownerList");
        this.ownerList.newOwner();
        this.ownerList.unify();
        this.ownerList.sort();
        this.ownerList.saveOwner("ownerList");
        this.ownerList.printAll();
        System.out.println("Postales en la base: "+ownerList.getAll());
        Init();
    }
    public void Init() throws FileNotFoundException, IOException
    {
        while(true)
        {
            System.out.println("--------------------Inicio--------------------");
            System.out.println("1.Comparar lista.");
            System.out.println("2.Ver mi lista.");
            System.out.println("3.Añadir postales a la lista principal.");
            System.out.println("4.Cambiar a lista original.");
            System.out.println("5.Establecer nueva lista original.");
            System.out.println("6.Salir.");
            Scanner reader = new Scanner(System.in);  
            System.out.println("¿Que desea hacer?");
            int l = reader.nextInt();
            if(l==1)
            {
                Lista nuevalista= new Lista();
                System.out.println(">Escriba S para salir");
                System.out.println(">Escriba el numero que desea agregar seguido de un # para agregar la cantidad de postales");
                while(true)
                {
                    System.out.println("Ingrese un numero: ");
                    String r = reader.nextLine();
                    if(r.toLowerCase().equals("s"))
                    {
                        System.out.println("Postales ingresadas: ");
                        nuevalista.printAll();
                        System.out.println("Cantidad: "+nuevalista.getAll());
                        System.out.println("Postales encontradas en la base: ");
                        Lista found=ownerList.compare(nuevalista);
                        found.printAll();
                        System.out.println("Cantidad: "+found.getLen());
                        while(true)
                        {
                            System.out.println(">¿Desea guardar la lista?  Y/N");
                            r = reader.nextLine();
                            if(r.toLowerCase().equals("y"))
                            {
                                System.out.println("Ingrese el nombre de la lista: ");
                                r=reader.nextLine();
                                found.setName(r);
                                ownerList=ownerList.remove(nuevalista);
                                ownerList.printAll();
                                ownerList.saveOwner("ownerList");
                                found.save();
                                mainList.add(nuevalista);
                                break;
                            }
                            else if(r.toLowerCase().equals("n"))
                            {
                                break;
                            }
                            else
                            {
                                System.out.println("Ingrese un comando valido!");
                            }
                        }
                        break;
                    }
                    else
                    {
                        nuevalista.add(new Nodo(r));
                    }
                }
            }
            else if(l==2)
            {
                ownerList.printAll();
                System.out.println("Cantidad de postales en la base: "+ownerList.getAll());
            }
            else if(l==3)
            {
                Lista n= new Lista();
                System.out.println(">Para finalizar el añadido, presione S");
                System.out.println(">Si desea añadir mas de una misma postal, agregue un # seguido de la cantidad de postales");
                while(true)
                {
                    System.out.println("Ingrese una postal:");
                    String r = reader.nextLine();
                    if(r.toLowerCase().equals("s"))
                    {
                        break;
                    }
                    n.add(new Nodo(r));
                }
                System.out.println("Postales añadidas: ");
                n.printAll();
                System.out.println("Cantidad de postales a añadir: "+n.getAll());
                System.out.println("¿Desea guardar el set de postales? Y/N");
                String r = reader.nextLine();
                if(r.toLowerCase().equals("y"))
                {
                    ownerList.addList(n);
                    ownerList.removeSpaces();
                    ownerList.saveOwner("ownerList");
                }
                else if(r.toLowerCase().equals("n"))
                {
                    break;
                }
                
            }
            else if(l==4)
            {
                this.ownerList.clear();
                this.ownerList.newPrimeOwner();
            }
            else if(l==5)
            {
               this.ownerList.saveOwner("PrimeOwnerList");
            }
            else if(l==6)
            {
                break;
            }
        }
    }
}
