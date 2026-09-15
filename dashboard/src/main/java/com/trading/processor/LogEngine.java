package com.trading.processor;

import java.io.Reader;
import java.util.List;

import com.trading.model.LogPacket;

public interface LogEngine {
    public List<LogPacket> processLogs(Reader in);
}
