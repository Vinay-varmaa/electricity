package bill.management.electricity.rest;

import bill.management.electricity.entities.CurrentOffice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentOfficeRepo extends JpaRepository<CurrentOffice,Integer> {
}
