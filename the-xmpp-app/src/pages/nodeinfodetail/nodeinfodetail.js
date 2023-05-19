import React from 'react'
import './nodeinfodetail.css'
import Header from '../../components/header/header'
import Footer from '../../components/footer/footer'
import Nodedetail from '../../components/nodedetail/nodedetail'
import nodeData from '../../data/nodedata'

export default function Nodeinfodetail() {
  return (
    <div className='node-infodetail-container'>
        <Header />
        <div className='node-title-container'>
            <div className='node-title-text'>Node</div>
            <div className='status-title-text'>Status</div>
            <div className='total-time-title-text'>Total Time</div>
        </div>
        {nodeData.map((nodes) => <Nodedetail node={nodes[0]} status={nodes[1]} time={nodes[2]} />)}

        <Footer />
    </div>
  )
}
