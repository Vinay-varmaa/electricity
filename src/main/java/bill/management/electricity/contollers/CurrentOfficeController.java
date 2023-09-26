package bill.management.electricity.contollers;

import bill.management.electricity.entities.CurrentOffice;
import bill.management.electricity.exception.OfficeNotFound;
import bill.management.electricity.rest.CurrentOfficeRepo;
import bill.management.electricity.service.CurrentOfficeService;
import com.fasterxml.jackson.core.PrettyPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CurrentOfficeController {
    @Autowired
    private CurrentOfficeRepo currentOfficeRepo;

    @Autowired
    private CurrentOfficeService currentOfficeService;

    @GetMapping("/getOffice")
    public List<CurrentOffice> getOffice() {
        return currentOfficeRepo.findAll();
    }

    @PostMapping("/addOffice")
    public CurrentOffice addOffice(@RequestBody CurrentOffice currentOffice){
        currentOfficeRepo.save(currentOffice);
        return currentOffice;
    }

    @DeleteMapping("/deleteOffice")
    public String deleteOffice(@RequestParam("id") int id) throws OfficeNotFound {
        return currentOfficeService.deleteOffice(id);
    }

    @PutMapping("/updateOffice")
    public CurrentOffice updateOffice(@RequestParam("id") int id,@RequestParam("address") String address) throws OfficeNotFound{
        return currentOfficeService.updateOffice(id,address);
    }


}
