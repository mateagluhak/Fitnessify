import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
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

function Exercise() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [newExercise, setNewExercise] = useState<Exercise>({
    id: 0,
    name: '',
    maxWeight: null,
    muscleGroup: ''
  });
  const [isModalOpen, setIsModalOpen] = useState(false);

  const fetchAllExercises = () => {
    api.get<Exercise[]>("/exercises").then((response) => {
      setExercises(response.data);
    });
  };

  const handleDelete = (id: number) => {
    if (window.confirm("Are you sure you want to delete?")){
      api.delete(`/exercise/${id}`).then(() => {
        fetchAllExercises();
      })
    }
  };

  const handleCreate = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    api.post<Exercise>(`/exercise/create`, newExercise).then(() => {
      setNewExercise({
        id: 0,
        name: '',
        maxWeight: null,
        muscleGroup: ''
      });
      setIsModalOpen(false);
      fetchAllExercises();
    });
  };

  const handleRefresh = () => {
    window.location.reload();
  };

  const handleEdit = (exerciseId: number) => {
    // Implement edit logic here
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  useEffect(() => {
    fetchAllExercises();
  }, []);

  return (
    <div>
      <div className='title-button-container'>
        <h1>Exercise</h1>
        <button className='create-button' onClick={() => setIsModalOpen(true)}>Create Exercise</button>
      </div>
      <div className='table-container'>
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
                  <button onClick={() => handleEdit(exercise.id)}>Edit</button>
                  <button onClick={() => {handleDelete(exercise.id); handleRefresh();}}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {isModalOpen && (
        <div className="modal">
          <div className="modal-content">
            <h3>Create Exercise</h3>
            <form onSubmit={handleCreate}>
              <label>
                Exercise Name:
                <input type="text" value={newExercise.name} onChange={(event) => setNewExercise({ ...newExercise, name: event.target.value })} />
              </label>
              <label>
                Max Weight:
                <input
                      type="number"
                      value={newExercise.maxWeight === null ? '' : String(newExercise.maxWeight)}
                      onChange={(event) =>
                        setNewExercise({
                          ...newExercise,
                          maxWeight: event.target.value === '' ? null : Number(event.target.value)
                        })
                      }
                />

              </label>
              <div>
                <button type="submit">Save</button>
                <button onClick={handleCloseModal}>Cancel</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default Exercise;
