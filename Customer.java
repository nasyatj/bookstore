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
public class Customer extends BookStore{
    private String status;
    private int points=0;
    private String username;
    private String password;
    
    Customer(String username, String password) {
        this.username = username;
        this.password = password;
        points = 0;
        findStatus(points);
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String name){
        this.username = name;
    }
    
    public int getPoints(){
        return points;
    }
    
    public void setPoints(int p){
        points = p;
    }
    
    public void addPoints(int p){
        this.points += p;
    }
    
    public void removePoints(int p){
        this.points -= p;
    }
    
    public String getStatus(){
        return status;
    }
    
    public String getPassword(){
        return password;
    }
    
    //calculates customer status based on current points
    public String findStatus(int points){
        if(points>=1000){
            status = "Gold";
            return status;
        }
        else{
            status = "Silver";
            return status;
        }
    }
    
    //creates shopping cart of chosen books for customers to purchase
    public ArrayList<Book> getShoppingCart(){
        shoppingCart.clear();
        
        for(Book b: availableBooks){
            if(b.getSelect().isSelected() == true){
                shoppingCart.add(b);
            }
        }
        
        return shoppingCart;
    }
}
