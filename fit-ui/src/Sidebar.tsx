import React, { useState } from 'react';
import { Link } from 'react-router-dom';

function Sidebar() {
  const [isExpanded, setIsExpanded] = useState(false);

  const handleToggleExpand = () => {
    setIsExpanded(!isExpanded);
  };

  return (
    <div className="sidebar">
      <div className="username">John Doe</div>
      <ul className="sidebar-nav">
        <li><Link to="/">Home</Link></li>
        <li><Link to="/profile">Profile</Link></li>
        <li>
          <span>Workouts</span>
          <button className="expand-button" onClick={handleToggleExpand}>
            {isExpanded ? '-' : '+'}
          </button>
          
          {isExpanded && (
            <ul className="sub-nav">
              <li><Link to="/workout">Workout</Link></li>
              <li><Link to="/exercise">Exercise</Link></li>
            </ul>
          )}
        </li>
        <li><Link to="/calories">Calories</Link></li>
        <li><Link to="/goals">Goals</Link></li>
      </ul>
      <div className="logout">Log out</div>
    </div>
  );
}

export default Sidebar;
