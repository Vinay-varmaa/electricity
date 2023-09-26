package bill.management.electricity.contollers;

import bill.management.electricity.entities.Location;
import bill.management.electricity.entities.MeterReading;
import bill.management.electricity.exception.LocationNotFound;
import bill.management.electricity.rest.LocationRepo;
import bill.management.electricity.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private LocationService locationService;

    @GetMapping("/getLocation/{distance}/{meterid}")
    public List <Integer> getLocation(@PathVariable("distance") int distance,@PathVariable("meterid") int meterid) throws  LocationNotFound{
       return locationService.getLocation(distance,meterid);
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

    @PutMapping("/updateLocation")
    public Location location(@RequestParam("id") int id,@RequestParam("meterid") int meterid) throws LocationNotFound{
        return locationService.updateLocation(id,meterid);
    }
    @DeleteMapping("/deleteLocations")
    public String deleteLocations(@RequestParam("id")int id) throws LocationNotFound{
        return locationService.deleteLocation(id);
    }
}
