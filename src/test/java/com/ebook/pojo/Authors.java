package com.ebook.pojo;

public class Authors {

    private int id;
    private int idBook;
    private String firstName;
    private String lastName;

    // Constructor
    public Authors(int id, int idBook, String firstName, String lastName) {
        this.id = id;
        this.idBook = idBook;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Default constructor
    public Authors() {
    }


    public int getId() {
        return this.id;
    }

    public int getIdBook() {
        return this.idBook;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}