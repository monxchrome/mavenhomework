package owu.mavenhomework.com.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Car> cars;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DriverLicense driverLicense;

    public void setCars(List<Car> cars) {
        this.cars = cars;
        cars.forEach(car -> car.setOwner(this));
    }
}
