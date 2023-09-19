package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userId", "superAdmin"})
public class AdminController {

}
