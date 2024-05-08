package edu.wgu.d387_sample_code.controller;

import edu.wgu.d387_sample_code.service.TimeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class TimeZoneController {

    @Autowired
    private TimeZoneService timeZoneService;

    @GetMapping("/timezone")
    public List<String> displayTimeZones() {
        return timeZoneService.getConvertedTimes();
    }
}