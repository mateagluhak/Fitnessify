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

interface WorkoutPlan {
  id: number;
  name: string;
  profile_id: number;
}

interface Workout {
  id: number;
  name: string;
  workoutPlanId: number;
  workoutExerciseData: WorkoutExerciseData[];
}

interface Priority {
  id: number,
  name: string
}

function WorkoutDetails() {
  const { id } = useParams<{ id: string }>();
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [workout, setWorkout] = useState<Workout>();
  const [workoutPlans, setWorkoutPlans] = useState<WorkoutPlan[]>([]);
  const [allPriorities, setAllPriorities] = useState<Priority[]>([]);

  const fetchWorkout = () => {
    api.get<Workout>(`/workout/${id}`).then((response) => {
      const fetchedWorkout = response.data;
      setWorkout(fetchedWorkout);
      console.log(response.data);
    });
  };

  const fetchAllWorkoutPlans = () => {
    api.get<WorkoutPlan[]>("/workout_plans").then((response) => {
      setWorkoutPlans(response.data);
    });
  };

  const fetchAllPriorities = () => {
    api.get<Priority[]>("/priorities").then((response) => {
      setAllPriorities(response.data);
    });
  };

  const getWorkoutPlan = (workoutPlanId: any): string => {
    const workoutPlan = workoutPlans.find((wp) => wp.id === workoutPlanId);
    return workoutPlan ? workoutPlan.name : '';
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
    fetchAllWorkoutPlans();
    fetchAllPriorities();
  }, [id]);

  useEffect(() => {
    if (workout) {
      fetchExercises();
    }
  }, [workout]);

  return (
    <div className="edit-workout-container">
      <h2>{workout?.name}</h2>
      <h3>{getWorkoutPlan(workout?.workoutPlanId)}</h3>
      <div className="table-container">
        <table>
          <thead>
            <tr>
              <th>Exercise Name</th>
              <th>Max Weight</th>
              <th>Muscle Group</th>
              <th>Priority</th>
              <th>Repetitions</th>
            </tr>
          </thead>
          <tbody>
            {exercises.map((exercise) => {
              const workoutExerciseData = workout?.workoutExerciseData.find(
                (wed) => wed.exerciseId === exercise.id
              );
              const priority = allPriorities.find((p) => p.id === workoutExerciseData?.priorityId);
              return (
                <tr key={exercise.id}>
                  <td>{exercise.name}</td>
                  <td>{exercise.maxWeight}</td>
                  <td>{exercise.muscleGroup}</td>
                  <td>{priority?.name}</td>
                  <td>{workoutExerciseData?.repetitions}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default WorkoutDetails;
