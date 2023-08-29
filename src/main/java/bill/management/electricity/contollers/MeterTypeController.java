package bill.management.electricity.contollers;

import bill.management.electricity.entities.MeterType;
import bill.management.electricity.rest.MeterTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MeterTypeController {
    @Autowired
    private MeterTypeRepo meterTypeRepo;

    @GetMapping("/getMeterTypes")
    public List<MeterType> getAllMeterTypes() {
        return meterTypeRepo.findAll();
    }

    @PostMapping("/addMeterTypes")
    public MeterType addMeterType(@RequestBody MeterType meterType) {
        meterTypeRepo.save(meterType);
        return meterType;
    }

    @PutMapping("/updateTypes")
    public MeterType updateMeterType(@RequestParam() int id, @RequestParam() int rate) {
        var meterid = meterTypeRepo.findById(id);
        if (meterid.isPresent()) {
            var type = meterid.get();
            type.setRate(rate);
            meterTypeRepo.save(type);
            return type;
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MeterType not found with the given id");
    }

    @DeleteMapping("/deleteMeterType")
    public String deleteMeterType(@RequestParam() int id) {
        var meterid = meterTypeRepo.findById(id);
        if (meterid.isPresent()) {
            meterTypeRepo.deleteById(id);
            return "Successfully Deleted";
        } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"this type id is not found");
    }

}
