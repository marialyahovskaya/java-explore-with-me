package ru.practicum.ewm;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.stats.client.StatsClient;

@RestController
@RequestMapping(path = "")
public class TestController {
    private final StatsClient statsClient;

    public TestController() {
        statsClient = new StatsClient();
    }



    @GetMapping("/test")
    public ResponseEntity<Void> test(){
        statsClient.test();
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
