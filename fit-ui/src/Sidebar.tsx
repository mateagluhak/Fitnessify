import React from 'react';

function Sidebar() {
  return (
    <div className="sidebar">
      <div className="username">John Doe</div>
      <ul className="sidebar-nav">
      <li><a href="/">Home</a></li>
        <li><a href="/profile">Profile</a></li>
        <li><a href="/workout">Workout</a></li>
        <li><a href="/calories">Calories</a></li>
        <li><a href="/goals">Goals</a></li>
      </ul>
      <div className="logout">Log out</div>
    </div>
  );
};

export default Sidebar;
