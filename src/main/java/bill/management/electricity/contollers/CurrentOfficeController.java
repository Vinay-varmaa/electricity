package bill.management.electricity.contollers;

import bill.management.electricity.rest.CurrentOfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentOfficeController {
    @Autowired
    private CurrentOfficeRepo CRepo;
}
