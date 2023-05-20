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
              <img src='./assets/logo.png' alt='no-img' className='img'/>
            </div> 
              <div className='description-container'> 
                TheXMPP project is built based on the XMPP Protocol which leverages the Openfire platform to setup and put to work. The platform uses various technology such as NodeJS, ReactJS, etc... to demonstrate how we can use sensors to communicate with the servers. <br>
                The program uses sensors to detect and calculate the temperature, humidity, and atmospheric pressure in the air and send those information to a server in the form of a packet, which then can be sent back to users on the other end. <br>
              </div>
            </div>    
          </div>
        <Footer />
    </div>
  )
}
