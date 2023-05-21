import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const api = axios.create({
  baseURL: '/'
});

interface Exercise {
  id: number;
  name: string;
  maxWeight: number;
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

function WorkoutDetails() {
  const { id } = useParams<{ id: string }>();
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [workout, setWorkout] = useState<Workout>();

  const fetchWorkout = () => {
    api.get<Workout>(`/workout/${id}`).then((response) => {
      const fetchedWorkout = response.data;
      setWorkout(fetchedWorkout);
      console.log(response.data)
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

  useEffect(() => {
    fetchWorkout();
  }, [id]);

  useEffect(() => {
    if (workout) {
      fetchExercises();
    }
  }, [workout]);

  return (
    <div className="edit-workout-container">
      <h2>{workout?.name}</h2>
      <div className="table-container">
        <table>
          <thead>
            <tr>
              <th>Exercise Name</th>
              <th>Max Weight</th>
              <th>Muscle Group</th>
            </tr>
          </thead>
          <tbody>
            {exercises.map((exercise) => (
              <tr key={exercise.id}>
                <td>{exercise.name}</td>
                <td>{exercise.maxWeight}</td>
                <td>{exercise.muscleGroup}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default WorkoutDetails;
