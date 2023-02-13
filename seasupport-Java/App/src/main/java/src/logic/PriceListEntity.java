package src.logic;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


/**
 * Class that is mapping Price_list entity to the class by the ORM conception
 */
@Entity
@Table(name = "PRICE_LIST")
public class PriceListEntity {
    @Id
    @SequenceGenerator(name = "price_list_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_list_id")
    @Column(name = "LIST_ID")
    private Integer listId;
    @Column(name = "LAUNDRY")
    private Short laundry;
    @Column(name = "DRYING_ROOM")
    private Short dryingRoom;
    @Column(name = "WATER")
    private Short water;
    @Column(name = "SHOWER")
    private Short shower;
    @Column(name = "SAUNA")
    private Short sauna;
    @Column(name = "PLACE_LESS_7M")
    private Short placeLess7M;
    @Column(name = "PLACE_7_12M")
    private Short place712M;
    @Column(name = "PLACE_12_17M")
    private Short place1217M;
    @Column(name = "PLACE_17_20M")
    private Short place1720M;
    @Column(name = "PLACE_MORE_20M")
    private Short placeMore20M;
    @OneToMany(mappedBy = "priceListEntity")
    private Collection<PortsEntity> portsEntities;

    /**
     * Instantiates a new Price list entity.
     */
    public PriceListEntity()
    {}

    /**
     * Instantiates a new Price list entity.
     *
     * @param listId       the list id
     * @param laundry      the laundry
     * @param dryingRoom   the drying room
     * @param water        the water
     * @param shower       the shower
     * @param sauna        the sauna
     * @param placeLess7M  the place less 7 m
     * @param place712M    the place 712 m
     * @param place1217M   the place 1217 m
     * @param place1720M   the place 1720 m
     * @param placeMore20M the place more 20 m
     */
    public PriceListEntity(int listId, Short laundry, Short dryingRoom, Short water, Short shower, Short sauna, Short placeLess7M,
                           Short place712M, Short place1217M, Short place1720M, Short placeMore20M) {
        this.listId = listId;
        this.laundry = laundry;
        this.dryingRoom = dryingRoom;
        this.water = water;
        this.shower = shower;
        this.sauna = sauna;
        this.placeLess7M = placeLess7M;
        this.place712M = place712M;
        this.place1217M = place1217M;
        this.place1720M = place1720M;
        this.placeMore20M = placeMore20M;
        this.portsEntities = new ArrayList<PortsEntity>();
    }

    /**
     * Instantiates a new Price list entity.
     *
     * @param listId       the list id
     * @param placeLess7M  the place less 7 m
     * @param place712M    the place 712 m
     * @param place1217M   the place 1217 m
     * @param place1720M   the place 1720 m
     * @param placeMore20M the place more 20 m
     */
    public PriceListEntity(int listId, short placeLess7M, short place712M, short place1217M, short place1720M,
                           short placeMore20M) {
        this.listId = listId;
        this.placeLess7M = placeLess7M;
        this.place712M = place712M;
        this.place1217M = place1217M;
        this.place1720M = place1720M;
        this.placeMore20M = placeMore20M;
        this.portsEntities = new ArrayList<PortsEntity>();
    }

    /**
     * Add port.
     *
     * @param port the port
     */
    public void addPort(PortsEntity port)
    {
        portsEntities.add(port);
    }

    /**
     * Remove port.
     *
     * @param port the port
     */
    public void removePort(PortsEntity port)
    {
        portsEntities.remove(port);
    }

    /**
     * Gets list id.
     *
     * @return the list id
     */
    public int getListId() {
        return listId;
    }

    /**
     * Sets list id.
     *
     * @param listId the list id
     */
    public void setListId(int listId) {
        this.listId = listId;
    }

    /**
     * Gets laundry.
     *
     * @return the laundry
     */
    public Short getLaundry() {
        return laundry;
    }

    /**
     * Sets laundry.
     *
     * @param laundry the laundry
     */
    public void setLaundry(Short laundry) {
        this.laundry = laundry;
    }

    /**
     * Gets drying room.
     *
     * @return the drying room
     */
    public Short getDryingRoom() {
        return dryingRoom;
    }

    /**
     * Sets drying room.
     *
     * @param dryingRoom the drying room
     */
    public void setDryingRoom(Short dryingRoom) {
        this.dryingRoom = dryingRoom;
    }

    /**
     * Gets water.
     *
     * @return the water
     */
    public Short getWater() {
        return water;
    }

    /**
     * Sets water.
     *
     * @param water the water
     */
    public void setWater(Short water) {
        this.water = water;
    }

    /**
     * Gets shower.
     *
     * @return the shower
     */
    public Short getShower() {
        return shower;
    }

    /**
     * Sets shower.
     *
     * @param shower the shower
     */
    public void setShower(Short shower) {
        this.shower = shower;
    }

    /**
     * Gets sauna.
     *
     * @return the sauna
     */
    public Short getSauna() {
        return sauna;
    }

    /**
     * Sets sauna.
     *
     * @param sauna the sauna
     */
    public void setSauna(Short sauna) {
        this.sauna = sauna;
    }

    /**
     * Gets place less 7 m.
     *
     * @return the place less 7 m
     */
    public Short getPlaceLess7M() {
        return placeLess7M;
    }

    /**
     * Sets place less 7 m.
     *
     * @param placeLess7M the place less 7 m
     */
    public void setPlaceLess7M(Short placeLess7M) {
        this.placeLess7M = placeLess7M;
    }

    /**
     * Gets place 712 m.
     *
     * @return the place 712 m
     */
    public Short getPlace712M() {
        return place712M;
    }

    /**
     * Sets place 712 m.
     *
     * @param place712M the place 712 m
     */
    public void setPlace712M(Short place712M) {
        this.place712M = place712M;
    }

    /**
     * Gets place 1217 m.
     *
     * @return the place 1217 m
     */
    public Short getPlace1217M() {
        return place1217M;
    }

    /**
     * Sets place 1217 m.
     *
     * @param place1217M the place 1217 m
     */
    public void setPlace1217M(Short place1217M) {
        this.place1217M = place1217M;
    }

    /**
     * Gets place 1720 m.
     *
     * @return the place 1720 m
     */
    public Short getPlace1720M() {
        return place1720M;
    }

    /**
     * Sets place 1720 m.
     *
     * @param place1720M the place 1720 m
     */
    public void setPlace1720M(Short place1720M) {
        this.place1720M = place1720M;
    }

    /**
     * Gets place more 20 m.
     *
     * @return the place more 20 m
     */
    public Short getPlaceMore20M() {
        return placeMore20M;
    }

    /**
     * Sets place more 20 m.
     *
     * @param placeMore20M the place more 20 m
     */
    public void setPlaceMore20M(Short placeMore20M) {
        this.placeMore20M = placeMore20M;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceListEntity that = (PriceListEntity) o;
        return listId == that.listId && placeLess7M == that.placeLess7M && place712M == that.place712M && place1217M == that.place1217M && place1720M == that.place1720M && placeMore20M == that.placeMore20M && Objects.equals(laundry, that.laundry) && Objects.equals(dryingRoom, that.dryingRoom) && Objects.equals(water, that.water) && Objects.equals(shower, that.shower) && Objects.equals(sauna, that.sauna);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listId, laundry, dryingRoom, water, shower, sauna, placeLess7M, place712M, place1217M, place1720M, placeMore20M);
    }

}
