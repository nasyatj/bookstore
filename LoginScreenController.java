/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nasyajames
 */
public class LoginScreenController extends BookStore implements Initializable {
    @FXML
    private TextField user;
    
    @FXML
    private TextField pass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    //send entered info to login method in BookStore
    public void loginButtonPressed(ActionEvent event) throws IOException
    {
        login(user.getText(), pass.getText());
    }
    
}
