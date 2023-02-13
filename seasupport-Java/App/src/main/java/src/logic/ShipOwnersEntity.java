package src.logic;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


/**
 * Class that is mapping Ship_Owners entity to the class by the ORM conception
 */
@Entity
@Table(name = "SHIP_OWNERS")
public class ShipOwnersEntity {
    @Id
    @SequenceGenerator(name = "ship_owners_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ship_owners_id")
    @Column(name = "SHIP_OWNER_ID")
    private Integer shipOwnerId;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FORENAME")
    private String forname;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "PESEL")
    private String pesel;
    @Column(name = "NAME_COMPANY")
    private String nameCompany;
    @Column(name = "NIP")
    private Integer nip;

    @OneToMany(mappedBy = "shipOwnersEntity")
    private Collection<ShipsEntity> shipsEntities;

    /**
     * Instantiates a new Ship owners entity.
     *
     * @param phoneNumber   the phone number
     * @param email         the email
     * @param forename      the forename
     * @param surname       the surname
     * @param pesel         the pesel
     * @param shipsEntities the ships entities
     */
    public ShipOwnersEntity(String phoneNumber, String email, String forename,
                            String surname, String pesel, Collection<ShipsEntity> shipsEntities) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.forname = forename;
        this.surname = surname;
        this.pesel = pesel;
        this.shipsEntities = shipsEntities;
    }

    /**
     * Instantiates a new Ship owners entity.
     *
     * @param phoneNumber the phone number
     * @param email       the email
     * @param forename    the forename
     * @param surname     the surname
     * @param pesel       the pesel
     */
    public ShipOwnersEntity(String phoneNumber, String email, String forename,
                            String surname, String pesel) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.forname = forename;
        this.surname = surname;
        this.pesel = pesel;
    }

    /**
     * Instantiates a new Ship owners entity.
     *
     * @param email       the email
     * @param nameCompany the name company
     * @param nip         the nip
     * @param phoneNumber the phone number
     */
    public ShipOwnersEntity(String email, String nameCompany, Integer nip, String phoneNumber) {
        this.email = email;
        this.nameCompany = nameCompany;
        this.nip = nip;
        this.shipsEntities = new ArrayList<>();
        this.phoneNumber = phoneNumber;
    }

    /**
     * Instantiates a new Ship owners entity.
     */
    public ShipOwnersEntity() {
    }

    /**
     * Add ship.
     *
     * @param ship the ship
     */
    public void addShip(ShipsEntity ship){
        this.shipsEntities.add(ship);
    }

    /**
     * Gets ships entities.
     *
     * @return the ships entities
     */
    public Collection<ShipsEntity> getShipsEntities() {
        return shipsEntities;
    }

    /**
     * Sets ships entities.
     *
     * @param shipsEntities the ships entities
     */
    public void setShipsEntities(Collection<ShipsEntity> shipsEntities) {
        this.shipsEntities = shipsEntities;
    }

    /**
     * Gets ship owner id.
     *
     * @return the ship owner id
     */
    public Integer getShipOwnerId() {
        return shipOwnerId;
    }

    /**
     * Sets ship owner id.
     *
     * @param shipOwnerId the ship owner id
     */
    public void setShipOwnerId(Integer shipOwnerId) {
        this.shipOwnerId = shipOwnerId;
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets forname.
     *
     * @return the forname
     */
    public String getForname() {
        return forname;
    }

    /**
     * Sets forname.
     *
     * @param forname the forname
     */
    public void setForname(String forname) {
        this.forname = forname;
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
     * Gets name company.
     *
     * @return the name company
     */
    public String getNameCompany() {
        return nameCompany;
    }

    /**
     * Sets name company.
     *
     * @param nameCompany the name company
     */
    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    /**
     * Gets nip.
     *
     * @return the nip
     */
    public Integer getNip() {
        return nip;
    }

    /**
     * Sets nip.
     *
     * @param nip the nip
     */
    public void setNip(Integer nip) {
        this.nip = nip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipOwnersEntity that = (ShipOwnersEntity) o;
        return shipOwnerId == that.shipOwnerId && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email) && Objects.equals(forname, that.forname) && Objects.equals(surname, that.surname) && Objects.equals(pesel, that.pesel) && Objects.equals(nameCompany, that.nameCompany) && Objects.equals(nip, that.nip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipOwnerId, phoneNumber, email, forname, surname, pesel, nameCompany, nip);
    }
}
