import React from 'react'
import nodeData from '../../../data/nodedata'
import {Bar} from 'react-chartjs-2';
import {
    Chart as ChartJS,
    LineElement,
    CategoryScale, //x
    LinearScale, //y
    BarElement
} from 'chart.js';
ChartJS.register(
    LineElement,
    CategoryScale, //x
    LinearScale, //y
    BarElement
);

function getColumns(cols) {
    let column = [];
    let j = 0;
    for (let i = 0; i < nodeData.length; i++) {
        if ((nodeData[i][1] === "on") || (nodeData[i][1] === "off")) {
            column[j] = nodeData[i][cols];
            j++
        }
    }
    return column;
}

const Nodes = getColumns(0);

const BarChart = (props) => {
    var Num;
    if (props.type === "temp") {
      Num = getColumns(3);
    } else if (props.type === "humid") {
      Num = getColumns(4);
    } else {
      Num = getColumns(5);
    }
    
    const data = {
        labels: Nodes,
        datasets: [
          {
            label: 'Temperature',
            data: Num,
            fill: false,
            backgroundColor: 'rgba(80, 125, 188, 0.5)',
            tension: 0.1
          }
        ]
      };

      return (
        <div>
          <Bar data={data} />
        </div>
      );
  };
  
  export default BarChart;