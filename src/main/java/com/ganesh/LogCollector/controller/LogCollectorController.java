package com.ganesh.LogCollector.controller;

import com.ganesh.LogCollector.dto.LogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequestMapping("api/logs")
@RestController
@RequiredArgsConstructor
public class LogCollectorController {
    @Autowired
    private final KafkaTemplate<String, LogRequest> kafkaTemplate;
    private static final String TOPIC = "raw-logs";
    @PostMapping
    public ResponseEntity<Void> collectLogs(@RequestBody LogRequest logRequest){
        if(Objects.isNull(logRequest.serviceName()) || Objects.isNull(logRequest.message()))
            return ResponseEntity.badRequest().build();

        kafkaTemplate.send(TOPIC,logRequest.serviceName(),logRequest);
return ResponseEntity.accepted().build();
    }
}
