package src.logic;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

/**
 * Class that is mapping Visits entity to the class by the ORM conception
 */
@Entity
@Table(name = "VISITS")
public class VisitsEntity {

    @Column(name = "DATE_BEGIN")
    private Date dateBegin;
    @Column(name = "DATE_END")
    private Date dateEnd;
    @Id
    @SequenceGenerator(name = "visit_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_id")
    @Column(name = "VISIT_ID")
    private Integer visitId;
    @ManyToOne
    @JoinColumn(name = "PORT_ID", referencedColumnName = "PORT_ID")
    private PortsEntity portsEntity;
    @ManyToOne
    @JoinColumn(name = "LOGIN", referencedColumnName = "LOGIN")
    private AllUsersEntity allUsersEntity;
    @ManyToOne
    @JoinColumn(name = "CALL_SIGN", referencedColumnName = "CALL_SIGN")
    private ShipsEntity shipsEntity;
    @ManyToOne
    @JoinColumn(name = "CAPTAIN_ID", referencedColumnName = "CAPTAIN_ID")
    private CaptainsEntity captainsEntity;

    /**
     * Instantiates a new Visits entity.
     *
     * @param dateBegin      the date begin
     * @param dateEnd        the date end
     * @param portsEntity    the ports entity
     * @param allUsersEntity the all users entity
     * @param shipsEntity    the ships entity
     * @param captainsEntity the captains entity
     */
    public VisitsEntity(Date dateBegin, Date dateEnd, PortsEntity portsEntity, AllUsersEntity allUsersEntity, ShipsEntity shipsEntity, CaptainsEntity captainsEntity) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.portsEntity = portsEntity;
        this.allUsersEntity = allUsersEntity;
        this.shipsEntity = shipsEntity;
        this.captainsEntity = captainsEntity;
    }

    /**
     * Instantiates a new Visits entity.
     */
    public VisitsEntity() {
    }

    /**
     * Gets ports entity.
     *
     * @return the ports entity
     */
    public PortsEntity getPortsEntity() {
        return portsEntity;
    }

    /**
     * Sets ports entity.
     *
     * @param portsEntity the ports entity
     */
    public void setPortsEntity(PortsEntity portsEntity) {
        this.portsEntity = portsEntity;
    }

    /**
     * Gets all users entity.
     *
     * @return the all users entity
     */
    public AllUsersEntity getAllUsersEntity() {
        return allUsersEntity;
    }

    /**
     * Sets all users entity.
     *
     * @param allUsersEntity the all users entity
     */
    public void setAllUsersEntity(AllUsersEntity allUsersEntity) {
        this.allUsersEntity = allUsersEntity;
    }

    /**
     * Gets ships entity.
     *
     * @return the ships entity
     */
    public ShipsEntity getShipsEntity() {
        return shipsEntity;
    }

    /**
     * Sets ships entity.
     *
     * @param shipsEntity the ships entity
     */
    public void setShipsEntity(ShipsEntity shipsEntity) {
        this.shipsEntity = shipsEntity;
    }

    /**
     * Gets date begin.
     *
     * @return the date begin
     */
    public Date getDateBegin() {
        return dateBegin;
    }

    /**
     * Sets date begin.
     *
     * @param dateBegin the date begin
     */
    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    /**
     * Gets date end.
     *
     * @return the date end
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Sets date end.
     *
     * @param dateEnd the date end
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Gets visit id.
     *
     * @return the visit id
     */
    public int getVisitId() {
        return visitId;
    }

    /**
     * Sets visit id.
     *
     * @param visitId the visit id
     */
    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitsEntity that = (VisitsEntity) o;
        return visitId == that.visitId && Objects.equals(dateBegin, that.dateBegin) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(portsEntity, that.portsEntity) && Objects.equals(allUsersEntity, that.allUsersEntity) && Objects.equals(shipsEntity, that.shipsEntity) && Objects.equals(captainsEntity, that.captainsEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateBegin, dateEnd, visitId, portsEntity, allUsersEntity, shipsEntity, captainsEntity);
    }

    /**
     * Gets captains entity.
     *
     * @return the captains entity
     */
    public CaptainsEntity getCaptainsEntity() {
        return captainsEntity;
    }

    /**
     * Sets captains entity.
     *
     * @param captainsEntity the captains entity
     */
    public void setCaptainsEntity(CaptainsEntity captainsEntity) {
        this.captainsEntity = captainsEntity;
    }



}
