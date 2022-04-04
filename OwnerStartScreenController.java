/*
DONE
 */
package coe528project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author nasyajames
 */
public class OwnerStartScreenController extends BookStore implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void BooksButtonPressed(ActionEvent event) throws IOException
    {
        sceneChange.changeScene("OwnerBooksScreen.fxml");
    }
    
    public void CustomerButtonPressed(ActionEvent event) throws IOException
    {
        sceneChange.changeScene("OwnerCustomerScreen.fxml");
    }
    
    public void logoutButtonPressed(ActionEvent event) throws IOException
    {
        logout(event);
    }
    
}
