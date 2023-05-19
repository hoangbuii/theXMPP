import dataHistory from '../../../data/historydata';
import {Line} from 'react-chartjs-2';
import {
    Chart as ChartJS,
    LineElement,
    CategoryScale, //x
    LinearScale, //y
    PointElement
} from 'chart.js';
ChartJS.register(
    LineElement,
    CategoryScale, //x
    LinearScale, //y
    PointElement
);

const Times = dataHistory[0];
// const Temperatures = data[1];
// const Humidity = data[2];
// const Pressure = data[3];

const LineChart = (props) => {
  var Num;
  if (props.type === "temp") {
    Num = dataHistory[1];
  } else if (props.type === "humid") {
    Num = dataHistory[2];
  } else {
    Num = dataHistory[3];
  }
  
  const data = {
    labels: Times,
    datasets: [
      {
        label: 'Temperature',
        data: Num,
        fill: false,
        borderColor: 'rgb(65, 90, 119)',
        tension: 0.1
      }
    ]
  };

  return (
    <div>
      <Line data={data} />
    </div>
  );
};

export default LineChart;
