package bill.management.electricity.contollers;

import bill.management.electricity.exception.MeterTypeidNotFound;
import bill.management.electricity.rest.*;
import bill.management.electricity.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private BillService billService;

    @GetMapping("/generate/bill")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public double getBill(@RequestParam int distance, @RequestParam int meterType) throws MeterTypeidNotFound {
        return billService.generateBill(distance, meterType);
    }
}
