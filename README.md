# theXMPP
>Implement XMPP for sensor network using Openfire server and Smack library
## Overview
XMPP is an open communication protocol that allows real-time transmission of messages and information between devices and servers. This protocol offers features such as scalability, flexibility, and high availability, making it a good choice for sensor networks.
In this project, my group will cover the implementation of XMPP in sensor networks using Openfire server to support weather sensor software including factors such as temperature, humidity and air pressure and display presents all that information in a simple, yet beautiful, user-accessible interface.
## Network topology
![alt](https://github.com/hoangbuii/ltm/blob/main/topo.png)
* XMPP server: The hub of data, maintaining the connection between the sensor and the gateway
* Sensor: Collect data about the surrounding environment and send to XMPP server, receive control data from gateway through XMPP server
* Gateway: receive sensor data from sensor, display and save sensor data, receive sensor control commands from user and send to Sensor
## Packet format
| Source | Destination | Time | Type | Temperature | Humidity | Atmospheric Pressure | Control Data |
| ------ | ----------- | ---- | ---- | ----------- | -------- | -------------------- | ------------ |
|The sender of the packet|The receiver of the packet for processing|Time required to generate data|'0' for sensor data and '1' for control data|Equals to temperature of sensor or '0' for control data|Equals to humidity of sensor or '0' for control data|Equals to atmospheric pressure of sensor or '0' for control data|Control message or "none" for sensor data|

Example:
```java
String sensorData = "sensor|gateway|16531512052023|0|35|60|1012|none|";
String controlData = "gateway|sensor|17003812052023|1|0|0|0|off sensor|";
```
## Implementation
### 1. Set up Openfỉe(XMPP server)
* Go to https://www.igniterealtime.org/ and download the latest version of openfire server and install.
* Set up domain for XMPP server
* Create username for gateway and sensor.
* Create group chat for sensor network
### 2. Set up gateway and sensor
* Using this username, password to enter this network(group chat), follow this script:
```bash
Enter your domain: xmppserver.com
Enter your Username: username
Password: password
Connecting to server...
Connect successfully!
Enter group name: sensornetwork
Enter a nickname: gateway
```
* After connection, sersor begin send sensor data to gateway, gateway can control sensor using this command:
```bash
off sensor
on sensor
```
### 3. Set up the-xmpp-app to monitor sensor data
* Using the-xmpp-app, run it to see information about this network
```bash
npm start
```
## Conclusion
Overall, this report provides a comprehensive overview of the implementation of XMPP for sensor networks using Openfire server and Smack library. It is intended to be a useful resource for those interested in developing and implementing XMPP-based solutions for sensor networks, as well as for those who want to better understand the benefits and challenges associated with this approach.
## Contributors
|StudentID|Name|Contribute|
|---|---|---|
|20021359|Bui Huy Hoang|Server, Gateway|
|20020180|Do Huy Anh|Fontend, Report|
|20021389|Nguyen Khanh Tho Loc|Fontend, Data|
|20021342|Nguyen Ngoc Hai|Sensor, Server|
|20021280|Nguyen Thanh An|Fontend, Report|
