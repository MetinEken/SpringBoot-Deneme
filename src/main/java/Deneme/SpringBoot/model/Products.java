package Deneme.SpringBoot.model;

import javax.persistence.*;

@Entity
@Table
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String fe1;
    private String fe2;
    private String fe3;

    public Products(Long id, String name, String type, String fe1, String fe2, String fe3) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFe1() {
        return fe1;
    }

    public void setFe1(String fe1) {
        this.fe1 = fe1;
    }

    public String getFe2() {
        return fe2;
    }

    public void setFe2(String fe2) {
        this.fe2 = fe2;
    }

    public String getFe3() {
        return fe3;
    }

    public void setFe3(String fe3) {
        this.fe3 = fe3;
    }



    public Products(String name, String type, String fe1, String fe2, String fe3) {
        this.name = name;
        this.type = type;
        this.fe1= fe1;
        this.fe2= fe2;
        this.fe3= fe3;
    }

    public Products() {

    }

}
