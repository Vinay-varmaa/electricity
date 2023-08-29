package bill.management.electricity.rest;

import bill.management.electricity.entities.MeterReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterReadingRepo extends JpaRepository<MeterReading,Integer> {
}
