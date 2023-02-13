package src.logic;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


/**
 * Class that is mapping Ports entity to the class by the ORM conception
 */
@Entity
@Table(name = "PORTS")
public class PortsEntity {
    @Id
    @SequenceGenerator(name = "ports_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ports_id")
    @Column(name = "PORT_ID")
    private Integer portId;
    @Column(name = "PORT_NAME")
    private String portName;
    @Column(name = "PLACES_SHIPS_BIG")
    private Integer placesShipsBig;
    @Column(name = "PLACES_SHIPS_SMALL")
    private Integer placesShipsSmall;
    @Column(name = "STREET_NUMBER")
    private Integer streetNumber;
    @Column(name = "STREET_NAME")
    private String streetName;
    @Column(name = "CITY_NAME")
    private String cityName;
    @Column(name = "POST_CODE")
    private String postCode;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "VHF_CHANNEL")
    private Integer vhfChannel;
    @Column(name = "BANK_ACCOUNT")
    private String bankAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRICE_LIST_ID", referencedColumnName = "LIST_ID")
    private PriceListEntity priceListEntity;

    @ManyToMany(mappedBy = "portsEntities")
    private Collection<AllUsersEntity> allUsersEntities;

    @OneToMany(mappedBy = "portsEntity")
    private Collection<VisitsEntity> visitsEntities;

    /**
     * Instantiates a new Ports entity.
     */
    public PortsEntity()
    {}

    /**
     * Instantiates a new Ports entity.
     *
     * @param portName         the port name
     * @param placesShipsBig   the places ships big
     * @param placesShipsSmall the places ships small
     * @param streetNumber     the street number
     * @param streetName       the street name
     * @param cityName         the city name
     * @param postCode         the post code
     * @param phoneNumber      the phone number
     * @param vhfChannel       the vhf channel
     * @param bankAccount      the bank account
     * @param priceListEntity  the price list entity
     * @param allUsersEntities the all users entities
     * @param visitsEntities   the visits entities
     */
    public PortsEntity(String portName, int placesShipsBig, int placesShipsSmall, int streetNumber, String streetName, String cityName, String postCode, String phoneNumber, int vhfChannel, String bankAccount, PriceListEntity priceListEntity, Collection<AllUsersEntity> allUsersEntities, Collection<VisitsEntity> visitsEntities) {
        this.portName = portName;
        this.placesShipsBig = placesShipsBig;
        this.placesShipsSmall = placesShipsSmall;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.cityName = cityName;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.vhfChannel = vhfChannel;
        this.bankAccount = bankAccount;
        this.priceListEntity = priceListEntity;
        this.allUsersEntities = allUsersEntities;
        this.visitsEntities = visitsEntities;
    }

    /**
     * Instantiates a new Ports entity.
     *
     * @param portName         the port name
     * @param placesShipsBig   the places ships big
     * @param placesShipsSmall the places ships small
     * @param streetNumber     the street number
     * @param streetName       the street name
     * @param cityName         the city name
     * @param postCode         the post code
     * @param phoneNumber      the phone number
     * @param vhfChannel       the vhf channel
     * @param bankAccount      the bank account
     * @param priceListEntity  the price list entity
     */
    public PortsEntity(String portName, int placesShipsBig, int placesShipsSmall, int streetNumber, String streetName, String cityName, String postCode, String phoneNumber, int vhfChannel, String bankAccount, PriceListEntity priceListEntity) {
        this.portName = portName;
        this.placesShipsBig = placesShipsBig;
        this.placesShipsSmall = placesShipsSmall;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.cityName = cityName;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.vhfChannel = vhfChannel;
        this.bankAccount = bankAccount;
        this.priceListEntity = priceListEntity;
        this.allUsersEntities = new ArrayList<AllUsersEntity>();
        this.visitsEntities = new ArrayList<VisitsEntity>();
    }

    /**
     * Gets price list entity.
     *
     * @return the price list entity
     */
    public PriceListEntity getPriceListEntity() {
        return priceListEntity;
    }

    /**
     * Sets price list entity.
     *
     * @param priceListEntity the price list entity
     */
    public void setPriceListEntity(PriceListEntity priceListEntity) {
        this.priceListEntity = priceListEntity;
    }

    /**
     * Gets all users entities.
     *
     * @return the all users entities
     */
    public Collection<AllUsersEntity> getAllUsersEntities() {
        return allUsersEntities;
    }

    /**
     * Sets all users entities.
     *
     * @param allUsersEntities the all users entities
     */
    public void setAllUsersEntities(Collection<AllUsersEntity> allUsersEntities) {
        this.allUsersEntities = allUsersEntities;
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
     * Gets port id.
     *
     * @return the port id
     */
    public int getPortId() {
        return portId;
    }

    /**
     * Sets port id.
     *
     * @param portId the port id
     */
    public void setPortId(int portId) {
        this.portId = portId;
    }

    /**
     * Gets port name.
     *
     * @return the port name
     */
    public String getPortName() {
        return portName;
    }

    /**
     * Sets port name.
     *
     * @param portName the port name
     */
    public void setPortName(String portName) {
        this.portName = portName;
    }

    /**
     * Gets places ships big.
     *
     * @return the places ships big
     */
    public int getPlacesShipsBig() {
        return placesShipsBig;
    }

    /**
     * Sets places ships big.
     *
     * @param placesShipsBig the places ships big
     */
    public void setPlacesShipsBig(int placesShipsBig) {
        this.placesShipsBig = placesShipsBig;
    }

    /**
     * Gets places ships small.
     *
     * @return the places ships small
     */
    public int getPlacesShipsSmall() {
        return placesShipsSmall;
    }

    /**
     * Sets places ships small.
     *
     * @param placesShipsSmall the places ships small
     */
    public void setPlacesShipsSmall(int placesShipsSmall) {
        this.placesShipsSmall = placesShipsSmall;
    }

    /**
     * Gets street number.
     *
     * @return the street number
     */
    public int getStreetNumber() {
        return streetNumber;
    }

    /**
     * Sets street number.
     *
     * @param streetNumber the street number
     */
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * Gets street name.
     *
     * @return the street name
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets street name.
     *
     * @param streetName the street name
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * Gets city name.
     *
     * @return the city name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets city name.
     *
     * @param cityName the city name
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Gets post code.
     *
     * @return the post code
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets post code.
     *
     * @param postCode the post code
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
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
     * Gets vhf channel.
     *
     * @return the vhf channel
     */
    public int getVhfChannel() {
        return vhfChannel;
    }

    /**
     * Sets vhf channel.
     *
     * @param vhfChannel the vhf channel
     */
    public void setVhfChannel(int vhfChannel) {
        this.vhfChannel = vhfChannel;
    }

    /**
     * Gets bank account.
     *
     * @return the bank account
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * Sets bank account.
     *
     * @param bankAccount the bank account
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortsEntity that = (PortsEntity) o;
        return portId == that.portId && placesShipsBig == that.placesShipsBig && placesShipsSmall == that.placesShipsSmall && streetNumber == that.streetNumber && vhfChannel == that.vhfChannel && priceListEntity == that.priceListEntity && Objects.equals(portName, that.portName) && Objects.equals(streetName, that.streetName) && Objects.equals(cityName, that.cityName) && Objects.equals(postCode, that.postCode) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(bankAccount, that.bankAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portId, portName, placesShipsBig, placesShipsSmall, streetNumber, streetName, cityName, postCode, phoneNumber, vhfChannel, bankAccount, priceListEntity);
    }

}
