package bill.management.electricity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zaxxer.hikari.util.ClockSource;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="meterreading")
public class MeterReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="metertype_id")
    private int metertype_id;

    @Column(name="cur_off_id")
    private int cur_off_id;

    @Column(name = "units_consumed")
    private int units;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "metertype_id",updatable = false,insertable = false)
    private MeterType meterTypeid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="id",insertable = false,updatable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="cur_off_id",insertable = false,updatable = false)
    private CurrentOffice currentOfficeid;

    @OneToOne(mappedBy = "meterReading")
    @JsonIgnore
    private Bill bill;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMetertype_id() {
        return metertype_id;
    }

    public void setMetertype_id(int metertype_id) {
        this.metertype_id = metertype_id;
    }

    public int getCur_off_id() {
        return cur_off_id;
    }

    public void setCur_off_id(int cur_off_id) {
        this.cur_off_id = cur_off_id;
    }


    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public MeterType getMeterTypeid() {
        return meterTypeid;
    }

    public void setMeterTypeid(MeterType meterTypeid) {
        this.meterTypeid = meterTypeid;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CurrentOffice getCurrentOfficeid() {
        return currentOfficeid;
    }

    public void setCurrentOfficeid(CurrentOffice currentOfficeid) {
        this.currentOfficeid = currentOfficeid;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "MeterReading{" +
                "id=" + id +
                ", metertype_id=" + metertype_id +
                ", cur_off_id=" + cur_off_id +
                ", units=" + units +
                ", meterTypeid=" + meterTypeid +
                ", location=" + location +
                ", currentOfficeid=" + currentOfficeid +
                ", bill=" + bill +
                '}';
    }
}
