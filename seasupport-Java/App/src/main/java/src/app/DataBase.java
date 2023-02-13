package src.app;


import jakarta.persistence.PersistenceException;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.hibernate.query.sql.internal.SQLQueryParser;
import src.logic.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.sql.Date;
import java.util.List;


/**
 * Class responsible for connecting to database -
 * fetching/pulling data to/from it.
 * <p>
 * Design pattern singleton - you can get the object only from the method getInstance()
 *
 * @author Michal
 */
public class DataBase {

    private static DataBase instance;

    private static Configuration configuration;

    private static StandardServiceRegistry serviceRegistry;

    private static SessionFactory sessionFactory;

    /**
     * Constructor calls the method configureConnection()
     * to set configuration for connection with a database
     */
    private DataBase()
    {
        configureConnection();
    }

    /**
     * Method that sets configuration for connection
     * to the database
     */
    private void configureConnection()
    {
        configuration = new Configuration().configure();
        configuration.addAnnotatedClass(CaptainsEntity.class);
        configuration.addAnnotatedClass(AllUsersEntity.class);
        configuration.addAnnotatedClass(PortsEntity.class);
        configuration.addAnnotatedClass(PriceListEntity.class);
        configuration.addAnnotatedClass(ShipOwnersEntity.class);
        configuration.addAnnotatedClass(ShipsEntity.class);
        configuration.addAnnotatedClass(VisitsEntity.class);
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    }

    /**
     * Method responsible for disabling connection after closing the application
     */
    public static void disableConnection()
    {
        sessionFactory.close();
    }

    /**
     * Method responsible on the first use for creation the DataBase object
     * and then for supplying the object to whoever needs it
     *
     * @return DataBase instance
     */
    public static DataBase getInstance() {
        if (instance == null)
        {
            instance = new DataBase();
        }
        return instance;
    }


    /**
     * Method responsible for fetching data from database
     * from the PORTS
     *
     * @param portId the port id
     * @return PortsEntity port
     */
    public PortsEntity getPort(int portId)
    {
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        PortsEntity portsEntity = ss.get(PortsEntity.class, portId);
        tx.commit();
        ss.close();
        return portsEntity;
    }

    /**
     * Method responsible for fetching data from database
     * from the PORTS
     *
     * @param name the name
     * @return PortsEntity port
     */
    public PortsEntity getPort(String name)
    {
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        Query query = ss.createQuery("from PortsEntity where portName = :name");
        query.setParameter("name", name);
        PortsEntity portsEntity = (PortsEntity) query.uniqueResult();
        tx.commit();
        ss.close();
        return portsEntity;
    }

    /**
     * Method responsible for fetching data from database
     * from the PORTS
     *
     * @return List  ports
     */
    public List<PortsEntity> getPorts(){
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        List<PortsEntity> ports = ss.createQuery("from PortsEntity").getResultList();
        //List<PortsEntity> ports = ss.createQuery("FROM PORTS").getResultList();
        tx.commit();
        ss.close();
        return ports;
    }


    /**
     * Method responsible for fetching data from database
     * from the ALL_USERS table
     *
     * @param login the login
     * @return AllUsersEntity user
     */
    public AllUsersEntity getUser(String login)
    {
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        AllUsersEntity allUsersEntity = ss.get(AllUsersEntity.class, login);
        tx.commit();
        ss.close();
        return allUsersEntity;
    }

    /**
     * Fetch visit with given user and data_begin
     *
     * @param user      the user
     * @param dateBegin the date begin
     * @return VisitsEntity visit
     */
    public VisitsEntity getVisit(AllUsersEntity user, Date dateBegin) {
        Session ss = sessionFactory.openSession();
        ss.beginTransaction();

        Query query = ss.createQuery("FROM VisitsEntity VE WHERE allUsersEntity = :user " +
                "AND :dateBegin BETWEEN VE.dateBegin AND VE.dateEnd");
        query.setParameter("user", user);
        query.setParameter("dateBegin", dateBegin);
        VisitsEntity visit = (VisitsEntity) query.uniqueResult();
        ss.getTransaction().commit();
        ss.close();
        return visit;
    }

