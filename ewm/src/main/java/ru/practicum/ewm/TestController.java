package ru.practicum.ewm;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.stats.client.StatsClient;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "")
public class TestController {
    private final StatsClient statsClient;


    @GetMapping("/test")
    public ResponseEntity<Void> test(HttpServletRequest request) {
        statsClient.hit(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
