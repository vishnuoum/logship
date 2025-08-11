@echo off
echo Starting Zookeeper...
start cmd /k "F:\devdir\kafka\bin\windows\zookeeper-server-start.bat F:\devdir\kafka\config\zookeeper.properties"
timeout /t 5 > nul

echo Starting Kafka broker...
start cmd /k "F:\devdir\kafka\bin\windows\kafka-server-start.bat F:\devdir\kafka\config\server.properties"
timeout /t 5 > nul

echo Creating topic 'shipments'...
F:\devdir\kafka\bin\windows\kafka-topics.bat --create --topic shipments --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

echo All done! Zookeeper and Kafka are running, topic created.
pause
