package com.trading.web;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trading.model.LogPacket;
import com.trading.processor.StreamEngine;
import com.trading.tracker.LogTracker;

@RestController
@RequestMapping("/trading")
public class TradingController {

    private ResourceLoader resourceLoader;

    public TradingController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    @GetMapping("/ping")
    public String ping() {
        return "Ok -" + Instant.now();
    }

    @Scheduled(cron = "*/30 * * * * *")
    @GetMapping("/dashboard")
    public List<LogPacket> dashboard() {
        StreamEngine engine = new StreamEngine();
        LogTracker tracker = new LogTracker();
        List<LogPacket> packets = Collections.emptyList();
        try {
            Resource resource = resourceLoader.getResource("classpath:transactions.txt");
            StringReader reader = new StringReader(resource.getContentAsString(StandardCharsets.UTF_8));
            packets = engine.processLogs(reader);
            
            for (LogPacket packet : packets) {
                tracker.monitor(packet);
            }

        } catch(IOException e) {
            throw new RuntimeException("Input file missing");
        }

        return packets;
    }

}
