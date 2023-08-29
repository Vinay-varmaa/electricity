package bill.management.electricity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="reading_id")
    private int readingid;

    @Column(name="total_amount")
    private int amount;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name="reading_id",updatable = false,insertable = false)
    private MeterReading meterReading;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReadingid() {
        return readingid;
    }

    public void setReadingid(int readingid) {
        this.readingid = readingid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public MeterReading getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(MeterReading meterReading) {
        this.meterReading = meterReading;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", readingid=" + readingid +
                ", amount=" + amount +
                ", meterReading=" + meterReading +
                '}';
    }
}
