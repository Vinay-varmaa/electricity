package bill.management.electricity.contollers;

import bill.management.electricity.entities.MeterReading;
import bill.management.electricity.entities.MeterType;
import bill.management.electricity.rest.MeterReadingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MeterReadingController {
    @Autowired
    private MeterReadingRepo meterReadingRepo;

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
    public MeterReading updateMeterReading(@RequestParam() int id, @RequestParam() int units) {
        var meter_readingid = meterReadingRepo.findById(id);
        if (meter_readingid.isPresent()) {
            var type = meter_readingid.get();
            type.setUnits(units);
            meterReadingRepo.save(type);
            return type;
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MeterType not found with the given id");
    }

}
