package org.csu.mypetstore.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpController {
    @GetMapping("/help")
    public String viewHelp(){
        return "help";
    }
}