    /**
     * Fetch visit with given callSign and data_begin
     *
     * @param ship      ShipsEntity
     * @param dateBegin Date
     * @return VisitsEntity visits entity
     */
    public VisitsEntity getVisit(ShipsEntity ship, Date dateBegin){
        Session ss = sessionFactory.openSession();
        ss.beginTransaction();

        Query query = ss.createQuery("FROM VisitsEntity VE WHERE shipsEntity = :ship " +
                "AND :dateBegin BETWEEN VE.dateBegin AND VE.dateEnd");
        query.setParameter("ship", ship);
        query.setParameter("dateBegin", dateBegin);
        VisitsEntity visit = (VisitsEntity) query.uniqueResult();
        ss.getTransaction().commit();
        ss.close();
        return visit;
    }

    /**
     * Gets a list of VistsEntities from a port after given date
     *
     * @param port      the port
     * @param dateBegin the date begin
     * @return List  visit from port 1
     */
    public List<VisitsEntity> getVisitFromPort_1(PortsEntity port, Date dateBegin)
    {
        Session ss = sessionFactory.openSession();
        ss.beginTransaction();

        Query query = ss.createQuery("FROM VisitsEntity VE WHERE portsEntity = :port " +
                                     "and VE.dateBegin >= :dateBegin");
        query.setParameter("port", port);
        query.setParameter("dateBegin", dateBegin);
        List<VisitsEntity> visits = query.list();
        ss.getTransaction().commit();
        ss.close();
        return visits;
    }

    /**
     * Gets a list of VistsEntities from a port after given date
     *
     * @param port      the port
     * @param dateBegin the date begin
     * @return List visit from port
     */
    public List<VisitsEntity> getVisitFromPort(PortsEntity port, Date dateBegin)
    {
        Session ss = sessionFactory.openSession();
        ss.beginTransaction();

        Query query = ss.createQuery("FROM VisitsEntity VE WHERE portsEntity = :port " +
                "AND :dateBegin BETWEEN VE.dateBegin AND VE.dateEnd");
        query.setParameter("port", port);
        query.setParameter("dateBegin", dateBegin);
        List<VisitsEntity> visits = query.list();
        ss.getTransaction().commit();
        ss.close();
        return visits;
    }

    /**
     * Gets a list of VistsEntities witch will start between dateBegin and dateEnd
     *
     * @param port      the port
     * @param dateBegin the date begin
     * @param dateEnd   the date end
     * @return List list of ports
     */
    public List<VisitsEntity> getVisitByPortBetween(PortsEntity port, Date dateBegin, Date dateEnd){
        Session ss = sessionFactory.openSession();
        ss.beginTransaction();

        Query query = ss.createQuery("FROM VisitsEntity VE WHERE portsEntity = :port " +
                "AND VE.dateBegin BETWEEN :dateBegin AND :dateEnd");
        query.setParameter("port", port);
        query.setParameter("dateBegin", dateBegin);
        query.setParameter("dateEnd", dateEnd);
        List<VisitsEntity> visits = query.list();
        ss.getTransaction().commit();
        ss.close();
        return visits;
    }

    /**
     * Gets a list of actually VistsEntities from this port.
     *
     * @param port the port
     * @return List  list
     */
    public List<VisitsEntity> getVisitByPort(PortsEntity port){
        Session ss = sessionFactory.openSession();
        ss.beginTransaction();

        Query query = ss.createQuery("FROM VisitsEntity WHERE portsEntity = :port ");
        query.setParameter("port", port);
        List<VisitsEntity> visits = query.list();
        ss.getTransaction().commit();
        ss.close();
        return visits;
    }

    /**
     * Removes visit from the data base
     *
     * @param visit the visit
     */
    public void removeVisit(VisitsEntity visit)
    {
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        ss.remove(visit);
        tx.commit();
        ss.close();
    }

    /**
     * Fetches all ports in the database
     *
     * @return List  all ports
     */
    public List<PortsEntity> getAllPorts()
    {
        Session ss = sessionFactory.openSession();
        ss.beginTransaction();

        Query query = ss.createQuery("from PortsEntity");
        List<PortsEntity> ports = query.list();
        ss.getTransaction().commit();
        ss.close();
        return ports;
    }

    /**
     * Get ShipsEntity from database
     *
     * @param callSign the call sign
     * @return the ships entity
     */
    public ShipsEntity getShip(String callSign){
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        ShipsEntity s = ss.get(ShipsEntity.class, callSign);
        tx.commit();
        ss.close();
        return s;
    }

