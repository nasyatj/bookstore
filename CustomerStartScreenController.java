/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author nasyajames
 */
public class CustomerStartScreenController extends BookStore implements Initializable {
    
    @FXML 
    private TableView<Book> tableView;
    
    @FXML 
    private TableColumn<Book, String> bookTitleColumn;
    
    @FXML 
    private TableColumn<Book, String> bookPriceColumn;
    
    @FXML 
    private TableColumn<Book, String> selectColumn;
    
    @FXML
    private Label customerWelcome;
    
    private Customer thisCustomer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        //set up table columns
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        bookPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
        
        //load data into the table
        getBooks();
        tableView.setItems(books);
        
        
        //reads data from file to supply for customer welcome message
        try {
            readCustomerData();
        } catch (IOException ex) {
            System.out.println("error reading customer data");
        }
        
        String message = "Welcome " + thisCustomer.getUsername() + ". You have " + thisCustomer.getPoints() + " points. Your Status is: " + thisCustomer.findStatus(thisCustomer.getPoints());
        customerWelcome.setText(message);
        
    }
    
    //reads customer info from message.txt
    public void readCustomerData() throws IOException{
        Scanner scan = new Scanner(new FileReader("message.txt"));

        String[] customerInformation = scan.nextLine().split(", ");
        String user = customerInformation[0];
        String pass = customerInformation[1];
        
        //find existing customer associated with data provided in file
        for(Customer c: customers){
            if(c.getUsername().equals(user) && c.getPassword().equals(pass))
                thisCustomer = c;
        }
    }
    
    //returns to login and clears files
    @FXML
    public void logoutButtonPressed(ActionEvent event) throws IOException
    {
        reset("purchase.txt");
        reset("message.txt");
        logout(event);
    }
    
    //writes current shopping cart info to shoppingCart.txt, writes customer info and type of purchase to purchase.txt
    @FXML
    public void buyButtonPressed(ActionEvent event) throws IOException
    {
        shoppingCart = thisCustomer.getShoppingCart();
        
        //write shopping cart to file
        FileWriter bookFile = new FileWriter("shoppingCart.txt", true);
        for(Book b: shoppingCart){
            String bookInfo = b.getTitle() + ", " + b.getPrice() + "\n";
            bookFile.write(bookInfo);
            b.setSelect(false);
        }
        bookFile.close();

        //write purchase info to file
        FileWriter file = new FileWriter("purchase.txt", true);
        
        String purchaseInfo = thisCustomer.getUsername() + ", "+ thisCustomer.getPassword() + ", "+ "buy\n";
        file.write(purchaseInfo);
        
        file.close();
        
        sceneChange.changeScene("CustomerCostScreen.fxml");
    }
    
    //writes current shopping cart info to shoppingCart.txt, writes customer info and type of purchase to purchase.txt
    @FXML
    public void redeemBuyButtonPressed(ActionEvent event) throws IOException
    {
        shoppingCart = thisCustomer.getShoppingCart();
        
        //write shopping cart to file
        FileWriter bookFile = new FileWriter("shoppingCart.txt", true);
        for(Book b: shoppingCart){
            String bookInfo = b.getTitle() + ", " + b.getPrice() + "\n";
            bookFile.write(bookInfo);
        }
        bookFile.close();

        //write purchase info to file
        FileWriter file = new FileWriter("purchase.txt", true);
        
        String purchaseInfo = thisCustomer.getUsername() + ", "+ thisCustomer.getPassword() + ", " + "redeem buy\n";
        file.write(purchaseInfo);
        
        file.close();
        
        sceneChange.changeScene("CustomerCostScreen.fxml");
    }
    
}
