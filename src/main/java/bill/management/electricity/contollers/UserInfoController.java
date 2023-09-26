package bill.management.electricity.contollers;

import bill.management.electricity.entities.UserInfo;
import bill.management.electricity.rest.UserInfoRepo;
import bill.management.electricity.service.UserInfoUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private UserInfoUserDetailService userInfoUserDetailService;

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
       return userInfoUserDetailService.addUser(userInfo);
    }
}
