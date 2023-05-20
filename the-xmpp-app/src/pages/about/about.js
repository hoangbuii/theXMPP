import React from 'react'
import Footer from '../../components/footer/footer'
import Header from '../../components/header/header'
import './about.css'

export default function About() {
  return (
    <div>
        <Header />
          <div className='about-container'>
          <div className='logo-container-large'>
            <img src='./assets/logo.png' alt='no-img' className='img' />
          </div> 
          <div className='description-container'> 
            The implementation of XMPP (Extensible Messaging and Presence Protocol) for sensor networks has become increasingly popular in recent years. XMPP is a widely-used communication protocol that allows for the exchange of data between devices, making it an ideal choice for use in sensor networks. This report provides an overview of the implementation of XMPP for sensor networks using Openfire server and Smack library.<br/>
            The implementation section provides a detailed guide for setting up and configuring the Openfire server and Smack library for use in a sensor network. This section includes information about configuring the server, installing and configuring the Smack library, and setting up communication between devices.<br/>
            Overall, this report provides a comprehensive overview of the implementation of XMPP for sensor networks using Openfire server and Smack library. It is intended to be a useful resource for those interested in developing and implementing XMPP-based solutions for sensor networks, as well as for those who want to better understand the benefits and challenges associated with this approach.<br/>
          </div>    
          </div>
        <Footer />
    </div>
  )
}
