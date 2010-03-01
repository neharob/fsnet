package fr.univartois.ili.fsnet.entities.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.fsnet.entities.Community;
import fr.univartois.ili.fsnet.entities.Hub;
import fr.univartois.ili.fsnet.entities.SocialEntity;
import fr.univartois.ili.fsnet.entities.Topic;
import fr.univartois.ili.fsnet.entities.TopicMessage;

public class TopicTest {

    private EntityManager em;

    @Before
    public void setUp() {
        EntityManagerFactory fact = Persistence.createEntityManagerFactory("TestPU");
        em = fact.createEntityManager();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test to check if it's possible to persist a Topic
     */
    @Test
    public void testPersist() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        Date date = null;
        try {
            date = (Date) formatter.parse("29/01/02");
        } catch (ParseException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "", e);
        }

        SocialEntity es = new SocialEntity("Ragoût", "Mouton", "RagoûtMouton@toiaussitafaim.com");
        es.setName("Théophile");
        es.setFisrtname("Gautier");
        final Community community = new Community(es, "macom");
        Hub hub = new Hub(community, es, "mon hub");
        Topic top = new Topic(hub, es, "mon topic");
        TopicMessage firstmessage = new TopicMessage("kiiiii", es, top);
        top.getMessages().add(firstmessage);
        em.getTransaction().begin();
        em.persist(es);
        em.persist(community);
        em.persist(hub);
        em.persist(top);
        em.getTransaction().commit();
    }
}