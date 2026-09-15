package com.trading.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.trading.model.LogPacket;

@Service
public class StreamEngine implements LogEngine {

    @Override
    public List<LogPacket> processLogs(Reader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("reader cannot be null");
        }

        List<LogPacket> packets = new ArrayList<>();
        try (BufferedReader bReader = new BufferedReader(reader)) {
            String line;
            while ((line = bReader.readLine()) != null) {               
                String[] words = line.split("\\s+");                
                if (words.length < 2) {
                    continue;
                }

                LogPacket packet = new LogPacket();
                packet.setId(UUID.randomUUID().toString());
                packet.setTimeInSeconds(Integer.valueOf(words[0]));
                packet.setAmount(Double.valueOf(words[1]));

                packets.add(packet);
            }   
        } catch (IOException io) {
            throw new RuntimeException("Unable to read");
        }

        return packets;
    }
}
