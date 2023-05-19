import React from 'react'
import '../inhome.css'
import './history.css'
import LineChart from '../chart/LineChart';
import { useState } from "react";

const desOpacity = {
  opacity: 0.3
};

const defOpacity = {
  opacity: 1
};



export default function History() {
  const [type, setType] = useState('temp');
  const [opacity1, setOpacity1] = useState(defOpacity);
  const [opacity2, setOpacity2] = useState(desOpacity);
  const [opacity3, setOpacity3] = useState(desOpacity);
  const chooseType = (typeDisplay) => {
    if (typeDisplay === 1) {
      setType("temp");
      setOpacity1(defOpacity);
      setOpacity2(desOpacity);
      setOpacity3(desOpacity);
    } else if (typeDisplay === 2) {
      setType("humid");
      setOpacity2(defOpacity);
      setOpacity1(desOpacity);
      setOpacity3(desOpacity);
    } else {
      setType("atm");
      setOpacity3(defOpacity);
      setOpacity1(desOpacity);
      setOpacity2(desOpacity);
    }
  }

  return (
    <div className='history-home-container'>
      <div className='title-container'>
            <div className='img-history-container'>
                <img src='../../assets/history.png' alt='no-img' className='img'/>
            </div>
            <div className='name-history'>History</div>
              <div className='history-content-container'>
                <div className='temp-history-icon' style={opacity1} id='temp-icon-opacity' onClick={() => chooseType(1)} >
                  <img src='./assets/temp.png' className='img' alt='no-img' />
                </div>
                <div className='humid-history-icon' style={opacity2} id='humid-icon-opacity' onClick={() => chooseType(2)}>
                  <img src='./assets/humid.png' className='img' alt='no-img' />
                </div>
                <div className='atm-history-icon'style={opacity3} id='atm-icon-opacity' onClick={() => chooseType(3)}>
                  <img src='./assets/atm.png' className='img' alt='no-img' />
                </div>
                
                <div className='line-chart-temp'>
                  <LineChart type={type}/>
                </div>
              </div>
        </div>


    </div>
  )
}