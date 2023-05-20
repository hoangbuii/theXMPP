import React from 'react'
import './detail.css'
import Header from '../../components/header/header'
import Footer from '../../components/footer/footer'
import overviewData from '../../data/overviewdata'
import BarChart from '../../components/inhome/chart/BarChart'
import PieChart from '../../components/inhome/chart/PieChart'

export default function Tempdetail() {
  return (
    <div className='detail-container'>
      <Header />
      <div className='content-container'>
        <div className='overview-container'>
          <div className='title-container'>
            <div className='icon-container'>
              <img src='./assets/temp.png' alt='no-img' className='img'/>
            </div>
            <div className='name'>
              Temperature
            </div>
          </div>
          
          <div className='avg-container'>
            <div className='title-avg'>
              Average Temperature
            </div>
            <div className='num-avg'>
            { overviewData[0][0] } °C 
            </div>
          </div>
          <div className='max-container'>
            <div className='title-max'>
              Max Temperature
            </div>
            <div className='num-max'>
            { overviewData[0][1] }° 
            </div>
          </div>
          <div className='min-container'>
            <div className='title-min'>
              Min Temperature
            </div>
            <div className='num-min'>
            { overviewData[0][2] }° 
            </div>
          </div>
        </div>
        <div className='distribution-container'>
          <div className='title-container'>
            <div className='icon-container'>
              <img src='./assets/node.png' alt='no-img' className='img'/>
            </div>
            <div className='name'>
              Temperature Distribution
            </div>
          </div>
          <div className='bar-chart'>
              <BarChart type="temp" />
          </div>
        </div>
        <div className='ratio-container'>
          <div className='title-container'>
            <div className='icon-container'>
              <img src='./assets/ratio.png' alt='no-img' className='img'/>
            </div>
            <div className='name-white'>
              Temperature Ratio
            </div>
          </div>
          <div className='pie-chart'>
              <PieChart type="temp" />
          </div>
          <div className='note1-color'></div>
          <div className='note1-text'>Less than Average</div>
          <div className='note2-color'></div>
          <div className='note2-text'>Greater than Average</div>
        </div>
      </div>
      <Footer />
    </div>
  )
}
