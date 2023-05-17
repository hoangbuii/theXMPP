package com.thexmpp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    /**
     * Says hello to the world.
     * 
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

            // Get group chat manager
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
                        // Display message
                        System.out.println(message.getFrom() + ": " + message.getBody());
                        // Get data from message(packet)
                        String receiverMessage = message.getBody();
                        if (receiverMessage != null) {
                            // System.out.println("null");
                            Map<String, String> data = Packet.processPacket(receiverMessage);
                            Map<String, String> timeData = Packet.processTime(data.get("time"));
                            if (timeData == null) {
                                System.out.println("Error packet: Missing Time");
                            } else {
                                if (data.get("to").equals(NICKNAME)) {
                                    // Display data
                                    System.out.print("Data from: " + data.get("from") + " at ");
                                    System.out.printf("%s:%s:%s %s-%s-%s%n", timeData.get("hour"),
                                            timeData.get("minute"), timeData.get("second"),
                                            timeData.get("day"), timeData.get("month"),
                                            timeData.get("year"));
                                    System.out.println("Temperature: " + data.get("temp"));
                                    System.out.println("Humidity: " + data.get("humid"));
                                    System.out.println("Atmospheric pressure: " + data.get("atm"));

                                    // Save data to log
                                    String stringLog = String.format(
                                            "[%s:%s:%s %s-%s-%s] temp:%s, humid:%s, atm:%s%n",
                                            timeData.get("hour"), timeData.get("minute"),
                                            timeData.get("second"), timeData.get("day"),
                                            timeData.get("month"), timeData.get("year"),
                                            data.get("temp"), data.get("humid"), data.get("atm"));
                                    try {
                                        File file = new File("logs/" + data.get("from") + ".txt");
                                        FileWriter fr = new FileWriter(file, true);

                                        fr.write(stringLog);
                                        fr.close();
                                    } catch (IOException e) {
                                        System.out.println("Error: Can't write to log file");
                                    }

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
                    System.out.println(participant + " has change nickname to " + newNickname);
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
                if (msg.equals("bye")) {
                    break;
                }
                Scanner command = new Scanner(msg);
                String controlData = command.next();
                String destination = command.next();
                command.close();
                String controlMessage = String.format("gateway|%s|%s|1|0|0|0|%s|", destination, Packet.getTime(), controlData);
                muc.sendMessage(controlMessage);
            }
            muc.leave();
            conn.disconnect();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
