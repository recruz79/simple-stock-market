package simplestock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import simplestock.model.Trade;

@RestController
public class SimpleStockController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(path = "/trade", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String processSingleTrade(@RequestBody Trade trade) throws Exception {
        return "Result successed!";
    }

}