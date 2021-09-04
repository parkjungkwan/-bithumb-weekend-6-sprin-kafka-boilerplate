package net.zerotodev.api.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducer producer;

    @GetMapping
    public String home(){
        return "Hello Kafka";
    }

    @PostMapping
    public String sendMessage(@RequestParam("message") String message){
        System.out.println("############# sendMessage 진입 ###########");
        this.producer.sendMessage(message);
        return "Kafka Successfully";
    }
}
