import React from 'react'
import './atm.css'
import Viewdetail from '../viewdetail'
import { Link } from 'react-router-dom'

export default function AtmHome(props) {
  return (
    <div className='atm-home-container'>
        <div className='title-container'>
            <div className='img-atm-container'>
                <img src='../../assets/atm.png' alt='no-img' className='img'/>
            </div>
            <div className='name-atm'>Pressure</div>
        </div>
        <div className='content-atm-container'>
            <div className='avg-atm'>{ props.avg }</div>
            <div className='atm-unit'>mbar</div>
        </div>
        <div className='viewdetail-home-data'>
            <Link to="/atmdetail">
                <Viewdetail/>
            </Link>    
        </div>
    </div>
  )
}
