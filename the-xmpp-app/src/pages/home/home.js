import React from 'react'
import Header from '../../components/header/header'
import TempHome from '../../components/inhome/temp/temp'
import HumidHome from '../../components/inhome/humid/humid'
import AtmHome from '../../components/inhome/atm/atm'
import History from '../../components/inhome/history/history'
import NodeInfo from '../../components/inhome/nodeinfo/nodeinfo'
import Footer from '../../components/footer/footer'
import './home.css'
import overviewData from '../../data/overviewdata'

export default function Home() {
  return (
    <div>
      <Header/>
      <div>
        <div>
          <TempHome avg={ overviewData[0][0] } max={ overviewData[0][1] } min={ overviewData[0][2] }/>
          <HumidHome avg={ overviewData[1][0] }/>
          <AtmHome avg={ overviewData[2][0] }/>
        </div>
        <div>
          <History/>
          <NodeInfo/>
        </div>
      </div>
      <Footer/>
    </div>
  )
}
