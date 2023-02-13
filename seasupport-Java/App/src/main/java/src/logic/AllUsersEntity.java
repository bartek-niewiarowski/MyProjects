package src.logic;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


/**
 * Class that is mapping All_Users entity to the class by the ORM conception
 */
@Entity
@Table(name = "ALL_USERS")
public class AllUsersEntity {
    @Id
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Basic
    @Column(name = "FORENAME")
    private String forename;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "BIRTHDAY")
    private Date birthday;
    @Column(name = "PESEL")
    private String pesel;
    @Column(name = "USER_TYPE")
    private String userType;

    /**
     * Instantiates a new All users entity.
     */
    public AllUsersEntity()
    {}

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "ADMIN_PORT_INTERMEDIARY",
            joinColumns = { @JoinColumn(name = "LOGIN") },
            inverseJoinColumns = { @JoinColumn(name = "PORT_ID") }
    )
    private Collection<PortsEntity> portsEntities;

    @OneToMany(mappedBy = "allUsersEntity", fetch = FetchType.EAGER)
    private Collection<VisitsEntity> visitsEntities;

    /**
     * Instantiates a new All users entity.
     *
     * @param login        the login
     * @param userPassword the user password
     * @param forename     the forename
     * @param surname      the surname
     * @param phoneNumber  the phone number
     * @param birthday     the birthday
     * @param pesel        the pesel
     * @param userType     the user type
     */
    public AllUsersEntity(String login, String userPassword, String forename, String surname, String phoneNumber, Date birthday, String pesel, String userType) {
        this.login = login;
        this.userPassword = userPassword;
        this.forename = forename;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.pesel = pesel;
        this.userType = userType;
        this.portsEntities = new ArrayList<PortsEntity>();
        this.visitsEntities = new ArrayList<VisitsEntity>();
    }

    /**
     * Instantiates a new All users entity.
     *
     * @param login        the login
     * @param userPassword the user password
     * @param forename     the forename
     * @param surname      the surname
     * @param phoneNumber  the phone number
     * @param birthday     the birthday
     * @param userType     the user type
     */
    public AllUsersEntity(String login, String userPassword, String forename, String surname, String phoneNumber, Date birthday, String userType) {
        this.login = login;
        this.userPassword = userPassword;
        this.forename = forename;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.userType = userType;
        this.portsEntities = new ArrayList<PortsEntity>();
        this.visitsEntities = new ArrayList<VisitsEntity>();
    }

    /**
     * Gets ports entities.
     *
     * @return the ports entities
     */
    public Collection<PortsEntity> getPortsEntities() {
        return portsEntities;
    }

    /**
     * Sets ports entities.
     *
     * @param portsEntities the ports entities
     */
    public void setPortsEntities(Collection<PortsEntity> portsEntities) {
        this.portsEntities = portsEntities;
    }

    /**
     * Gets visits entities.
     *
     * @return the visits entities
     */
    public Collection<VisitsEntity> getVisitsEntities() {
        return visitsEntities;
    }

    /**
     * Sets visits entities.
     *
     * @param visitsEntities the visits entities
     */
    public void setVisitsEntities(Collection<VisitsEntity> visitsEntities) {
        this.visitsEntities = visitsEntities;
    }

    /**
     * Add port.
     *
     * @param port the port
     */
    public void addPort(PortsEntity port)
    {
        this.portsEntities.add(port);
    }

    /**
     * Add visit.
     *
     * @param visit the visit
     */
    public void addVisit(VisitsEntity visit)
    {
        this.visitsEntities.add(visit);
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets user password.
     *
     * @return the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets user password.
     *
     * @param userPassword the user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Gets forename.
     *
     * @return the forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * Sets forename.
     *
     * @param forename the forename
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets birthday.
     *
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Sets birthday.
     *
     * @param birthday the birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Gets pesel.
     *
     * @return the pesel
     */
    public String getPesel() {
        return pesel;
    }

    /**
     * Sets pesel.
     *
     * @param pesel the pesel
     */
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    /**
     * Gets user type.
     *
     * @return the user type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets user type.
     *
     * @param userType the user type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllUsersEntity that = (AllUsersEntity) o;
        return Objects.equals(login, that.login) && Objects.equals(userPassword, that.userPassword) && Objects.equals(forename, that.forename) && Objects.equals(surname, that.surname) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(birthday, that.birthday) && Objects.equals(pesel, that.pesel) && Objects.equals(userType, that.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, userPassword, forename, surname, phoneNumber, birthday, pesel, userType);
    }
}
