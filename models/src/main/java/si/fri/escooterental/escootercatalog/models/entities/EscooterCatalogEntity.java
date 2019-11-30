package si.fri.escooterental.escootercatalog.models.entities;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Table(name ="escooter")
@NamedQueries(value =
        {
                @NamedQuery(name = "EscooterCatalogEntity.getAll", query = "SELECT c FROM EscooterCatalogEntity c")
        })


public class EscooterCatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nam")
    private String nam;

    @Column(name = "status")
    private String status;

    @Column(name = "price")
    private Float price;

    @Column(name="description")
    private String description;

    @Column(name = "pickuplocation")
    private String pickuplocation;

    @Column(name = "leavelocation")
    private String leavelocation;

    @Column(name = "created")
    private Instant created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return nam;
    }

    public void setName(String name) {
        this.nam = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPickuplocation() {
        return pickuplocation;
    }

    public void setPickuplocation(String pickuplocation) {
        this.pickuplocation = pickuplocation;
    }

    public String getLeavelocation() {
        return leavelocation;
    }

    public void setLeavelocation(String leavelocation) {
        this.leavelocation = leavelocation;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }
}

