package com.example.homeservices;

public class Book {
    public String Email, Phone, Location, Date,worker_name,worker_Profession;

    public Book(){

    }

    public Book(String Email, String Phone, String Location, String Date,String worker_name,String worker_Profession) {
        this.Email = Email;
        this.Phone = Phone;
        this.Location = Location;
        this.Date = Date;
        this.worker_name = worker_name;
        this.worker_Profession = worker_Profession;
    }
}
