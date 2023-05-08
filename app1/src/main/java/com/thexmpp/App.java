package com.thexmpp;

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
        System.out.println("Hello World!");
        String server = "127.0.0.1";
        int port = 5222;
        String username = "hai";
        String password = "696685";
        
        // Create a connection configuration
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
            .setHost(server)
            .setPort(port)
            .setXmppDomain("test.com")
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
        ChatManager chatManager = ChatManager.getInstanceFor(conn);
        
        // Create a Chat object with the recipient's JID
        EntityBareJid jid = JidCreate.entityBareFrom("hai@test.com");
        Chat chat = chatManager.chatWith(jid);
        
        // Send a message to the recipient
        String messageText = "Hello, how are you?";
        Message message = new Message();
        message.setBody(messageText);
        chat.send(message);
          
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
}
