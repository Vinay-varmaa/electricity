package bill.management.electricity.service;

import bill.management.electricity.entities.Location;
import bill.management.electricity.exception.LocationNotFound;
import bill.management.electricity.rest.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepo locationRepo;
    public List<Integer> getLocation(@PathVariable("distance") int distance, @PathVariable("meterid") int meterid){
        List<Integer> locations= locationRepo.getLocation(distance,meterid);
        if (!locations.isEmpty()){
            return locations;
        }else {
            throw new LocationNotFound();
        }

    }

    public Location updateLocation(int id,int meterid){
        var locationid=locationRepo.findById(id);
        if (locationid.isPresent()){
            var type=locationid.get();
            type.setMeterid(meterid);
            locationRepo.save(type);
            return type;
        }
        else {
            throw new LocationNotFound();
        }
    }
    public String deleteLocation(int id){
        var location_id=locationRepo.findById(id);
        if (location_id.isPresent()){
            locationRepo.deleteById(id);
            return "deleted";
        }
        else {
            throw new LocationNotFound();
        }
    }

}
