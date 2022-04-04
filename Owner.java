/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

/**
 *
 * @author nasyajames
 */
import java.io.FileWriter;
import java.io.IOException;


public class Owner extends BookStore{

    //add customer to all array lists and to file
    public void addCustomer(Customer c){
        customers.add(c);
        allCustomers.add(c);
        
        //add to file
        try{
            writeCustomer(c);
            System.out.println("Arrays restocked from files");
        }
        catch (IOException e){
            System.out.println("File Importing Error");
        }
    }

    //add book to all array lists and to file
    public void addBook(Book b){
        availableBooks.add(b);
        books.add(b);
        
        //add to file
        try{
            writeBooks(b);
            System.out.println("Arrays restocked from files");
        }
        catch (IOException e){
            System.out.println("File Importing Error");
        }
    }
   
    
    //this copies a specified book into a the books.txt file
    public void writeBooks(Book b) throws IOException{
        FileWriter bookFile = new FileWriter("books.txt", true);
        
        String bookInfo = b.getTitle() + ", " + b.getPrice() + "\n";
        bookFile.write(bookInfo);
        
        bookFile.close();
    }


    //this copies a specified customer into a the customer.txt file
    public void writeCustomer(Customer c) throws IOException{
        FileWriter customerFile = new FileWriter("customers.txt", true);
        
        String outputText = c.getUsername() + ", " + c.getPassword() + ", " + c.getPoints() + "\n";
        customerFile.write(outputText);
        
        customerFile.close();
    }
}

