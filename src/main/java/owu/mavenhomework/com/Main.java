package owu.mavenhomework.com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import owu.mavenhomework.com.enums.CarTypeEnum;
import owu.mavenhomework.com.models.Car;
import owu.mavenhomework.com.models.DriverLicense;
import owu.mavenhomework.com.models.Owner;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build()) {
            Metadata metadata = new MetadataSources(serviceRegistry)
                    .addAnnotatedClass(Owner.class)
                    .addAnnotatedClass(Car.class)
                    .addAnnotatedClass(DriverLicense.class)
                    .getMetadataBuilder()
                    .build();

            try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
                 Session session = sessionFactory.openSession()) {

                session.getTransaction().begin();

                DriverLicense stefansDriverLicense = new DriverLicense();
                stefansDriverLicense.setSeries("11sj49830aa");

                DriverLicense kristinasDriverLicense = new DriverLicense();
                kristinasDriverLicense.setSeries("12dne09dyrnt");

                Car car1 = new Car();
                car1.setModel("Toyota Supra");
                car1.setYear(1996);
                car1.setPrice(70000);
                car1.setType(CarTypeEnum.FUEL);
                car1.setPower(3000);

                Car car2 = new Car();
                car2.setModel("Mercedes CLS");
                car2.setYear(2022);
                car2.setPrice(63000);
                car2.setType(CarTypeEnum.HYBRID);
                car2.setPower(740);

                Car car3 = new Car();
                car3.setModel("BMW i3");
                car3.setYear(2020);
                car3.setPrice(24000);
                car3.setType(CarTypeEnum.ELECTRIC);
                car3.setPower(340);

                Owner stefan = new Owner();
                stefan.setName("Stefan");
                stefan.setCars(List.of(car1, car2));
                stefan.setDriverLicense(stefansDriverLicense);

                Owner max = new Owner();
                max.setName("Max");
                max.setCars(List.of(car2));
                max.setDriverLicense(kristinasDriverLicense);

                Owner kokos = new Owner();
                kokos.setName("Kokos");
                kokos.setCars(List.of(car1, car2, car3));

                session.persist(stefan);
                session.persist(kokos);
                session.persist(max);

                session.getTransaction().commit();
            }
        }
    }
}
