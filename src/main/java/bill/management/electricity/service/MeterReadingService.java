package bill.management.electricity.service;

import bill.management.electricity.entities.MeterReading;
import bill.management.electricity.rest.MeterReadingRepo;
import bill.management.electricity.rest.MeterTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MeterReadingService {

    @Autowired
    private MeterReadingRepo meterReadingRepo;

    public MeterReading getMeterReadings(int id,int units){
       var meterreadingid= meterReadingRepo.findById(id);
       if (meterreadingid.isPresent()){
           var type=meterreadingid.get();
           type.setUnits(units);
           meterReadingRepo.save(type);
           return type;
       }else
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MeterType not found with the given id");
    }
}
