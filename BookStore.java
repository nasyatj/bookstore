/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 *
 * @author nasyajames
 */
public abstract class BookStore {
    protected Main sceneChange = new Main();
    protected static ArrayList<Book> availableBooks = new ArrayList<>();
    protected static ArrayList<Customer> customers = new ArrayList<>();
    protected ObservableList<Book> books = FXCollections.observableArrayList();
    protected ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    protected ArrayList<Book> shoppingCart = new ArrayList<>();
    
    //check username and password
    public void login(String username, String password) throws IOException{
       
        if(username.equals("admin") && password.equals("admin")) {
            sceneChange.changeScene("OwnerStartScreen.fxml");
        }
        else{
            for(Customer c: customers){
                if(username.equals(c.getUsername()) && password.equals(c.getPassword())){
                    writeCustomerData(c.getUsername() + ", " + c.getPassword() + "\n");
                    sceneChange.changeScene("CustomerStartScreen.fxml");
                }
            }
        }
    }
    
    //send logged in customer info to message.txt
    public void writeCustomerData(String message) throws IOException{
        FileWriter file = new FileWriter("message.txt", true);
        
        String customerData = message;
        file.write(customerData);
        
        file.close();
    }
    
    //returns to login screen, resets visible data
    public void logout(ActionEvent event) throws IOException
    {
        sceneChange.changeScene("LoginScreen.fxml");
        books.clear();
        allCustomers.clear();
        
        for(Book b: availableBooks)
            b.setSelect(false);
    }
    
    //clears a specified text file
    public void reset(String txtFile) throws IOException {
        FileWriter file = new FileWriter(txtFile, false);
        file.close();
    }
    
    //returns to owner start screen, resets visible data
    public void goBack(ActionEvent event) throws IOException{
        sceneChange.changeScene("OwnerStartScreen.fxml");
        books.clear();
        allCustomers.clear();
    }
    
    //adds books provided by owner to tableview
    public ObservableList<Book> getBooks(){
        
        for(Book b: availableBooks){
            books.add(b);
        }
        
        return books;
    }
    
    //adds books provided by owner to tableview
    public ObservableList<Customer> getCustomers(){
        for(Customer c: customers){
            allCustomers.add(c);
        }
        
        return allCustomers;
    }
}
