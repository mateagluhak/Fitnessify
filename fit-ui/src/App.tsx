import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import Sidebar from './Sidebar';
import Home from './pages/Home';
import Workout from './pages/Workout';
import Exercise from './pages/Exercise';


function App() {
  return (
    <Router>
      <div className="App">
        <div className="sidebar-container">
          <Sidebar />
        </div>
        <div className="content-container">
          <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/workout" element={<Workout/>} />
            <Route path="/exercise" element={<Exercise/>} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
