package owu.mavenhomework2.com.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import owu.mavenhomework2.com.enums.CarEnum;

@Data
@Entity
@Table(name = "cars", schema = "public")
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "model")
    String model;

    @Column(name = "type")
    CarEnum type;

    @Column(name = "power")
    int power;

    @Column(name = "price")
    int price;

    @Column(name = "year")
    int year;
}
