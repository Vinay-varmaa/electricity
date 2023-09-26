package bill.management.electricity.contollers;

import bill.management.electricity.dto.AuthRequest;
import bill.management.electricity.entities.MeterType;
import bill.management.electricity.entities.UserInfo;
import bill.management.electricity.exception.MeterTypeidNotFound;
import bill.management.electricity.rest.MeterTypeRepo;
import bill.management.electricity.service.JWTService;
import bill.management.electricity.service.MeterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MeterTypeController {
    @Autowired
    private MeterTypeRepo meterTypeRepo;

    @Autowired
    private MeterTypeService meterTypeService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addMeterTypes")
    public MeterType addMeterType(@RequestBody MeterType meterType) {
        meterTypeRepo.save(meterType);
        return meterType;
    }

    @PutMapping("/updateTypes")
    public MeterType updateMeterType(@RequestParam("id") int id, @RequestParam("rate") int rate) throws MeterTypeidNotFound, MeterTypeidNotFound {
        var meterid = meterTypeRepo.findById(id);
        if (meterid.isPresent()) {
            var type = meterid.get();
            type.setRate(rate);
            meterTypeRepo.save(type);
            return type;
        } else
            throw new MeterTypeidNotFound();
    }

    @DeleteMapping("/deleteMeterType")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public String deleteMeterType(@RequestParam() int id) throws MeterTypeidNotFound, MeterTypeidNotFound {
        Optional<MeterType> meterType = meterTypeRepo.findById(id);
        if (meterType != null) {
            return meterTypeService.deleteMeterType(id);
        } else {
            throw new MeterTypeidNotFound();
        }
    }




    @GetMapping("/findMeters")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<MeterType> findAllMeters(){
        return meterTypeService.getAllMeters();
    }

    //pagination
  @RequestMapping(value = "/pageable/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public Page<MeterType>MeterTypePage(@PathVariable Integer pageNo,@PathVariable Integer pageSize){
        return meterTypeService.getMeterTypePagination(pageNo,pageSize);
  }
  @PostMapping("/authenticate")
  public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){

     Authentication authentication =   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(),authRequest.getPassword()));
     if(authentication.isAuthenticated()) {
         return jwtService.generateToken(authRequest.getName());
     } else {
         throw  new UsernameNotFoundException("Invalid user request");
     }
  }
}
