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
public class ListList 
{
    private int len;
    private NodeList head;
    
    public ListList()
    {
        this.len=0;
        this.head=null;
    }
    public Boolean isEmpty()
    {
        if(len==0)
        {
            return true;
        }
        return false;
    }
    public void add(Lista l)
    {
        if(isEmpty())
        {
            this.head= new NodeList(l);
            
        }
        else
        {
            NodeList temp= this.head;
            while(temp.getNext()!=null)
            {
                temp=temp.getNext();
            }
            temp.setNext(l);
        }
        len++;
    }
    
}