    /**
     * Adds ShipsEntity to the database
     *
     * @param ship the ship
     */
    public void addShip(ShipsEntity ship)
    {
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();

        ss.saveOrUpdate(ship.getShipOwnersEntity());
        if (!ship.getVisitsEntities().isEmpty())
        {
            for (var visit : ship.getVisitsEntities())
                ss.saveOrUpdate(visit);
        }
        ss.saveOrUpdate(ship);
        tx.commit();
        ss.close();
    }

    /**
     * Adds ShipsEntity to the database and sets its shipowner to
     * that one with given pesel
     *
     * @param ship  the ship
     * @param pesel the pesel
     */
    public void addShip(ShipsEntity ship, String pesel)
    {
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        Query query = ss.createQuery("from ShipOwnersEntity where pesel = :pesel");
        query.setParameter("pesel", pesel);
        ShipOwnersEntity shipOwner = (ShipOwnersEntity) query.uniqueResult();
        ship.setShipOwnersEntity(shipOwner);
        ss.saveOrUpdate(shipOwner);
        ss.saveOrUpdate(ship);
        tx.commit();
        ss.close();
    }

    /**
     * Method responsible for pulling data to database
     * to the ALL_USERS table
     *
     * @param allUsers the all users
     */
    public void addUser(AllUsersEntity allUsers)
    {
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        if (!allUsers.getPortsEntities().isEmpty()){
            for (var port : allUsers.getPortsEntities())
                ss.saveOrUpdate(port);
        }
        if (!allUsers.getVisitsEntities().isEmpty()){
            for (var visit : allUsers.getVisitsEntities())
                ss.saveOrUpdate(visit);
        }
        ss.saveOrUpdate(allUsers);
        tx.commit();
        ss.close();
    }


    /**
     * Method responsible for pulling data to database
     * to the CAPTAINS table
     *
     * @param cap the cap
     */
    public void addCaptain(CaptainsEntity cap) throws PersistenceException
    {
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        ss.saveOrUpdate(cap);
        tx.commit();
        ss.close();
    }

    /**
     * Get CaptainsEntity from database
     *
     * @param id the id
     * @return the captains entity
     */
    public CaptainsEntity getCaptain(int id){
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        CaptainsEntity captian = ss.get(CaptainsEntity.class, id);
        tx.commit();
        ss.close();
        return captian;
    }

    /**
     * Adds new visit to the database
     *
     * @param visit the visit
     */
    public void addVisit(VisitsEntity visit)
    {
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        this.addUser(visit.getAllUsersEntity());
        ss.saveOrUpdate(visit.getPortsEntity());
        this.addShip(visit.getShipsEntity());
        ss.saveOrUpdate(visit.getCaptainsEntity());
        ss.saveOrUpdate(visit);
        tx.commit();
        ss.close();
    }

    /**
     * Get PriceListEntity from database
     *
     * @param listId the list id
     * @return the price list entity
     */
    public PriceListEntity getPriceList(int listId){
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        PriceListEntity priceList = ss.get(PriceListEntity.class, listId);
        tx.commit();
        ss.close();
        return priceList;
    }

    /**
     * Get ShipOwnersEntity from database
     *
     * @param id the id
     * @return the ship owners entity
     */
    public ShipOwnersEntity getOwner(String id){
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        ShipOwnersEntity s = ss.get(ShipOwnersEntity.class, id);
        tx.commit();
        ss.close();
        return s;
    }

    /**
     * Adds get owner by email from the database
     *
     * @param email String
     * @return the ship owners entity
     */
    public ShipOwnersEntity getOwnerByEmail(String email){
        Session ss = sessionFactory.openSession();
        ss.beginTransaction();

        Query query = ss.createQuery("FROM ShipOwnersEntity WHERE email = :email ");
        query.setParameter("email", email);
        ShipOwnersEntity owner = (ShipOwnersEntity) query.uniqueResult();
        ss.getTransaction().commit();
        ss.close();
        return owner;
    }

    /**
     * Adds new owner to the database
     *
     * @param owner the owner
     */
    public void addOwner(ShipOwnersEntity owner) throws PersistenceException{
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        ss.persist(owner);
        tx.commit();
        ss.close();
    }
    }
