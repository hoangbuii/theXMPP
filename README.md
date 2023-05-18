# theXMPP
>Implement XMPP for sensor network using Openfire server and Smack library
## Overview
The implementation of XMPP (Extensible Messaging and Presence Protocol) for sensor networks has become increasingly 
popular in recent years. XMPP is a widely-used communication protocol that allows for the exchange of data between 
devices, making it an ideal choice for use in sensor networks. This report provides an overview of the implementation of
XMPP for sensor networks using Openfire server and Smack library.

The report is divided into four main parts: overview, network topology, packet format, implementation. The 
network topology section provides an overview of the physical and logical layout of the sensor network, including the 
types of sensors and devices used, as well as the network architecture. The packet format section details the structure 
of the data packets used for communication between the devices, including information about the payload, headers, and 
other relevant data.

The implementation section provides a detailed guide for setting up and configuring the Openfire server and Smack 
library for use in a sensor network. This section includes information about configuring the server, installing and 
configuring the Smack library, and setting up communication between devices.

Overall, this report provides a comprehensive overview of the implementation of XMPP for sensor networks using Openfire 
server and Smack library. It is intended to be a useful resource for those interested in developing and implementing 
XMPP-based solutions for sensor networks, as well as for those who want to better understand the benefits and challenges
associated with this approach.

## Network topology
## Packet format
| Source | Destination | Time | Type | Temperature | Humidity | Atmospheric Pressure | Control Data |
| ------ | ----------- | ---- | ---- | ----------- | -------- | -------------------- | ------------ |
|The sender of the packet|The receiver of the packet for processing|Time required to generate data|'0' for sensor data and '1' for control data|Equals to temperature of sensor or 0 for control data|Equals to humidity of sensor or 0 for control data|Equals to atmospheric pressure of sensor or 0 for control data|Control message or "none" for sensor data|

Example:
```java
String sensorData = "sensor|gateway|16531512052023|0|35|60|1012|none|";
String controlData = "gateway|sensor|17003812052023|1|0|0|0|off sensor|";
```
## Implementation
