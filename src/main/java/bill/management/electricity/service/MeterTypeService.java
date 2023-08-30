package bill.management.electricity.service;

import bill.management.electricity.entities.MeterType;
import bill.management.electricity.rest.MeterTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterTypeService {

    @Autowired
    private MeterTypeRepo meterTypeRepo;

    public List<MeterType>getAllMeters(){
        return meterTypeRepo.findAll();
    }
}
