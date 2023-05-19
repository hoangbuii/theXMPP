import React from 'react'
import { useState } from "react";

export default function Nodedetail(props) {
  const dark = {
    background: 'rgb(161, 198, 234)'
  }

  const light = {
    background: 'rgb(187, 209, 234)'
  }

  const [color, setColor] = useState(light);

  return (
    <div className='node-content-container' style={color} onMouseOver={() => setColor(dark)} onMouseOut={() => setColor(light)}>
        <div className='node-text'>{ props.node }</div>
        <div className='status-text'>{ props.status }</div>
        <div className='total-time-text'>{props.time}</div>
    </div>
  )
}
