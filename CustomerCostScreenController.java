/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author nasyajames
 */
public class CustomerCostScreenController extends BookStore implements Initializable {
    @FXML 
    private Label totalCostLabel;
    
    @FXML
    private Label pointsLabel;
    
    @FXML 
    private Label statusLabel;
    
    private double totalCost;
    
    private String typeOfPurchase;
    
    private Purchase thisPurchase;
    
    private Customer thisCustomer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //create purchase class from purchase info file
        try {
            getPurchaseInfo();
            getShoppingCart();
        } catch (IOException ex) {
            System.out.println("error getting purchase info");
        }
        
        //complete correct type of purchase
        if(typeOfPurchase.equals("buy"))
            totalCost = thisPurchase.buy(thisCustomer, shoppingCart);
        else if(typeOfPurchase.equals("redeem buy"))
            totalCost = thisPurchase.redeemBuy(thisCustomer, shoppingCart);
        
        //set labels
        totalCostLabel.setText("" + totalCost);
        
        pointsLabel.setText("" + thisCustomer.getPoints());
        
        statusLabel.setText(thisCustomer.findStatus(thisCustomer.getPoints()));
    }
    
    //reads books from shoppingCart.txt file
    public void getShoppingCart() throws IOException{
        Scanner scan = new Scanner(new FileReader("shoppingCart.txt"));

        while(scan.hasNext()) {
            String[] bookInformation = scan.nextLine().split(",");
            String title = bookInformation[0];
            double price = Double.parseDouble(bookInformation[1]);
            shoppingCart.add(new Book(title, price));
        }
    }

    //read purchase info from purchase.txt
    public void getPurchaseInfo() throws IOException{
        Scanner scan = new Scanner(new FileReader("purchase.txt"));
        
        String[] purchaseInfo = scan.nextLine().split(", ");
        String user = purchaseInfo[0];
        String pass = purchaseInfo[1];
        typeOfPurchase = purchaseInfo[2];
        
        for(Customer c: customers){
            if(c.getUsername().equals(user) && c.getPassword().equals(pass)){
                thisCustomer = c;
            }
        }
        
        thisPurchase = new Purchase(user, pass);
    }
    
    //returns to login and clears text files
    public void logoutButtonPressed(ActionEvent event) throws IOException
    {
        reset("shoppingCart.txt");
        reset("message.txt");
        reset("purchase.txt");
        logout(event);
    }
    
}
