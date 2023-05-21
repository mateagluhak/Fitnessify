import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from 'axios';

const api = axios.create({
  baseURL: '/'
});

interface Exercise {
  id: number;
  name: string;
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

interface WorkoutPlan {
  id: number;
  name: string;
  profile_id: number;
}

function Workout() {
  const navigate = useNavigate();
  const [workouts, setWorkouts] = useState<Workout[]>([]);
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [workoutPlans, setWorkoutPlans] = useState<WorkoutPlan[]>([]);

  useEffect(() => {
    fetchAllWorkouts();
    fetchAllExercises();
    fetchAllWorkoutPlans();
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

  const fetchAllWorkoutPlans = () => {
    api.get<WorkoutPlan[]>("/workout_plans").then((response) => {
      setWorkoutPlans(response.data);
    });
  };

  const getExercisesByIds = (workoutExerciseData: WorkoutExerciseData[]): string => {
    const exerciseNames = workoutExerciseData.map((wed) => {
      const exercise = exercises.find((ex) => ex.id === wed.exerciseId);
      return exercise ? exercise.name : '';
    });
    return exerciseNames.join(', ');
  };

  const getWorkoutPlan = (workoutPlanId: number): string => {
    const workoutPlan = workoutPlans.find((wp) => wp.id === workoutPlanId);
    return workoutPlan ? workoutPlan.name : '';
  };

  const handleEdit = (id: number) => {
    navigate(`/workout/${id}`);
  };

  const handleDetails = (id: number) => {
    navigate(`/workout/${id}/details`);
  };

  const handleDelete = (id: number) => {
    console.log("Zelim obrisat: ", id)
    if (window.confirm("Are you sure you want to delete?")) {
      api.delete(`/workout/${id}`).then(() => {
        fetchAllWorkouts();
      })
    }
  };

  const handleRefresh = () => {
    window.location.reload();
  };

  return (
    <div className="workout-container">
      <h1>Workout</h1>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Exercises</th>
            <th>Workout Plan</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {workouts.map((workout) => (
            <tr key={workout.id}>
              <td>{workout.name}</td>
              <td>{getExercisesByIds(workout.workoutExerciseData)}</td>
              <td>{getWorkoutPlan(workout.workoutPlanId)}</td>
              <td>
                <button onClick={() => handleDetails(workout.id)}>Details</button>
                <button onClick={() => handleEdit(workout.id)}>Edit</button>
                <button onClick={() => { handleDelete(workout.id); handleRefresh(); }}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Workout;
