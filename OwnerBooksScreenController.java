/*
DONE
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
public class OwnerBooksScreenController extends Owner implements Initializable {
    
    @FXML
    private TextField bookTitle;
    
    @FXML
    private TextField bookPrice;
    
    @FXML 
    private TableView<Book> tableView;
    
    @FXML 
    private TableColumn<Book, String> bookTitleColumn;
    
    @FXML 
    private TableColumn<Book, String> bookPriceColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //set up table columns
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        bookPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //load data into the table
        getBooks();
        tableView.setItems(books);
    }
    
    //add entered book
    public void addBookButtonPressed(ActionEvent event) throws IOException{
        
        //get textfield values
        String title = bookTitle.getText();
        double price = Double.valueOf(bookPrice.getText());
        
        //add book to array lsit
        addBook(new Book(title, price));
        
        //reset table view and clear text fields
        tableView.setItems(books);
        bookTitle.clear();
        bookPrice.clear();
    }
    
    //delete selected book
    public void deleteBookButtonPressed(ActionEvent event) throws IOException {
        //removes book from table
        Book selection = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(selection);
        
        for (Book b: availableBooks) {
            if(selection.getTitle().equals(b.getTitle()) && selection.getPrice() == b.getPrice()){
                selection = b;
            }
        }
        
        //removes book from arrayList
        availableBooks.remove(selection);
        
        //removes book from file
        reset("books.txt");
        
        FileWriter bookFile = new FileWriter("books.txt", true);
        for(Book b: availableBooks){
            String bookInfo = b.getTitle() + ", " + b.getPrice() + "\n";
            bookFile.write(bookInfo);
        }
        bookFile.close();
    }
    
    public void backButtonPressed(ActionEvent event) throws IOException{
        goBack(event);
    }
    
}
