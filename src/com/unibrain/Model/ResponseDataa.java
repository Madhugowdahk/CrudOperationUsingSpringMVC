package com.unibrain.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "response_data")
public class ResponseDataa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Add a primary key field

    @Column(name = "message")
    private String message;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "number")
    private long number;

    @Column(name = "age")
    private int age;

    // Default constructor
    public ResponseDataa() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ResponseData [id=" + id + ", message=" + message + ", name=" + name + ", email=" + email + ", number=" + number + ", age=" + age + "]";
    }
}
