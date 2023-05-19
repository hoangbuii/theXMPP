import React from 'react'
import './nodeinfo.css'
import '../inhome.css'
import { Link } from 'react-router-dom'
import nodeData from '../../../data/nodedata'

export default function NodeInfo() {
  function totalnode() {
    let countOn = 0;
    let countOff = 0;
    for (let i = 0; i < nodeData.length; i++) {
      if (nodeData[i][1] === "on") {
        countOn++;
      } else if (nodeData[i][1] === "off") {
        countOff++;
      }
    }
    return [countOn, countOff];
  }
  return (
    <div className='nodeinfo-home-container'>
      <div className='title-container'>
            <div className='img-nodeinfo-container'>
                <img src='../../assets/node2.png' alt='no-img' className='img'/>
            </div>
            <div className='name-nodeinfo'>Node Information</div>

            <div className='content-nodeinfo-container'>
              <div className='totalnode-title'>Total Node</div>
              <div className='totalnode-text'>
                {totalnode()[0] + totalnode()[1]}
              </div>
              <div className='onnode-title'>on</div>
              <div className='onnode-text'>
                {totalnode()[0]}
              </div>
              <div className='offnode-title'>off</div>
              <div className='offnode-text'>
                {totalnode()[1]}
              </div>

            </div>

            <div className='viewdetail-home-data'>
              <Link to="/nodeinfodetail">
              <div className='view-detail-container'>
                <div className='view-detail-text2'>View details</div>
                  <div className='triangle'>
                    <img className='img' src='./assets/viewdetail2.png' alt='no-img'/>
                  </div> 
                </div>
              </Link>    
            </div>
        </div>
    </div>
  )
}
