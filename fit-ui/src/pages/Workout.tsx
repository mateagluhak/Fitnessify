import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from 'axios';

const api = axios.create({
  baseURL: '/'
});

interface Exercise {
  id: number;
  name: string;
}

interface Workout {
  id: number;
  name: string;
  workoutPlanId: number;
  exerciseIds: number[];
}

function Workout() {
  const navigate = useNavigate();
  const [workouts, setWorkouts] = useState<Workout[]>([]);
  const [exercises, setExercises] = useState<Exercise[]>([]);

  useEffect(() => {
    fetchAllWorkouts();
    fetchAllExercises();
  }, []);

  const fetchAllWorkouts = () => {
    api.get<Workout[]>("/workouts").then((response) => {
      setWorkouts(response.data);
    });
  };

  const fetchAllExercises = () => {
    api.get<Exercise[]>("/exercises").then((response) => {
      setExercises(response.data);
    });
  };

  const getExercisesByIds = (exerciseIds: number[]): string => {
    const exerciseNames = exerciseIds.map((id) => {
      const exercise = exercises.find((ex) => ex.id === id);
      return exercise ? exercise.name : '';
    });
    return exerciseNames.join(', ');
  };

  const handleEdit = (id: number) => {
    navigate(`/workout/${id}`);
  };

  const handleDetails = (id: number) => {
    navigate(`/workout/${id}/details`);
  };

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
              <td>{getExercisesByIds(workout.exerciseIds)}</td>
              <td>
                <button onClick={() => handleDetails(workout.id)}>Details</button>
                <button onClick={() => handleEdit(workout.id)}>Edit</button>
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
