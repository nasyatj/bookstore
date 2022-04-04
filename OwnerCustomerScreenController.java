/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author nasyajames
 */
public class OwnerCustomerScreenController extends Owner implements Initializable {
    @FXML
    private TextField username;
    
    @FXML
    private TextField password;
    
    @FXML 
    private TableView<Customer> tableView;
    
    @FXML 
    private TableColumn<Customer, String> usernameColumn;
    
    @FXML 
    private TableColumn<Customer, String> passwordColumn;
    
    @FXML 
    private TableColumn<Customer, String> pointsColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //set up table columns
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        
        //load data into the table
        getCustomers();
        tableView.setItems(allCustomers);
    }
    
    //add entered customer
    public void addCustomers(ActionEvent event) throws IOException{
        
        //get textfield values
        String user = username.getText();
        String pass = password.getText();
        
        //add customer to array lsit
        addCustomer(new Customer(user, pass));
        
        //reset table view and clear text fields
        tableView.setItems(allCustomers);
        username.clear();
        password.clear();
    }
    
    //delete selected customer
    public void deleteCustomer(ActionEvent event) throws IOException{
        //removes customer from table
        Customer selection = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(selection);
        
        for (Customer c : customers) {
            if (selection.getUsername().equals(c.getUsername()) && selection.getPassword().equals(c.getPassword())) 
            { selection = c; }
        }
        
        //removes customer from arrayList
        customers.remove(selection);
        
        //removes customer from file
        reset("customers.txt");
        
        FileWriter customerFile = new FileWriter("customers.txt", true);
        for(Customer c: customers){
            String outputText = c.getUsername() + ", " + c.getPassword() + ", " + c.getPoints() + "\n";
            customerFile.write(outputText);
        }
        customerFile.close();
        
    }  
  
    public void backButtonPressed(ActionEvent event) throws IOException{
        goBack(event);
    }
    
}
