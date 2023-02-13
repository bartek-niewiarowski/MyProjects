package src.logic;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


/**
 * Class that is mapping Captains entity to the class by the ORM conception
 */
@Entity
@Table(name = "CAPTAINS")
public class CaptainsEntity {
    @Column(name = "FORENAME")
    private String forename;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "PESEL")
    private String pesel;
    @Id
    @SequenceGenerator(name = "captains_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "captains_id")
    @Column(name = "CAPTAIN_ID")
    private Integer captainId;
    @OneToMany(mappedBy = "captainsEntity")
    private Collection<VisitsEntity>  visitsEntity;

    /**
     * Instantiates a new Captains entity.
     */
    public CaptainsEntity()
    {}

    /**
     * Instantiates a new Captains entity.
     *
     * @param forename     the forename
     * @param surname      the surname
     * @param pesel        the pesel
     * @param visitsEntity the visits entity
     */
    public CaptainsEntity(String forename, String surname, String pesel, Collection<VisitsEntity> visitsEntity) {
        this.forename = forename;
        this.surname = surname;
        this.pesel = pesel;
        this.visitsEntity = visitsEntity;
    }

    /**
     * Instantiates a new Captains entity.
     *
     * @param forename the forename
     * @param surname  the surname
     * @param pesel    the pesel
     */
    public CaptainsEntity(String forename, String surname, String pesel) {
        this.forename = forename;
        this.surname = surname;
        this.pesel = pesel;
        this.visitsEntity = new ArrayList<VisitsEntity>();
    }

    /**
     * Instantiates a new Captains entity.
     *
     * @param forename  the forename
     * @param surname   the surname
     * @param pesel     the pesel
     * @param captainId the captain id
     */
    public CaptainsEntity(String forename, String surname, String pesel, Integer captainId) {
        this.forename = forename;
        this.surname = surname;
        this.pesel = pesel;
        this.captainId = captainId;
        this.visitsEntity = new ArrayList<VisitsEntity>();
    }

    /**
     * Gets visits entity.
     *
     * @return the visits entity
     */
    public Collection<VisitsEntity> getVisitsEntity() {
        return visitsEntity;
    }

    /**
     * Sets visits entity.
     *
     * @param visitsEntity the visits entity
     */
    public void setVisitsEntity(Collection<VisitsEntity> visitsEntity) {
        this.visitsEntity = visitsEntity;
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
     * Gets captain id.
     *
     * @return the captain id
     */
    public Integer getCaptainId() {
        return captainId;
    }

    /**
     * Sets captain id.
     *
     * @param captainId the captain id
     */
    public void setCaptainId(int captainId) {
        this.captainId = captainId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaptainsEntity that = (CaptainsEntity) o;
        return captainId == that.captainId && Objects.equals(forename, that.forename) && Objects.equals(surname, that.surname) && Objects.equals(pesel, that.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forename, surname, pesel, captainId);
    }
}
