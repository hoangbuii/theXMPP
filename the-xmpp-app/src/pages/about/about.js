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
              
            </div>    
          </div>
        <Footer />
    </div>
  )
}
