package bill.management.electricity.service;

import bill.management.electricity.rest.BillRepo;
import bill.management.electricity.rest.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepo billRepo;

    @Autowired
    private LocationRepo locationRepo;

    public double generateBill(int distance,int meterType) {
        var readingid = locationRepo.getLocation(distance, meterType);
        if (readingid.isEmpty()) {
            if (meterType == 1) {
                return distance * 15;
            } else {
                return distance * 13;
            }
        }return billRepo.getBills(readingid.get(0));
    }
}
