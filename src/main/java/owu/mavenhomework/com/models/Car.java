package owu.mavenhomework.com.models;

import jakarta.persistence.*;
import lombok.Data;
import owu.mavenhomework.com.enums.CarTypeEnum;

@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Enumerated(EnumType.STRING)
    private CarTypeEnum type;

    @Column(name = "power")
    private int power;

    @Column(name = "price")
    private int price;

    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
