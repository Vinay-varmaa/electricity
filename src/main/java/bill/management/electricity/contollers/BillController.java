package bill.management.electricity.contollers;

import bill.management.electricity.entities.*;
import bill.management.electricity.rest.*;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {
    @Autowired
    private BillRepo billRepo;

    @Autowired
    private MeterReadingRepo meterReadingRepo;

    @Autowired
    private MeterTypeRepo meterTypeRepo;

    @Autowired
    private LocationRepo locationRepo;

    @Autowired
    private CurrentOfficeRepo currentOfficeRepo;

    @GetMapping("/getBill")
    public List<Bill> getAllBills() {
        return billRepo.findAll();
    }

    @PostMapping("add/bill")
    public Bill addBill(@RequestBody Bill bill)
    {
        billRepo.save(bill);
        return bill;
    }

}
