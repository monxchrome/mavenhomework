package owu.mavenhomework.com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import owu.mavenhomework.com.models.Word;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateDemo {
    public static void main(String[] args) {
        try(StandardServiceRegistry serviceRegistryBuilder = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build()) {
            Metadata metadata = new MetadataSources(serviceRegistryBuilder)
                    .addAnnotatedClass(Word.class)
                    .getMetadataBuilder()
                    .build();

            try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
                 Session session = sessionFactory.openSession()) {

                session.beginTransaction();

                Word word = new Word();
                word.setValue("asd");
                Word word2 = new Word();
                word2.setValue("qwe");
                Word word3 = new Word();
                word3.setValue("hello");
                Word word4 = new Word();
                word4.setValue("okten");
                Word word5 = new Word();
                word5.setValue("zxc");

                session.persist(word);
                session.persist(word2);
                session.persist(word3);
                session.persist(word4);
                session.persist(word5);

                session.getTransaction().commit();

                System.out.println("Words has been saved in database");

                List<Word> words = session.createNativeQuery("select * from public.words", Word.class).list();

                var result = words.stream().map(Word::getValue).collect(Collectors.toList());
                System.out.println(result);
            }
        }
    }
}
