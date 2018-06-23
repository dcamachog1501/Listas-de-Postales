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
public class NodeList 
{
    private Lista valor;
    private NodeList next;
    
    public NodeList(Lista l)
    {
        
        this.valor=l;
        this.next=null;
    }
    public void setNext(Lista l)
    {
        this.next= new NodeList(l);
    }
    public Lista getList()
    {
        return this.valor;
    }
    public NodeList getNext()
    {
        return this.next;
    } 
}
