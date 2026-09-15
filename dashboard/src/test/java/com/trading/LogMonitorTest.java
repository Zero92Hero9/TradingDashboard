package com.trading;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trading.model.LogPacket;
import com.trading.processor.StreamEngine;

public class LogMonitorTest {
    private LogMonitor monitor;
    private StreamEngine streamEngine;

    @BeforeEach
    public void setUp() {
        monitor = new LogMonitor();
        streamEngine = new StreamEngine();
    }

    @Test
    public void testHappyPath() {
         String data = 
            "14 200\n" +
            "15 50\n" +
            "17 300\n";

        
        List<LogPacket> packets = streamEngine.processLogs(new StringReader(data));
        assertTrue(packets.size() == 3);
        assertNotNull(packets.get(0).getId(), "UUID is automatically generated");
        assertEquals(packets.get(1).getTimeInSeconds(), 15);
    }
    
}
