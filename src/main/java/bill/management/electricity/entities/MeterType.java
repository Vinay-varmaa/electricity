package bill.management.electricity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="metertype")
public class MeterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="rate_per_unit")
    private int rate;

    @OneToMany(mappedBy = "meterTypeid")
    @JsonIgnore
    private List<MeterReading> meterReadings;

   @OneToMany(mappedBy = "meterType")
   @JsonIgnore
   private List<Location>location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public List<MeterReading> getMeterReadings() {
        return meterReadings;
    }

    public void setMeterReadings(List<MeterReading> meterReadings) {
        this.meterReadings = meterReadings;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "MeterType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", meterReadings=" + meterReadings +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeterType meterType = (MeterType) o;
        return id == meterType.id && rate == meterType.rate && Objects.equals(name, meterType.name) && Objects.equals(meterReadings, meterType.meterReadings) && Objects.equals(location, meterType.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rate, meterReadings, location);
    }
}
