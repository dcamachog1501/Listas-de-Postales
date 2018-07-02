/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Camacho
 */
public class Lista 
{
    private int len;
    private Nodo head;
    private String name;
    
    public Lista()
    {
        this.len=0;
        this.head=null;
        this.name=null;
    }
    public Boolean isEmpty()
    {
        if(len==0)
        {
            return true;
        }
        return false;
    }
    public void setName(String n)
    {
        this.name=n;
    }
    public void add(Nodo n)
    {
        if(isEmpty())
        {
            this.head=new Nodo(n.getNum(),n.getQuantity());
            
        }
        else
        {
            Nodo temp= this.head;
            while(temp.getNext()!=null)
            {
                if(temp.getNum().equals(n.getNum()))
                {
                    temp.setQuantity(Integer.toString(Integer.parseInt(temp.getQuantity())+Integer.parseInt(n.getQuantity())));
                    break;
                }
                temp=temp.getNext();
            }
            if(temp.getNext()==null)
            {
                temp.setNext(new Nodo(n.getNum(),n.getQuantity()));
            }
        }
        len++;
    }
    public void newOwner() throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("src/Resources/Listas/ownerList.txt"));
        String text = br.readLine();
        String[]nlista=text.split("@");
        for(int i=0;i<nlista.length;i++)
        {
            this.add(new Nodo(nlista[i]));
        }
    }
    public void newPrimeOwner() throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("src/Resources/Listas/PrimeOwnerList.txt"));
        String text = br.readLine();
        String[]nlista=text.split("@");
        for(int i=0;i<nlista.length;i++)
        {
            this.add(new Nodo(nlista[i]));
        }
    }
    public void printAll()
    {
        Nodo temp= this.head;
        while(temp!=null)
        {
            System.out.println(temp.getNum()+"--> Cantidad: "+temp.getQuantity());
            temp=temp.getNext();
        }
        
    }
    public Lista compare(Lista l)
    {
        Nodo temp= this.head;
        Lista n= new Lista();
        while(temp!=null)
        {
            if(l.lookFor(temp))
            {
                n.add(temp);
            }
            temp=temp.getNext();
        }
        return n;
    }
    public Boolean lookFor(Nodo n)
    {
        Nodo temp= this.head;
        while(temp!=null)
        {
            if(n.getNum().equals(temp.getNum()))
            {
                return true;
            }
            temp=temp.getNext();
        }
        return false;
    }
    public Lista remove(Lista l)
    {
        Nodo temp= this.head;
        Lista n= new Lista();
        while(temp!=null)
        {
            if(!l.lookFor(temp))
            {
                n.add(temp);
            }
            else
            {
                if(Integer.parseInt(temp.getQuantity())>1)
                {
                    temp.setQuantity(Integer.toString((Integer.parseInt(temp.getQuantity())-1)));
                    n.add(temp);
                }
            }
            temp=temp.getNext();
        }
        return n;
    }
    public void save()
    {
        
        try {
            PrintWriter printer= new PrintWriter("src/Resources/Listas/"+this.name+".txt");
            printer.println("---------------Lista Generada---------------");
            printer.println("Dueño: "+this.name);
            printer.println("Numero de postales a cambiar: "+this.len);
            printer.println("Postales a cambiar: ");
            String hileras[]=this.get8().split("@");
            for(int i=0;i<hileras.length;i++)
            {
                printer.println(hileras[i]);
            }
            printer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void saveOwner(String file)
    {
        PrintWriter printer= null;
        try {
            printer = new PrintWriter("src/Resources/Listas/"+file+".txt");
            Nodo temp= this.head;
            String f="";
            while(temp!=null)
            {
                if(temp.getNext()==null)
                {
                    f+=temp.getNum()+"#"+temp.getQuantity();
                }
                else
                {
                    f+=temp.getNum()+"#"+temp.getQuantity()+"@";
                }
                temp=temp.getNext();
                
            }   printer.println(f);
        } catch (Exception e) 
        {
            e.printStackTrace();
        } finally {
            printer.close();
        }
    }
    public String get8()
    {
        int i=1;
        String f="";
        Nodo temp= this.head;
        while(true)
        {
            if(i%8==0)
            {
                try
                {
                f+=temp.getNum()+"@";
                }
                catch(Exception e)
                {
                    
                }
            }
            else
            {
                if(temp==null)
                {
                    break;
                }
                f+=temp.getNum()+" ";
            }
            try
            {
            temp=temp.getNext();
            }
            catch(Exception e)
            {
                
            }
            i++;
        }
        return f;
    }
    public void addList(Lista l)
    {
        Nodo temp= this.head;
        while(temp.getNext()!=null)
        {
            temp=temp.getNext();
        }
        temp.setNext(l.getHead());
    }
    public Nodo getHead()
    {
        return this.head;
    }
    public void removeSpaces()
    {
        Nodo temp=this.head;
        Lista n= new Lista();
        while(temp!=null)
        {
            if(temp.isNumeric())
            {
                n.add(temp);
            }
            temp=temp.getNext();
        }
        this.head=n.getHead();
    }
    public void clear()
    {
        this.head=null;
        this.len=0;
    }
    public int getLen()
    {
        return this.len;
    }
    public int getAll()
    {
        Nodo temp= this.head;
        int i=0;
        while (temp!=null)
        {
              i+=Integer.parseInt(temp.getQuantity());
              temp=temp.getNext();
        }
        return i;
    }
    public void sort()
    {
        this.removeSpaces();
        while(true)
        {
            Boolean changes=false;
            Nodo temp=this.head;
            Nodo temp2=this.head;
            while (temp.getNext()!=null)
            {
                if(Integer.parseInt(temp.getNum())>Integer.parseInt(temp.getNext().getNum()))
                {
                    if(temp==this.head)
                    {
                        Nodo t=temp.getNext();
                        temp.setNext(temp.getNext().getNext());
                        t.setNext(temp);
                        this.head=t;
                    }
                    else
                    {
                        Nodo t=temp.getNext();
                        temp.setNext(temp.getNext().getNext());
                        t.setNext(temp);
                        temp2.setNext(t);
                        temp2=t;
                    }
                    changes=true;
                }
                else
                {
                    temp2=temp;
                    temp=temp.getNext();
                }
            }
            if(!changes)
            {
                break;
            }
        }
                
    }
    
}
