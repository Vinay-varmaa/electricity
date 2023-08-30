package bill.management.electricity.contollers;

import bill.management.electricity.entities.MeterReading;
import bill.management.electricity.entities.MeterType;
import bill.management.electricity.rest.MeterReadingRepo;
import bill.management.electricity.service.MeterReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MeterReadingController {
    @Autowired
    private MeterReadingRepo meterReadingRepo;

    @Autowired
    private MeterReadingService meterReadingService;

    @GetMapping("/getMeterReading")
    public List<MeterReading>getAllMeterReadings(){
        return meterReadingRepo.findAll();
    }

    @PostMapping("/addMeterReading")
    public MeterReading addNewMeterReading(@RequestBody MeterReading m){
        meterReadingRepo.save(m);
        return m;
    }

    @PutMapping("/updateReading")
    public MeterReading updateMeterReading(@RequestParam("id") int id, @RequestParam("units") int units) {
        return meterReadingService.getMeterReadings(id, units);
    }

}
