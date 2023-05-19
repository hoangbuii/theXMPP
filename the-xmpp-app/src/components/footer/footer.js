import React from 'react'
import Logo2 from './logo2'
import Copyright from './copyright'
import './footer.css'

export default function Footer() {
  return (
    <div className='container-footer-copyright'>
        <div className='container-footer'>
            <Logo2/>
            <div className='info-project'>The project is powered by <a href='https://www.igniterealtime.org/'><u>ignite realtime</u></a>. Latest release: May 19, 2023</div>
            <div className='openfire-logo-container'>
                <img src='./assets/openfire.png' alt='no-img' className='img' />
            </div>
            <div className='smack-logo-container'>
                <img src='./assets/smack.png' alt='no-img' className='img' />
            </div>
            <div className='to-admin'><a href='http://localhost:9090/'>Admin page</a></div>
            <div className='to-github'><a href='https://github.com/hoangbuii/theXMPP'>Github resource</a></div>
        </div>
        <Copyright/>
    </div>
  )    
}
