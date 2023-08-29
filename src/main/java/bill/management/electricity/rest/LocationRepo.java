package bill.management.electricity.rest;

import bill.management.electricity.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepo extends JpaRepository<Location,Integer> {
    @Query(value = "select meter_readingid  from location where dist_from_off =:distance and meter_id =:meterid",nativeQuery = true)
    List<Integer> getLocation(@Param("distance") int distance,@Param("meterid") int meterid);
}
