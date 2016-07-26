package hello.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author 王成义
 * @version 7/21/16
 */
@Controller
public class IndexController {
    @RequestMapping("/ooo")
    public String index() {
        return "index";
    }
}