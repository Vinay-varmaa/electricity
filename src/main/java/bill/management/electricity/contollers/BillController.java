package bill.management.electricity.contollers;

import bill.management.electricity.entities.*;
import bill.management.electricity.exception.BillNotFoundException;
import bill.management.electricity.rest.*;
import bill.management.electricity.service.BillService;
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
    @Autowired
    private BillService billService;

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

    @PutMapping("/updateBill")
    public Bill updateBill(@RequestParam("id") int id,@RequestParam("amount") int amount) throws BillNotFoundException {

        return billService.updateBills(id,amount);
    }

    @DeleteMapping("/deleteBill")
    public String deleteBills(@RequestParam("id") int id) throws BillNotFoundException{
        return billService.deleteBill(id);
    }

}
