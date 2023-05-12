package com.thexmpp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.tcp.*;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;
import org.jivesoftware.smackx.muc.ParticipantStatusListener;
import org.jivesoftware.smackx.muc.RoomInfo;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.EntityFullJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Resourcepart;
import org.jxmpp.stringprep.XmppStringprepException;
import org.jivesoftware.smack.packet.*;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    public static Map<String, String> processPacket(String packet) throws StringIndexOutOfBoundsException {
      try {
          String from = packet.substring(0, packet.indexOf('|'));
          String subpacket = packet.substring(packet.indexOf('|') + 1);
          String to = subpacket.substring(0, subpacket.indexOf('|'));
          subpacket = subpacket.substring(subpacket.indexOf('|') + 1);
          String time = subpacket.substring(0, subpacket.indexOf('|'));
          subpacket = subpacket.substring(subpacket.indexOf('|') + 1);
          String type = subpacket.substring(0, subpacket.indexOf('|'));
          subpacket = subpacket.substring(subpacket.indexOf('|') + 1);
          String temp = subpacket.substring(0, subpacket.indexOf('|'));
          subpacket = subpacket.substring(subpacket.indexOf('|') + 1);
          String humid = subpacket.substring(0, subpacket.indexOf('|'));
          subpacket = subpacket.substring(subpacket.indexOf('|') + 1);
          String atm = subpacket.substring(0, subpacket.indexOf('|'));
          subpacket = subpacket.substring(subpacket.indexOf('|') + 1);
          String control = subpacket.substring(0, subpacket.indexOf('|'));

          Map<String, String> sensorData = new HashMap<String, String>();

          sensorData.put("from", from);
          sensorData.put("to", to);
          sensorData.put("time", time);
          sensorData.put("type", type);
          sensorData.put("temp", temp);
          sensorData.put("humid", humid);
          sensorData.put("atm", atm);
          sensorData.put("control", control);

        return sensorData;
      } catch (NullPointerException e) {
        //e.printStackTrace();
        System.out.println("Error packet: Missing data");
      }
      return null;

  }

  public static Map<String, Integer> processTime(String timeString) {
    try {
      if (timeString.length() < 14) {
        System.out.println("Error packet: Error time format");
        return null;
      }
      Map<String, Integer> time = new HashMap<String, Integer>();
      time.put("hour", Integer.valueOf(timeString.substring(0,2)));
      time.put("minute", Integer.valueOf(timeString.substring(2,4)));
      time.put("second", Integer.valueOf(timeString.substring(4,6)));
      time.put("day", Integer.valueOf(timeString.substring(6,8)));
      time.put("month", Integer.valueOf(timeString.substring(8,10)));
      time.put("year", Integer.valueOf(timeString.substring(10)));
      return time;
    } catch (NullPointerException e) {
      System.out.println("Error packet: Error time");
    }
    return null;
  }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws XmppStringprepException
     */
    public static void main(String[] args) throws XmppStringprepException {
        Scanner sc = new Scanner(System.in);

        // Get Connection info
        System.out.print("Enter your domain: ");
        String domain = sc.nextLine();
        String server = "127.0.0.1";
        int port = 5222;
        System.out.print("Enter your Username: ");
        String username = sc.nextLine(); 
        System.out.print("Password: ");       
        String password = sc.nextLine();
        System.out.println("Connectting to server...");
        
        
        // Create a connection configuration
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
            .setHost(server)
            .setPort(port)
            .setXmppDomain(domain)
            .setUsernameAndPassword(username, password)
            .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
            .build();
        
        // Create a connection
        AbstractXMPPConnection conn = new XMPPTCPConnection(config);
        
        try {
            // Connect & login to the server
            conn.connect();
            conn.login();
            System.out.println("Connect succserfully!");

            //Get group chat manager
            MultiUserChatManager manager = MultiUserChatManager.getInstanceFor(conn);
            System.out.print("Enter group name: ");
            String groupName = sc.nextLine();
            EntityBareJid jid = JidCreate.entityBareFrom(groupName + "@conference." + domain);
            MultiUserChat muc = manager.getMultiUserChat(jid);

            String tempNickname = "";
            // Enter group
            while (true) {
                try {
                    System.out.print("Enter a nickname: ");
                    tempNickname = sc.nextLine();
                    muc.join(Resourcepart.from(tempNickname));
                    
                    break;
                } catch (XMPPErrorException e) {
                    System.out.println("This nick name alreadly exits!");
                    e.getMessage();
                } catch (NullPointerException e) {
                  System.out.println(e.getMessage());
                }
            }
            final String NICKNAME = tempNickname;
            
            
            // Add a listener to receive messages from the chat room
            muc.addMessageListener(new MessageListener() {
                @Override
                public void processMessage(Message message) {
                    try {
                        System.out.println(message.getFrom() + ": " + message.getBody());
                        String receiverMessage = message.getBody();
                        if (receiverMessage != null) {
                          //System.out.println("null");
                          Map<String, String> data = processPacket(receiverMessage);
                          Map<String, Integer> timeData = processTime(data.get("time"));
                          if (timeData == null) {
                            System.out.println("Error packet: Missing Time");
                          } else {
                            if (data.get("to").equals(NICKNAME)) {
                                System.out.print("Data from: " +  data.get("from") + " at ");
                                System.out.printf("%d:%d:%d %d-%d-%d%n", timeData.get("hour"), timeData.get("minute"), timeData.get("second"), 
                                        timeData.get("day"), timeData.get("month"), timeData.get("year"));
                                System.out.println("Temperature: " + data.get("temp"));
                                System.out.println("Humidity: " + data.get("humid"));
                                System.out.println("Atmospheric pressure: " + data.get("atm"));
                            }
                          }
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Error packet: Missing some collums");
                    }
                }
            });

            // Print the room information
            RoomInfo info = manager.getRoomInfo(jid);
            System.out.println("Room Name: " + info.getName());
            System.out.println("Number of occupants: " + info.getOccupantsCount());

    
            // Add listeners to receive notifications about changes in the room
            muc.addParticipantStatusListener(new ParticipantStatusListener() {

              @Override
              public void joined(EntityFullJid participant) {
                System.out.println(participant + " has joined the room.");
              }

              @Override
              public void left(EntityFullJid participant) {
                System.out.println(participant + " has left the room.");
              }

              @Override
              public void kicked(EntityFullJid participant, Jid actor, String reason) {
                System.out.println(participant + " has kicked by " + actor + " for " + reason);
              }

              @Override
              public void banned(EntityFullJid participant, Jid actor, String reason) {
                System.out.println(participant + " has banned by " + actor + " for " + reason);
              }

              @Override
              public void nicknameChanged(EntityFullJid participant, Resourcepart newNickname) {
                System.out.println(participant + " has change nickname.");
              }

              @Override
              public void voiceGranted(EntityFullJid participant) {
              }
              @Override
              public void voiceRevoked(EntityFullJid participant) {
              }
              @Override
              public void membershipGranted(EntityFullJid participant) {
              }
              @Override
              public void membershipRevoked(EntityFullJid participant) {
              }
              @Override
              public void moderatorGranted(EntityFullJid participant) {
              }
              @Override
              public void moderatorRevoked(EntityFullJid participant) {
              }
              @Override
              public void ownershipGranted(EntityFullJid participant) {
              }
              @Override
              public void ownershipRevoked(EntityFullJid participant) {
              }
              @Override
              public void adminGranted(EntityFullJid participant) {
              }
              @Override
              public void adminRevoked(EntityFullJid participant) {
              }
              
            });

            // send message to group
            while (true) {
                String msg = sc.nextLine();
                if (msg.equals("bye!")) {
                  break;
                }
                muc.sendMessage(msg);
            }
            muc.leave();
            conn.disconnect();
            sc.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
}
