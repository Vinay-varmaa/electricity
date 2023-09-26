package bill.management.electricity.service;

import bill.management.electricity.entities.Bill;
import bill.management.electricity.exception.BillNotFoundException;
import bill.management.electricity.exception.MeterTypeidNotFound;
import bill.management.electricity.rest.BillRepo;
import bill.management.electricity.rest.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepo billRepo;

    @Autowired
    private LocationRepo locationRepo;

    public double generateBill(int distance,int meterType) {
        var readingid = locationRepo.getLocation(distance, meterType);
        if (readingid.isEmpty()) {
            if (meterType == 1) {
                return distance * 15;
            } else if(meterType==2){
                return distance * 13;
            }else {
                throw new MeterTypeidNotFound();
            }
        }return billRepo.getBills(readingid.get(0));
    }

    public Bill updateBills(int id, int amount) throws BillNotFoundException {
        var billId=billRepo.findById(id);
        if (billId.isPresent()){
            var type=billId.get();
            type.setAmount(amount);
            billRepo.save(type);
            return type;
        }
        else {
            throw new BillNotFoundException();
        }
    }

    public String deleteBill(int id) throws BillNotFoundException{
        var billid=billRepo.findById(id);
        if (billid.isPresent()){
            billRepo.deleteById(id);
            return "deleted Successfully";
        }
        else {
            throw new BillNotFoundException();
        }
    }

}
