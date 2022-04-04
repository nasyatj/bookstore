/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import static coe528project.BookStore.availableBooks;
import static coe528project.BookStore.customers;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nasyajames
 */
public class Main extends Application {
    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        //load arrays
        try {
            loadArrays();
        } catch (IOException ex) {
            System.out.println("Error loading data");
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml")); 
        Scene scene = new Scene(root);
        
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("BookStore");
        stage.show();
        
        stage.setOnCloseRequest(e -> {
            try {
                saveFiles();
            } catch (IOException ex) {
                System.out.println("error closing program");
            }
        });
    }
    
    //change between screens
    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene2 = new Scene(pane);
        stage.setScene(scene2);
        stage.setResizable(false);
    }
    
    //load info from files into arrays
    public void loadArrays() throws IOException {
        readBookFile();
        readCustomerFile();
    }
    
    //reads in from file puts everything in a temp object array and sends array to be copied from to actual array
    public void readBookFile() throws IOException{
        Scanner scan = new Scanner(new FileReader("books.txt"));

        while(scan.hasNext()) {
            String[] bookInformation = scan.nextLine().split(",");
            String title = bookInformation[0];
            double price = Double.parseDouble(bookInformation[1]);
            availableBooks.add(new Book(title, price));
        }
    }

    //reads in from file puts everything in a temp object array and sends array to be copied from to actual array
    public void readCustomerFile() throws IOException{
        Scanner scan = new Scanner(new FileReader("customers.txt"));

        while(scan.hasNext()) {
            String[] customerInformation = scan.nextLine().split(", ");
            String user = customerInformation[0];
            String pass = customerInformation[1];
            int points = Integer.parseInt(customerInformation[2]);
            customers.add(new Customer(user, pass));
            customers.get(customers.size()-1).setPoints(points);
        }
    }
    
    //save data from arrays to files on exit
    public void saveFiles() throws IOException{
        FileWriter file = new FileWriter("books.txt", false);
        file.close();
        
        FileWriter cFile = new FileWriter("customers.txt", false);
        cFile.close();
        
        FileWriter bookFile = new FileWriter("books.txt", true);
        for(Book b: availableBooks){
            String bookInfo = b.getTitle() + ", " + b.getPrice() + "\n";
            bookFile.write(bookInfo);
        }
        bookFile.close();
        
         FileWriter customerFile = new FileWriter("customers.txt", true);
        for(Customer c: customers){
            String outputText = c.getUsername() + ", " + c.getPassword() + ", " + c.getPoints() + "\n";
            customerFile.write(outputText);
        }
        customerFile.close();

    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
