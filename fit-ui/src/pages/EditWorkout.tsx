import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { FaEdit } from 'react-icons/fa';
import axios from 'axios';

const api = axios.create({
  baseURL: '/'
});

interface Exercise {
  id: number;
  name: string;
  maxWeight: number | null;
  muscleGroup: string;
}

interface WorkoutExerciseData {
  exerciseId: number;
  priorityId: number;
  repetitions: number;
}

interface Workout {
  id: number;
  name: string;
  workoutPlanId: number;
  workoutExerciseData: WorkoutExerciseData[];
}

function EditWorkout() {
  const { id } = useParams<{ id: string }>();
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [allExercises, setAllExercises] = useState<Exercise[]>([]);
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
      if (workout && workout.workoutExerciseData) {
        const exerciseIds = workout.workoutExerciseData.map(
          (workoutExerciseData) => workoutExerciseData.exerciseId
        );
        const workoutExercises = allExercises.filter((exercise) =>
          exerciseIds.includes(exercise.id)
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
    if (workout) {
      const updatedWorkout = { ...workout, name: workoutName };
      api
        .put(`/workout/edit/${workout.id}`, updatedWorkout)
        .then((response) => {
          const updatedWorkoutData = response.data;
          setWorkout(updatedWorkoutData);
          setWorkoutName(updatedWorkoutData.name); // Update the workout name in the state
        })
        .catch((error) => {
          console.error('Failed to update workout:', error);
        });
    }
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

  const handleExerciseSubmit = (event: React.FormEvent) => {
    event.preventDefault();
  
    if (selectedExerciseId && workout) {
      const selectedExercise = allExercises.find(
        (exercise) => exercise.id === selectedExerciseId
      );
  
      if (selectedExercise) {
        const updatedWorkout = {
          ...workout,
          workoutExerciseData: [
            ...workout.workoutExerciseData,
            {
              exerciseId: selectedExerciseId,
              priorityId: 1,
              repetitions: 1
            }
          ]
        };
  
        api
          .put(`/workout/edit/${workout.id}`, updatedWorkout)
          .then((response) => {
            const updatedWorkout = response.data;
            setWorkout(updatedWorkout);
          })
          .catch((error) => {
            console.error('Failed to update workout:', error);
          });
      }
    }
  
    setSelectedExerciseId(null);
    setIsModalOpen(false);
  };
  

  const handleRemove = (exerciseId: number) => {
    if (workout) {
      const updatedWorkoutExerciseData = workout.workoutExerciseData.filter(
        (exerciseData) => exerciseData.exerciseId !== exerciseId
      );

      const updatedWorkout = {
        ...workout,
        workoutExerciseData: updatedWorkoutExerciseData
      };

      api
        .put(`/workout/edit/${workout.id}`, updatedWorkout)
        .then((response) => {
          const updatedWorkout = response.data;
          setWorkout(updatedWorkout);
        })
        .catch((error) => {
          console.error('Failed to update workout:', error);
        });
    }
  };

  const handleRefresh = () => {
    window.location.reload();
  };

  useEffect(() => {
    fetchWorkout();
    fetchAllExercises();
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
            {workoutName}{' '}
            <FaEdit className="edit-icon" onClick={handleEditWorkoutName} />
          </>
        )}
      </h2>
      <div className="table-container">
        <table>
          <thead>
            <tr>
              <th>Exercise Name</th>
              <th>Max Weight</th>
              <th>Muscle Group</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {exercises.map((exercise) => (
              <tr key={exercise.id}>
                <td>{exercise.name}</td>
                <td>{exercise.maxWeight}</td>
                <td>{exercise.muscleGroup}</td>
                <td>
                  <button onClick={() => handleRemove(exercise.id)}>
                    Remove
                  </button>
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
              <select
                value={selectedExerciseId || ''}
                onChange={handleExerciseSelect}
              >
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
