package com.board.FeatureHub.connectTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/test/")
@RestController
public class ConnectTestController {

    @GetMapping("/connect")
    public String helloAnswer() {
        log.info("port 3000 -> 80 connect success!");
        return "hello here is board_back project!";
    }
}
