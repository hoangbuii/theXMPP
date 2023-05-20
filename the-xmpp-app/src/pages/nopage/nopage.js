import React from 'react'
import Header from '../../components/header/header'
import './nopage.css'

export default function Nopage() {
  return (
    <div className='nopage-container'>
      <Header />
      <div className='n404-text'>404</div>
      <div className='logo-container'>
        <img src='./assets/logo.png' alt='no-img' className='img' />
      </div>  
      <div className='notfound-text'>Not found</div>   
    </div>
  )
}
