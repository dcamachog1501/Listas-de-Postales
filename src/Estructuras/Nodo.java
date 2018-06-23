/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author Daniel Camacho
 */
public class Nodo 
{
    private String valor;
    private String cantidad;
    private Nodo next;
    
    public Nodo(String n, String q)
    {
        this.valor=n;
        this.cantidad=q;
        this.next=null;
    }
    public Nodo(String n)
    {
        String[]l=n.split("#");
        this.valor=l[0];
        try
        {
        this.cantidad=l[1];
                }
        catch(Exception e)
        {
            this.cantidad="1";
        }
        this.next=null;
    }
    public void setNext(Nodo n)
    {
        this.next= n;
    }
    public String getNum()
    {
        return this.valor;
    }
    public Nodo getNext()
    {
        return this.next;
    }
    public String getQuantity()
    {
        return this.cantidad;
    }
    public void setQuantity(String i)
    {
        this.cantidad=i;
    }
    public boolean isNumeric()  
    {  
      try  
      {  
        double d = Double.parseDouble(this.valor);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
}
