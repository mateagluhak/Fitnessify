import React from "react";

function Workout() {
  // Sample data for the table
  const workouts = [
    { id: 1, name: "Workout 1", exercises: "Exercise 1, Exercise 5, Exercise 6" },
    { id: 2, name: "Workout 2", exercises: "Exercise 2" },
    { id: 3, name: "Workout 3", exercises: "Exercise 3" },
  ];

  return (
    <div className="workout-container">
      <h1>Workout</h1>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Exercises</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {workouts.map((workout) => (
            <tr key={workout.id}>
              <td>{workout.name}</td>
              <td>{workout.exercises}</td>
              <td>
                <button>Edit</button>
                <button>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Workout;
