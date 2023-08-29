package bill.management.electricity.contollers;

import bill.management.electricity.rest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private BillRepo billRepo;
    @Autowired
    private CurrentOfficeRepo currentOfficeRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private MeterReadingRepo meterReadingRepo;
    @Autowired
    private MeterTypeRepo meterTypeRepo;

    @GetMapping("/generate/bill")
    public double getBill(@RequestParam int distance, @RequestParam int meterType)
    {
        var meters = locationRepo.getLocation(distance,meterType);
        if(meters.isEmpty())
        {
            if(meterType==1)
                return distance*15;
            else
                return distance*10;
        }
        return billRepo.getBills(meters.get(1));
    }


}
