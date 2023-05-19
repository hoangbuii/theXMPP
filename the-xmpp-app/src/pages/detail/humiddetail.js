import React from 'react'
import './detail.css'
import Header from '../../components/header/header'
import Footer from '../../components/footer/footer'
import overviewData from '../../data/overviewdata'
import BarChart from '../../components/inhome/chart/BarChart'
import PieChart from '../../components/inhome/chart/PieChart'

export default function Humiddetail() {
    return (
        <div className='detail-container'>
          <Header />
          <div className='content-container'>
            <div className='overview-container'>
              <div className='title-container'>
                <div className='icon-container'>
                  <img src='./assets/humid.png' alt='no-img' className='img'/>
                </div>
                <div className='name'>
                  Humidity
                </div>
              </div>
              
              <div className='avg-container'>
                <div className='title-avg'>
                  Average Humidity
                </div>
                <div className='num-avg'>
                { overviewData[1][0] } %
                </div>
              </div>
              <div className='max-container'>
                <div className='title-max'>
                  Max Humidity
                </div>
                <div className='num-max'>
                { overviewData[1][1] }%
                </div>
              </div>
              <div className='min-container'>
                <div className='title-min'>
                  Min Humidity
                </div>
                <div className='num-min'>
                { overviewData[1][2] }%
                </div>
              </div>
            </div>
            <div className='distribution-container'>
            <div className='title-container'>
              <div className='icon-container'>
                <img src='./assets/node.png' alt='no-img' className='img'/>
              </div>
              <div className='name'>
                Humidity Distribution
              </div>
            </div>
            <div className='bar-chart'>
              <BarChart type="humid" />
          </div>
          </div>
          <div className='ratio-container'>
            <div className='title-container'>
              <div className='icon-container'>
                <img src='./assets/ratio.png' alt='no-img' className='img'/>
              </div>
              <div className='name-white'>
                Humidity Ratio
              </div>
            </div>
            <div className='pie-chart'>
              <PieChart type="humid" />
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
