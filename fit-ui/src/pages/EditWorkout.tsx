import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { FaEdit } from 'react-icons/fa';
import axios from 'axios';

const api = axios.create({
  baseURL: '/'
});

interface Exercise {
  id: number;
  name: string;
  maxWeight: number | null;
}

interface Workout {
  id: number;
  name: string;
  workoutPlanId: number;
  exerciseIds: number[];
}

function EditWorkout() {
  const { id } = useParams<{ id: string }>();
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [allExercises, setAllExercises] = useState<Exercise[]>([]); // Updated state for all exercises
  const [workout, setWorkout] = useState<Workout>();
  const [workoutName, setWorkoutName] = useState('');
  const [isEditing, setIsEditing] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedExerciseId, setSelectedExerciseId] = useState<number | null>(null);

  const fetchWorkout = () => {
    api.get<Workout>(`/workout/${id}`).then((response) => {
      const fetchedWorkout = response.data;
      setWorkout(fetchedWorkout);
      setWorkoutName(fetchedWorkout.name);
    });
  };

  const fetchAllExercises = () => {
    api.get<Exercise[]>('/exercises').then((response) => {
      setAllExercises(response.data);
    });
  };

  const fetchExercises = () => {
    api.get<Exercise[]>('/exercises').then((response) => {
      const allExercises = response.data;
      if (workout && workout.exerciseIds) {
        const workoutExercises = allExercises.filter((exercise) =>
          workout.exerciseIds.includes(exercise.id)
        );
        setExercises(workoutExercises);
      } else {
        setExercises([]);
      }
    });
  };

  const handleEditWorkoutName = () => {
    setIsEditing(true);
  };

  const handleSubmitWorkoutName = (event: React.FormEvent) => {
    event.preventDefault();
    setIsEditing(false);
    // Update workout name logic here
  };

  const handleAddExercise = () => {
    setIsModalOpen(true);
  };

  const handleModalClose = () => {
    setIsModalOpen(false);
  };

  const handleExerciseSelect = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedId = Number(event.target.value);
    setSelectedExerciseId(selectedId);
  };

// ...import statements and component definition...

const handleExerciseSubmit = (event: React.FormEvent) => {
  event.preventDefault();
  
  if (selectedExerciseId && workout) {
    const updatedWorkout = {
      ...workout,
      exerciseIds: [...workout.exerciseIds, selectedExerciseId]
    };

    api.put(`/workout/edit/${workout.id}`, updatedWorkout)
      .then((response) => {
        // Handle the successful response
        const updatedWorkout = response.data;
        setWorkout(updatedWorkout);
      })
      .catch((error) => {
        // Handle the error
        console.error('Failed to update workout:', error);
      });
  }

  setSelectedExerciseId(null);
  setIsModalOpen(false);
};

const handleRemove = (exerciseId: number) => {
  if (workout) {
    const updatedExerciseIds = workout.exerciseIds.filter(id => id !== exerciseId);
    const updatedWorkout = {
      ...workout,
      exerciseIds: updatedExerciseIds
    };

    api.put(`/workout/edit/${workout.id}`, updatedWorkout)
      .then((response) => {
        // Handle the successful response
        const updatedWorkout = response.data;
        setWorkout(updatedWorkout);
      })
      .catch((error) => {
        // Handle the error
        console.error('Failed to update workout:', error);
      });
  }
};



const handleRefresh = () => {
  window.location.reload();
};

  useEffect(() => {
    fetchWorkout();
    fetchAllExercises(); // Fetch all exercises
  }, [id]);

  useEffect(() => {
    if (workout) {
      fetchExercises();
    }
  }, [workout]);

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
                <button onClick={() => handleRemove(exercise.id)}>Remove</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <button onClick={handleAddExercise}>Add Exercise</button>

      {isModalOpen && (
        <div className="modal">
          <div className="modal-content">
            <h3>Select an Exercise</h3>
            <form onSubmit={handleExerciseSubmit}>
              <select value={selectedExerciseId || ''} onChange={handleExerciseSelect}>
                <option value="">Select an exercise</option>
                {allExercises.map((exercise) => (
                  <option key={exercise.id} value={exercise.id}>
                    {exercise.name}
                  </option>
                ))}
              </select>
              <button type="submit">Save</button>
              <button onClick={handleModalClose}>Cancel</button>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default EditWorkout;
