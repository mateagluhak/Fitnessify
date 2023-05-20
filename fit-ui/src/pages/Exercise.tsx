import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

function Exercise() {
  const { id } = useParams();
  const navigate = useNavigate();

  // Sample data for exercises
  const [exercises, setExercises] = useState([
    { id: 1, name: "Exercise 1", maxWeight: 100 },
    { id: 2, name: "Exercise 2", maxWeight: 150 },
    { id: 3, name: "Exercise 3", maxWeight: 120 },
  ]);

  const [selectedExercise, setSelectedExercise] = useState<null | { id: number; name: string; maxWeight: number }>(null);

  // Handle deleting an exercise
  const handleDelete = (exerciseId: number) => {
    // Implement delete logic here
  };

  // Handle editing an exercise
  const handleEdit = (exerciseId: number) => {
    const exercise = exercises.find((exercise) => exercise.id === exerciseId);
    setSelectedExercise(exercise || null);
  };

  // Handle form submission for exercise editing
  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // Implement form submission logic here
  };

  // Handle form input change
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    // Implement form input change logic here
  };

  // Handle closing the modal
  const handleCloseModal = () => {
    setSelectedExercise(null);
  };

  return (
    <div>
      <h1>Exercise</h1>
      <div className='table-container'>
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
                <button onClick={() => handleEdit(exercise.id)}>Edit</button>
                <button onClick={() => handleDelete(exercise.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      </div>

      {/* Modal for editing exercise */}
      {selectedExercise && (
        <div className="modal-overlay">
          <div className="modal">
            <div className="modal-content">
              <h3>Edit Exercise</h3>
              <form onSubmit={handleSubmit}>
                <label>
                  Exercise Name:
                  <input
                    type="text"
                    name="name"
                    value={selectedExercise.name}
                    onChange={handleChange}
                  />
                </label>
                <label>
                  Max Weight:
                  <input
                    type="number"
                    name="maxWeight"
                    value={selectedExercise.maxWeight}
                    onChange={handleChange}
                  />
                </label>
                <div>
                  <button type="submit">Save</button>
                  <button onClick={handleCloseModal}>Cancel</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      )}

      {/* Rest of the code... */}
    </div>
  );
}

export default Exercise;
