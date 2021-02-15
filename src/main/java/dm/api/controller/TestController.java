package dm.api.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @RequestMapping(method = RequestMethod.GET, value = "/api/swagger")
    public String sayHello() {
        return "Swagger Hello World";
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> test(){
        Map<String,String> map = new HashMap<>();
        map.put("message","Hello");
        return map;
    }
}
