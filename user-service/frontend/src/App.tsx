import './App.css'
import {Route, Routes} from "react-router-dom";
import Register from "./Pages/register.tsx";
import Login from "./Pages/login.tsx";

function App() {
  return (
   <Routes>
       <Route path="/" element={<Register/>} />
       <Route path="/login" element={<Login/>} />
   </Routes>
  );
}

export default App
