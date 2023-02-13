package src.logic;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


/**
 * Class that is mapping Ships entity to the class by the ORM conception
 */
@Entity
@Table(name = "SHIPS")
public class ShipsEntity {
    @Id
    @Column(name = "CALL_SIGN")
    private String callSign;
    @Column(name = "SHIP_NAME")
    private String shipName;
    @Column(name = "SHIP_TYPE")
    private String shipType;
    @Column(name = "SHIP_LENGTH")
    private Short shipLength;
    @ManyToOne
    @JoinColumn(name = "SHIP_OWNER_ID", referencedColumnName = "SHIP_OWNER_ID")
    private ShipOwnersEntity shipOwnersEntity;
    @OneToMany(mappedBy = "shipsEntity", fetch = FetchType.EAGER)
    private Collection<VisitsEntity> visitsEntities;

    /**
     * Instantiates a new Ships entity.
     *
     * @param callSign         the call sign
     * @param shipName         the ship name
     * @param shipType         the ship type
     * @param shipLength       the ship length
     * @param shipOwnersEntity the ship owners entity
     */
    public ShipsEntity(String callSign, String shipName, String shipType, short shipLength, ShipOwnersEntity shipOwnersEntity) {
        this.callSign = callSign;
        this.shipName = shipName;
        this.shipType = shipType;
        this.shipLength = shipLength;
        this.shipOwnersEntity = shipOwnersEntity;
        this.visitsEntities = new ArrayList<VisitsEntity>();
    }

    /**
     * Instantiates a new Ships entity.
     */
    public ShipsEntity() {
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
     * Instantiates a new Ships entity.
     *
     * @param callSign                the call sign
     * @param shpipName               the shpip name
     * @param shipOwnerId             the ship owner id
     * @param shipType                the ship type
     * @param shipLength              the ship length
     * @param shipOwnersByShipOwnerId the ship owners by ship owner id
     */
    public ShipsEntity(String callSign, String shpipName, int shipOwnerId, String shipType, short shipLength, ShipOwnersEntity shipOwnersByShipOwnerId) {
        this.callSign = callSign;
        this.shipName = shpipName;
        this.shipType = shipType;
        this.shipLength = shipLength;
        this.shipOwnersEntity = shipOwnersByShipOwnerId;
    }

    /**
     * Gets call sign.
     *
     * @return the call sign
     */
    public String getCallSign() {
        return callSign;
    }

    /**
     * Sets call sign.
     *
     * @param callSign the call sign
     */
    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    /**
     * Gets ship name.
     *
     * @return the ship name
     */
    public String getShipName() {
        return shipName;
    }

    /**
     * Sets ship name.
     *
     * @param shpipName the shpip name
     */
    public void setShipName(String shpipName) {
        this.shipName = shpipName;
    }


    /**
     * Gets ship type.
     *
     * @return the ship type
     */
    public String getShipType() {
        return shipType;
    }

    /**
     * Sets ship type.
     *
     * @param shipType the ship type
     */
    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    /**
     * Gets ship length.
     *
     * @return the ship length
     */
    public short getShipLength() {
        return shipLength;
    }

    /**
     * Sets ship length.
     *
     * @param shipLength the ship length
     */
    public void setShipLength(short shipLength) {
        this.shipLength = shipLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipsEntity that = (ShipsEntity) o;
        return shipLength == that.shipLength && Objects.equals(callSign, that.callSign) && Objects.equals(shipName, that.shipName) && Objects.equals(shipType, that.shipType) && Objects.equals(shipOwnersEntity, that.shipOwnersEntity) && Objects.equals(visitsEntities, that.visitsEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(callSign, shipName, shipType, shipLength, shipOwnersEntity, visitsEntities);
    }

    /**
     * Gets ship owners entity.
     *
     * @return the ship owners entity
     */
    public ShipOwnersEntity getShipOwnersEntity() {
        return shipOwnersEntity;
    }

    /**
     * Sets ship owners entity.
     *
     * @param shipOwnersEntity the ship owners entity
     */
    public void setShipOwnersEntity(ShipOwnersEntity shipOwnersEntity) {
        this.shipOwnersEntity = shipOwnersEntity;
    }
}
