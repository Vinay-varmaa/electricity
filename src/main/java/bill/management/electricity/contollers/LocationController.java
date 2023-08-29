package bill.management.electricity.contollers;

import bill.management.electricity.entities.Location;
import bill.management.electricity.entities.MeterReading;
import bill.management.electricity.rest.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {
    @Autowired
    private LocationRepo locationRepo;

    @GetMapping("/getLocation/{distance}/{meterid}")
    public List <Integer> getLocation(@PathVariable("distance") int distance,@PathVariable("meterid") int meterid){
        return locationRepo.getLocation(distance,meterid);

    }

    @GetMapping("/getAllLocations")
    public List<Location>getAllLocation(){
        return locationRepo.findAll();
    }
    @PostMapping("/addLocation")
    public Location addNewLocation(@RequestBody Location location){
        locationRepo.save(location);
        return location;
    }

}
