package Deneme.SpringBoot.model;



import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
private String addressName;
private String firstName;
private String lastName;
private String country;
private String city;
private String street;
private String streetNo;
private String postCode;


@ManyToOne
@JoinColumn(name = "user_id")
User user;




    public Address(String addressName, String firstName, String lastName,
                   String country, String city, String street, String streetNo, String postCode) {
        this.addressName = addressName;
        this.firstName =firstName;
        this.lastName = lastName;
        this.country =country;
        this.city = city;
        this.street =street;
        this.streetNo = streetNo;
        this.postCode =postCode;
    }
    public Address(Long id,String addressName, String firstName, String lastName,
                   String country, String city, String street, String streetNo, String postCode) {
        this.id=id;
        this.addressName = addressName;
        this.firstName =firstName;
        this.lastName = lastName;
        this.country =country;
        this.city = city;
        this.street =street;
        this.streetNo = streetNo;
        this.postCode =postCode;
    }

    public Address(Address address) {
    }
}
