import React from 'react';
import { Pie } from 'react-chartjs-2';
import overviewData from '../../../data/overviewdata';
import nodeData from '../../../data/nodedata';
import { Chart as ChartJS,
    ArcElement
} from 'chart.js';
ChartJS.register(ArcElement);


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

function getData(indexData) {
    let allData = getColumns(indexData);
    let countGreater = 0;
    for (let i = 0; i < allData.length; i++) {
        if (allData[i] > overviewData[indexData - 3][0]) {
            countGreater++;
        }  
    }
    let less = allData.length - countGreater;
    return [countGreater, less];
}

const PieChart = (props) => {
    var Num;
    if (props.type === "temp") {
      Num = getData(3);
    } else if (props.type === "humid") {
      Num = getData(4);
    } else {
      Num = getData(5);
    }
    console.log(Num);
    
    const data = {
        labels: ["less", "more"],
        datasets: [
          {
            label: 'Temperature',
            data: Num,
            backgroundColor:[
                'rgba(224, 225, 221, 1)',
                'rgba(119, 141, 169, 1)'
            ]
            
          }
        ]
      };

      return (
        <div>
          <Pie data={data} />
        </div>
      );
  };
  
  export default PieChart;