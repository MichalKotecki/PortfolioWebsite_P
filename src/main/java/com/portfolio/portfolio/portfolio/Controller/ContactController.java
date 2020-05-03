package com.portfolio.portfolio.portfolio.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/*
To enable conversion of SASS files to CSS, you need to run the command:

sass --watch sass/bootstrap.scss css/bootstrap.css

on folder:

D:\Projekty\PortfolioWebsite\src\main\resources\static
 */


@Controller
public class ContactController {

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}