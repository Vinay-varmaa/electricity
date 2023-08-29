package bill.management.electricity.rest;

import bill.management.electricity.entities.MeterType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterTypeRepo extends JpaRepository<MeterType,Integer> {
}
