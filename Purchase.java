/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import java.util.ArrayList;

/**
 *
 * @author nasyajames
 */
public class Purchase extends Customer{
    public double totalPrice=0.0;
    private int accumulatedPoints;

    public Purchase(String username, String password) {
        super(username, password);
    }
    
    //calculates total cost and earned points based on customer and shopping cart
    public double buy(Customer c, ArrayList<Book> cart){
        
        for(Book b: cart){
            totalPrice += b.getPrice();
        }
        
        accumulatedPoints = (int) (totalPrice * 10);
        c.addPoints(accumulatedPoints);
        
        return totalPrice;
    }

    //applies discount on total cost based on customers points and shopping cart 
    public double redeemBuy(Customer c, ArrayList<Book> cart){
            double discount = ((c.getPoints())/100);
            int pointsUsed;
            
            for(Book b: cart){
            totalPrice += b.getPrice();
            }
            
            double transactionCost = totalPrice - discount;
            
            if(transactionCost < 0)
                pointsUsed = (int) ((transactionCost*-1) * 100);
            else
                pointsUsed = (int) (discount*100);
            
            transactionCost = totalPrice - discount;

            if(transactionCost<0){
                transactionCost = 0.00;
            }
            
            c.removePoints(pointsUsed);
            
            accumulatedPoints = (int) (transactionCost * 10);
            c.addPoints(accumulatedPoints);
            
            return transactionCost;
        }
}
