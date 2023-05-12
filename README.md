# theXMPP
>Implement XMPP for sensor network using Openfire server and Smack library
## Overview
## Network topology
## Packet format
| Source | Destination | Time | Type | Temperature | Humidity | Atmospheric Pressure | Control Data |
| ------ | ----------- | ---- | ---- | ----------- | -------- | -------------------- | ------------ |
|It's sender who send packet|It's receiver who receive this packet and process it|Time that sender get data|Type equals 0 if it's sensor data and equals 1 if it's control data|Equals temperature of sensor or 0 if it's control data|Equals humidity of sensor or 0 if it's control data|Equals atmospheric pressure of sensor or 0 if it's control data|Control message or "none" if it's sensor data|

Example:
```java
String sensorData = "sensor|gateway|16531512052023|0|35|60|1012|none|";
String controlData = "gateway|sensor|17003812052023|1|0|0|0|off sensor|";
```
## Implement
