package com.thexmpp;

import java.util.Scanner;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.chat2.*;
import org.jivesoftware.smack.tcp.*;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
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
        //System.out.println("Hello World!");
        Scanner sc = new Scanner(System.in);
        String server = "127.0.0.1";
        int port = 5222;
        System.out.print("Enter your Username: ");
        String username = sc.nextLine(); 
        System.out.print("Password: ");       
        String password = sc.nextLine();
        System.out.println("Connectting to server...");
        // String username = "hoang";        
        // String password = "hoang";
        
        
        // Create a connection configuration
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
            .setHost(server)
            .setPort(port)
            .setXmppDomain("hoangbuii.com")
            .setUsernameAndPassword(username, password)
            .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
            .build();
        
        // Create a connection
        AbstractXMPPConnection conn = new XMPPTCPConnection(config);
        
        try {
            // Connect to the server
            conn.connect();
            
            // Log in to the server
            conn.login();
            // get the chatManager 
            System.out.println("Connect succserfully!");
            ChatManager chatManager = ChatManager.getInstanceFor(conn);
            
            System.out.println("Enter user to start a chat: ");
            String useString = sc.nextLine();
            // Create a Chat object with the recipient's JID
            EntityBareJid jid = JidCreate.entityBareFrom(useString + "@hoangbuii.com");
            Chat chat = chatManager.chatWith(jid);
            
            //Send a message to the recipient
            //String messageText = "Hello, how are you?";
            //chat.send(messageText);

            chatManager.addIncomingListener(new IncomingChatMessageListener() {
              @Override
              public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
                  System.out.println(from + ": " + message.getBody());
              }
            });

            while (true) {
              String msg = sc.nextLine();
              chat.send(msg);
              // if ("bye".equalsIgnoreCase(msg)) {
              //   break;
              // }
            }
          
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
}
