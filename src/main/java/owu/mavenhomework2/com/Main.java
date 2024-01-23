package owu.mavenhomework2.com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import owu.mavenhomework2.com.enums.CarEnum;
import owu.mavenhomework2.com.models.Car;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build()) {
            Metadata metadata = new MetadataSources(serviceRegistry).addAnnotatedClass(Car.class).getMetadataBuilder().build();

            try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
                 Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                Car car = new Car();
                car.setYear(1997);
                car.setType(CarEnum.FUEL);
                car.setPrice(30000);
                car.setPower(2500);
                car.setModel("Toyota Supra");

                Car car2 = new Car();
                car.setYear(2022);
                car.setType(CarEnum.HYBRID);
                car.setPrice(45000);
                car.setPower(950);
                car.setModel("Mercedes Benz");

                session.persist(car);
                session.persist(car2);

                session.getTransaction().commit();

                System.out.println("Cars has been saved in database");

                List<Car> list = session.createNativeQuery("select * from public.cars", Car.class).list();

                System.out.println(list);
            }
        }
    }
}
