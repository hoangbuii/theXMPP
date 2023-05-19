import React from 'react'
import './humid.css'
import Viewdetail from '../viewdetail'
import { Link } from 'react-router-dom'

export default function HumidHome(props) {
  return (
    <div className='humid-home-container'>
        <div className='title-container'>
            <div className='img-humid-container'>
                <img src='../../assets/humid.png' alt='no-img' className='img'/>
            </div>
            <div className='name-humid'>Humidity</div>
        </div>
        <div className='content-humid-container'>
            <div className='avg-humid'>{ props.avg } %</div>
        </div>
        <div className='viewdetail-home-data'>
            <Link to="/humiddetail">
                <Viewdetail/>
            </Link>    
        </div>
    </div>
  )
}
