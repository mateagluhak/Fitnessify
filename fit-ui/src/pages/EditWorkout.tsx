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
  priorityId: number | null;
  repetitions: number | null;
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

interface Priority {
  id: number,
  name: string
}

function EditWorkout() {
  const { id } = useParams<{ id: string }>();
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [allExercises, setAllExercises] = useState<Exercise[]>([]);
  const [workout, setWorkout] = useState<Workout>();
  const [workoutName, setWorkoutName] = useState('');
  const [isEditing, setIsEditing] = useState(false);
  const [isEditing2, setIsEditing2] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isModalOpen2, setIsModalOpen2] = useState(false);
  const [selectedExerciseId, setSelectedExerciseId] = useState<number | null>(null);
  const [workoutPlans, setWorkoutPlans] = useState<WorkoutPlan[]>([]);
  const [selectedWorkoutPlanId, setSelectedWorkoutPlanId] = useState<number | null>(null);
  const [selectedPriority, setSelectedPriority] = useState<number | null>(1);
  const [selectedRepetitions, setSelectedRepetitions] = useState<number | null>(1);  
  const [selectedExercise, setSelectedExercise] = useState<Exercise | null>(null);
  const [allPriorities, setAllPriorities] = useState<Priority[]>([]);
  const [priority, setPriorityDropDown] = useState<number | null>();
  const [priority2, setPriorityDropDown2] = useState<number | null>();


  const fetchWorkout = () => {
    api.get<Workout>(`/workout/${id}`).then((response) => {
      const fetchedWorkout = response.data;
      setWorkout(fetchedWorkout);
      setWorkoutName(fetchedWorkout.name);
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
  
  const getWorkoutPlan = (workoutPlanId: number): string => {
    const workoutPlan = workoutPlans.find((wp) => wp.id === workoutPlanId);
    return workoutPlan ? workoutPlan.name : '';
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
  const handleEditWorkoutPlanName = () => {
    setIsEditing2(true);
  };

  const handleSubmitWorkoutPlanName = (event: React.FormEvent) => {
    event.preventDefault();
  
    if (selectedWorkoutPlanId && workout) {
      const updatedWorkoutPlan = workoutPlans.find(plan => plan.id === selectedWorkoutPlanId);
  
      if (updatedWorkoutPlan) {
        const updatedWorkout = {
          ...workout,
          workoutPlanId: updatedWorkoutPlan.id
        };
  
        api
          .put(`/workout/edit/${workout.id}`, updatedWorkout)
          .then((response) => {
            const updatedWorkout = response.data;
            setWorkout(updatedWorkout);
            setIsEditing2(false);
          })
          .catch((error) => {
            console.error('Failed to update workout:', error);
          });
      }
    }
  };
  

  const handleAddExercise = () => {
    setIsModalOpen(true);
  };
  
  const handleModalClose = () => {
    setIsModalOpen(false);
  };

  const handleEdit = (exercise: Exercise) => {
    setSelectedExercise(exercise);
    setIsModalOpen2(true);
  };
  
  const handleModalClose2 = () => {
    setIsModalOpen2(false);
  };
  
  const handleExerciseSelect = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedId = Number(event.target.value);
    setSelectedExerciseId(selectedId);
  };

  const handlePrioritySelect = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedId = Number(event.target.value);
    setPriorityDropDown(selectedId);
  };

  const handlePrioritySelect2 = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedId = Number(event.target.value);
    setPriorityDropDown2(selectedId);
  };
  
  const handleExerciseSubmit = (event: React.FormEvent) => {
    event.preventDefault();
  
    if (selectedExerciseId && workout) {
      const selectedExercise = allExercises.find(
        (exercise) => exercise.id === selectedExerciseId
      );
  
      if (selectedExercise) {
        const updatedWorkoutExerciseData = [
          ...workout.workoutExerciseData,
          {
            exerciseId: selectedExerciseId,
            priorityId: priority,
            repetitions: selectedRepetitions,
          },
        ];
  
        const updatedWorkout = {
          ...workout,
          workoutExerciseData: updatedWorkoutExerciseData,
        };
  
        api
          .put(`/workout/edit/${workout.id}`, updatedWorkout)
          .then((response) => {
            const updatedWorkout = response.data;
            setWorkout(updatedWorkout);
  
            // Fetch the updated exercise data
            fetchExercises();
            handleRefresh();
          })
          .catch((error) => {
            console.error('Failed to update workout:', error);
          });
      }
    }
  
    setSelectedExerciseId(null);
    setSelectedPriority(1); 
    setSelectedRepetitions(1);
    setIsModalOpen(false);
  };
  
  
  

  const handleRemove = (exerciseId: number) => {
    if (workout) {
      const updatedWorkoutExerciseData = workout.workoutExerciseData.filter(
        (exerciseData) => exerciseData.exerciseId !== exerciseId
      );
  
      const updatedWorkout = {
        ...workout,
        workoutExerciseData: updatedWorkoutExerciseData,
      };
  
      api
        .put(`/workout/edit/${workout.id}`, updatedWorkout)
        .then((response) => {
          const updatedWorkout = response.data;
          setWorkout(updatedWorkout);

          fetchExercises();
        })
        .catch((error) => {
          console.error('Failed to update workout:', error);
        });
    }
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

  const handleEditSubmit = (event: React.FormEvent) => {
    event.preventDefault();
  
    if (selectedExercise && workout) {
      const updatedWorkoutExerciseData = workout.workoutExerciseData.map(
        (exerciseData) => {
          if (exerciseData.exerciseId === selectedExercise.id) {
            return {
              ...exerciseData,
              priorityId: priority2,
              repetitions: selectedRepetitions,
            };
          }
          return exerciseData;
        }
      );
  
      const updatedWorkout = {
        ...workout,
        workoutExerciseData: updatedWorkoutExerciseData,
      };
  
      api
        .put(`/workout/edit/${workout.id}`, updatedWorkout)
        .then((response) => {
          const updatedWorkout = response.data;
          setWorkout(updatedWorkout);
  
          // Fetch the updated exercise data
          fetchExercises();
        })
        .catch((error) => {
          console.error('Failed to update workout:', error);
        });
    }
  
    setSelectedExercise(null);
    setSelectedPriority(1);
    setSelectedRepetitions(1);
    setIsModalOpen2(false);
  };
  
  

  const handleRefresh = () => {
    window.location.reload();
  };

  useEffect(() => {
    fetchWorkout();
    fetchAllExercises();
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
          <h3>
      {isEditing2 ? (
        <form onSubmit={handleSubmitWorkoutPlanName}>
          <select
            value={selectedWorkoutPlanId ?? ''}
            onChange={(event) => setSelectedWorkoutPlanId(Number(event.target.value))}
          >
            <option value="">Select a workout plan</option>
            {workoutPlans.map((workoutPlan) => (
              <option key={workoutPlan.id} value={workoutPlan.id}>
                {workoutPlan.name}
              </option>
            ))}
          </select>
          <button type="submit">Save</button>
        </form>
      ) : (
        <>
          {workout && getWorkoutPlan(workout.workoutPlanId)}
          <FaEdit className="edit-icon" onClick={handleEditWorkoutPlanName} />
        </>
      )}
    </h3>



      <div className="table-container">
        <table>
          <thead>
            <tr>
              <th>Exercise Name</th>
              <th>Max Weight</th>
              <th>Muscle Group</th>
              <th>Priority</th>
              <th>Repetitions</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {exercises.map((exercise) => {
              const exerciseData = workout?.workoutExerciseData.find(
                (data) => data.exerciseId === exercise.id
              );
              const priority = allPriorities.find((p) => p.id === exerciseData?.priorityId);
              
              return (
                <tr key={exercise.id}>
                  <td>{exercise.name}</td>
                  <td>{exercise.maxWeight}</td>
                  <td>{exercise.muscleGroup}</td>
                  {priority && <td>{priority.name}</td>}
                  <td>{exerciseData?.repetitions}</td>
                  <td>
                  <button onClick={() => handleEdit(exercise)}>Edit</button>
                    <button onClick={() => handleRemove(exercise.id)}>
                      Remove
                    </button>
                  </td>
                </tr>
              );
            })}
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
        <label>
          <select
          value={priority || ''}
          onChange={handlePrioritySelect}
        >
          <option value="">Select a priority</option>
          {allPriorities.map((pr) => (
            <option key={pr.id} value={pr.id}>
              {pr.name}
            </option>
          ))}
        </select>
        </label>
        <label>
          Repetitions:
              <input
                      type="number"
                      value={selectedRepetitions === null ? '' : String(selectedRepetitions)}
                      onChange={(event) => {
                        const value = Number(event.target.value);
                        if (value !== null || event.target.value === '') {
                          setSelectedRepetitions(value);
                        }
                      }}
                />

        </label>
        <button type="submit">Save</button>
        <button onClick={handleModalClose}>Cancel</button>
      </form>
    </div>
  </div>
)}

{isModalOpen2 && (
  <div className="modal">
    <div className="modal-content">
      <h3>Edit</h3>
      <form onSubmit={handleEditSubmit}>
        <label>
          Priority:
          <select
          value={priority2 || ''}
          onChange={(event) => handlePrioritySelect2(event)}
        >
          <option value="">Select a priority</option>
          {allPriorities.map((pr) => (
            <option key={pr.id} value={pr.id}>
              {pr.name}
            </option>
          ))}
        </select>
        </label>
        <label>
          Repetitions:
              <input
                      type="number"
                      value={selectedRepetitions === null ? '' : String(selectedRepetitions)}
                      onChange={(event) => {
                        const value = Number(event.target.value);
                        if (value !== null || event.target.value === '') {
                          setSelectedRepetitions(value);
                        }
                      }}
                />

        </label>
        <button type="submit">Save</button>
        <button onClick={handleModalClose2}>Cancel</button>
      </form>
    </div>
  </div>
)}

    </div>
  );
}

export default EditWorkout;
