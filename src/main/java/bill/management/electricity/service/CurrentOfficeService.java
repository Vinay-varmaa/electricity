package bill.management.electricity.service;

import bill.management.electricity.entities.CurrentOffice;
import bill.management.electricity.exception.OfficeNotFound;
import bill.management.electricity.rest.CurrentOfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentOfficeService {

    @Autowired
    private CurrentOfficeRepo currentOfficeRepo;


    public String deleteOffice(int id) throws OfficeNotFound {
        var officeid=currentOfficeRepo.findById(id);
        if (officeid.isPresent()){
            currentOfficeRepo.deleteById(id);
            return "deleted successfully";
        }
        else {
            throw new OfficeNotFound();
        }
    }

    public CurrentOffice updateOffice(int id,String address) throws OfficeNotFound {
        var officeid=currentOfficeRepo.findById(id);
        if (officeid.isPresent()){
            var type=officeid.get();
            type.setAddress(address);
            currentOfficeRepo.save(type);
            return type;
        }
        else {
            throw new OfficeNotFound();
        }
    }
}
