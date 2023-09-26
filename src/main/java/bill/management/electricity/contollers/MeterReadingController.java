package bill.management.electricity.contollers;

import bill.management.electricity.entities.MeterReading;
import bill.management.electricity.entities.MeterType;
import bill.management.electricity.exception.ReadingIdNotFoundException;
import bill.management.electricity.rest.MeterReadingRepo;
import bill.management.electricity.service.MeterReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class MeterReadingController {
    @Autowired
    private MeterReadingRepo meterReadingRepo;

    @Autowired
    private MeterReadingService meterReadingService;

    @GetMapping("/getMeterReading")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<MeterReading>getAllMeterReadings(){
        return meterReadingRepo.findAll();
    }

    @PostMapping("/addMeterReading")
    public MeterReading addNewMeterReading(@RequestBody MeterReading m){
        return meterReadingRepo.save(m);

    }

    @PutMapping("/updateReading")
    public MeterReading updateMeterReading(@RequestParam("id") int id, @RequestParam("units") int units)throws ReadingIdNotFoundException {
        return meterReadingService.getMeterReadings(id, units);
    }
    @RequestMapping(value = "/pagination/{pageno}/{pagesize}",method = RequestMethod.GET)
    public Page<MeterReading> meterReadingpage(@PathVariable Integer pageno, @PathVariable Integer pagesize){
        return meterReadingService.getMeterReadingPagination(pageno,pagesize);
    }

    @DeleteMapping("/deleteMeterReading")
    public String deleteMeterReading(@RequestParam("id") int id)throws ReadingIdNotFoundException, ReadingIdNotFoundException {
        return meterReadingService.deleteMeterReading(id);
    }
}
