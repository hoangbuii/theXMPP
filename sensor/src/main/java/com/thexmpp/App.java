package com.thexmpp;

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


            // Enter group
            String nickname;
            while (true) {
                try {
                    System.out.print("Enter a nickname: ");
                    nickname = sc.nextLine();
                    muc.join(Resourcepart.from(nickname));
                    break;
                } catch (XMPPErrorException e) {
                    System.out.println("This nick name alreadly exits!");
                    e.getMessage();
                }
            }
            
            
            // Add a listener to receive messages from the chat room
            muc.addMessageListener(new MessageListener() {
                @Override
                public void processMessage(Message message) {
                    System.out.println(message.getFrom() + "\t: " + message.getBody());
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
              String msg = nickname + "|gateway|" + SGN.getTime() + "|0|" + SGN.getTemp() + "|" +  SGN.getHumid() + "|" + SGN.getAtm() + "|none|";
              
              muc.sendMessage(msg);
              try {
                Thread.sleep(3000);
              } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (msg.equals("bye!")) {
              break;
            }
          }
            muc.leave();
            conn.disconnect();
            sc.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
}