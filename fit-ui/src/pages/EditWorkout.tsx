import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { FaEdit } from 'react-icons/fa';

function EditWorkout() {
  const { id } = useParams();
  const navigate = useNavigate();

  // Sample data for exercises
  const [exercises, setExercises] = useState([
    { id: 1, name: 'Exercise 1', maxWeight: 100 },
    { id: 2, name: 'Exercise 2', maxWeight: 150 },
    { id: 3, name: 'Exercise 3', maxWeight: 120 },
  ]);

  const [selectedExercise, setSelectedExercise] = useState<null | { id: number; name: string; maxWeight: number }>(null);
  const [workoutName, setWorkoutName] = useState(`Workout ${id}`);
  const [isEditing, setIsEditing] = useState(false);

  // Handle deleting an exercise
  const handleDelete = (exerciseId: number) => {
    // Implement delete logic here
  };

  // Handle form submission for exercise editing
  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // Implement form submission logic here
  };

  const handleEditWorkoutName = () => {
    setIsEditing(!isEditing);
  };

  const handleSubmitWorkoutName = (event:any) => {
    event.preventDefault();
    setIsEditing(false);
    // Perform any necessary logic with the updated workout name
  };

  return (
    <div className="edit-workout-container">
      <h2>
        {isEditing ? (
          <form onSubmit={handleSubmitWorkoutName}>
            <input
              type="text"
              value={workoutName}
              onChange={(event) => setWorkoutName(event.target.value)}
            />
            <button type="submit">Save</button>
          </form>
        ) : (
          <>
            {workoutName} <FaEdit className="edit-icon" onClick={handleEditWorkoutName} />
          </>
        )}
      </h2>
      <div className="table-container">
        <table>
          <thead>
            <tr>
              <th>Exercise Name</th>
              <th>Max Weight</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {exercises.map((exercise) => (
              <tr key={exercise.id}>
                <td>{exercise.name}</td>
                <td>{exercise.maxWeight}</td>
                <td>
                  <button onClick={() => handleDelete(exercise.id)}>Remove</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <button>Add Exercise</button>
    </div>
  );
}

export default EditWorkout;
