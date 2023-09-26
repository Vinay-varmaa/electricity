package bill.management.electricity.service;

import bill.management.electricity.entities.MeterType;
import bill.management.electricity.exception.MeterTypeidNotFound;
import bill.management.electricity.rest.MeterTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class MeterTypeService {

    @Autowired
    private MeterTypeRepo meterTypeRepo;

    public List<MeterType> getAllMeters() {
        return meterTypeRepo.findAll();
    }

    public String deleteMeterType(int id) throws MeterTypeidNotFound {
        var meterid = meterTypeRepo.findById(id);
        if (meterid.isPresent()) {
            meterTypeRepo.deleteById(id);
            return "Successfully Deleted";
        } else throw new MeterTypeidNotFound();
    }

    public Page<MeterType> getMeterTypePagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return meterTypeRepo.findAll(pageable);
    }
}
