import React from 'react'
import "./header.css"
import Logo from './logo'
import { Link } from 'react-router-dom'

export default function Header() {
  return (
    <div className="container-header">
        <Logo/>
        <Link to='/'>
          <div className='navigation-item-home'>Home</div>
        </Link>
        <Link to='/about'>
          <div className='navigation-item-about'>About</div>
        </Link>
        <div className='container-setting'>
            <img className='img' src='assets/setting.png' alt='no-img'/>
        </div>
    </div>
  )
}
