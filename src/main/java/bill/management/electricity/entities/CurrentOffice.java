package bill.management.electricity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="currentoffice")
public class CurrentOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="address")
    private String address;

    @Column(name="location_id")
    private int location_id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "location_id",updatable = false,insertable = false)
    private Location location;

    @OneToMany(mappedBy = "currentOfficeid")
    @JsonIgnore
    private List<MeterReading> meterReading;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<MeterReading> getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(List<MeterReading> meterReading) {
        this.meterReading = meterReading;
    }

    @Override
    public String toString() {
        return "CurrentOffice{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", location_id=" + location_id +
                ", location=" + location +
                ", meterReading=" + meterReading +
                '}';
    }
}
