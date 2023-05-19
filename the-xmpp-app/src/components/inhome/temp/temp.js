import React from 'react'
import './temp.css'
import Viewdetail from '../viewdetail'
import { Link } from 'react-router-dom'

export default function TempHome(props) {
  return (
    <div className='temp-home-container'>
        <div className='title-container'>
            <div className='img-temp-container'>
                <img src='../../assets/temp.png' alt='no-img' className='img'/>
            </div>
            <div className='name-temp'>Temperature</div>
        </div>
        <div className='content-temp-container'>
            <div className='avg-temp'>{ props.avg } °C</div>
            <div className='min-max-temp'>{ props.max }° / { props.min }°</div>
        </div>
        <div className='viewdetail-home-data'>
            <Link to="/tempdetail">
                <Viewdetail/>
            </Link>    
        </div>
        
    </div>
  )
}
