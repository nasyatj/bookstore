/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import javafx.scene.control.CheckBox;

/**
 *
 * @author nasyajames
 */
public class Book {

    private String title;
    private double price;
    private CheckBox select;
    
    public Book(String t, double p) {
        this.title = t;
        this.price = p;
        this.select = new CheckBox();
    }
   
    public String getTitle() {
        return title;
    } 
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double p) {
        p = price;
    }
    
    public CheckBox getSelect(){
        return select;
    }
    
    public void setSelect(Boolean value){
        select.setSelected(value);
    }
}

