import About from "./pages/about/about";
import Home from "./pages/home/home";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Nopage from "./pages/nopage/nopage";
import Layout from "./pages/layout";
import Tempdetail from "./pages/detail/tempdetail";
import Humiddetail from "./pages/detail/humiddetail";
import Atmdetail from "./pages/detail/atmdetail";
import Nodeinfodetail from "./pages/nodeinfodetail/nodeinfodetail";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="about" element={<About />} />
          <Route path="tempdetail" element={<Tempdetail /> } /> 
          <Route path="humiddetail" element={<Humiddetail /> } />
          <Route path="atmdetail" element={<Atmdetail /> } /> 
          <Route path="nodeinfodetail" element={<Nodeinfodetail /> } />
          <Route path="*" element={<Nopage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App;
