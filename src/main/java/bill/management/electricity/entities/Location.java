package bill.management.electricity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "meter_id")
    private int meterid;

    @Column(name = "address")
    private String address;

    @Column(name = "dist_from_off")
    private int distance;

    @Column(name="meter_readingid")
    private int meter_reading_id;

    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private CurrentOffice currentOffice;

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<MeterReading>meterReadings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "meter_id",updatable = false,insertable = false)
    private MeterType meterType;

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

    public int getMeterid() {
        return meterid;
    }

    public void setMeterid(int meterid) {
        this.meterid = meterid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getMeter_reading_id() {
        return meter_reading_id;
    }

    public void setMeter_reading_id(int meter_reading_id) {
        this.meter_reading_id = meter_reading_id;
    }

    public CurrentOffice getCurrentOffice() {
        return currentOffice;
    }

    public void setCurrentOffice(CurrentOffice currentOffice) {
        this.currentOffice = currentOffice;
    }

    public List<MeterReading> getMeterReadings() {
        return meterReadings;
    }

    public void setMeterReadings(List<MeterReading> meterReadings) {
        this.meterReadings = meterReadings;
    }

    public MeterType getMeterType() {
        return meterType;
    }

    public void setMeterType(MeterType meterType) {
        this.meterType = meterType;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", meterid=" + meterid +
                ", address='" + address + '\'' +
                ", distance=" + distance +
                ", meter_reading_id=" + meter_reading_id +
                ", currentOffice=" + currentOffice +
                ", meterReadings=" + meterReadings +
                ", meterType=" + meterType +
                '}';
    }
}
