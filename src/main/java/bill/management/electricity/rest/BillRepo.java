package bill.management.electricity.rest;

import bill.management.electricity.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepo extends JpaRepository<Bill,Integer> {

    @Query(value = "select total_amount from bill where reading_id =:reading_id",nativeQuery = true)
    int getBills(@Param("reading_id") int reading_id);

}
