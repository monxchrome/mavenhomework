package owu.mavenhomework.com.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "driver_license")
public class DriverLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "series", unique = true)
    private String series;
}
