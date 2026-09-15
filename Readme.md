# Trading Dashboard

## Create maven project
mvn archetype:generate -DgroupId=com.trading -DartifactId=dashboard 
-DarchetypeArtifactId=maven-archetype-quickstart
-DarchetypeVersion=1.5
-DinteractiveMode=false

## Design
Classes:
    LogPacket: A simple model/data class containing timestamp in seconds and trading amount.

    StreamEngine implements LogEngine: A class that processes incoming stream of LogPackets sequentially.

    Tracker: A utility class to track the window.

Interface:
    LogEngine: This will contain a method processLogs(Reader in) that takes in either File, Stream or any other stream input. 

