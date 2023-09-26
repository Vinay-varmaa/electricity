package bill.management.electricity.service;

import bill.management.electricity.entities.MeterReading;
import bill.management.electricity.exception.ReadingIdNotFoundException;
import bill.management.electricity.rest.MeterReadingRepo;
import bill.management.electricity.rest.MeterTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MeterReadingService {

    @Autowired
    private MeterReadingRepo meterReadingRepo;

    public MeterReading getMeterReadings(int id,int units) throws ReadingIdNotFoundException {
       var meterreadingid= meterReadingRepo.findById(id);
       if (meterreadingid.isPresent()){
           var type=meterreadingid.get();
           type.setUnits(units);
           meterReadingRepo.save(type);
           return type;
       }else {
           throw new ReadingIdNotFoundException();
       }
    }
    public Page<MeterReading> getMeterReadingPagination(Integer pageno, Integer pagesize){
        Sort sort=Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageno,pagesize,sort);
        return meterReadingRepo.findAll(pageable);
    }

    public String deleteMeterReading(int id)throws ReadingIdNotFoundException{
        var meterid=meterReadingRepo.findById(id);
        if (meterid.isPresent()){
            meterReadingRepo.deleteById(id);
            return "deleted successfully";
        }
        else {
            throw new ReadingIdNotFoundException();
        }
    }
}
