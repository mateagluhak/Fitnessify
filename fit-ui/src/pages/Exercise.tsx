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
  muscleGroup: string | null;
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
  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [editedExercise, setEditedExercise] = useState<Exercise | null>(null);

  const fetchAllExercises = () => {
    api.get<Exercise[]>("/exercises").then((response) => {
      setExercises(response.data);
    });
  };

  const handleDelete = (id: number) => {
    if (window.confirm("Are you sure you want to delete?")) {
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
      setIsCreateModalOpen(false);
      fetchAllExercises();
    })
    .catch((error) => {
      console.error('Failed to create exercise', error);
    });
  };

  const handleEdit = (exerciseId: number) => {
    const exerciseToEdit = exercises.find(exercise => exercise.id === exerciseId);
    if (exerciseToEdit) {
      setEditedExercise(exerciseToEdit);
      setIsEditModalOpen(true);
    }
  };

  const handleUpdate = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (editedExercise) {
      api.put<Exercise>(`/exercise/edit/${editedExercise.id}`, editedExercise).then(() => {
        setEditedExercise(null);
        setIsEditModalOpen(false);
        fetchAllExercises();
      })
      .catch((error) => {
        console.error('Failed to update exercise', error);
      });
    }
  };

  const handleCloseCreateModal = () => {
    setNewExercise({
      id: 0,
      name: '',
      maxWeight: null,
      muscleGroup: ''
    });
    setIsCreateModalOpen(false);
  };

  const handleCloseEditModal = () => {
    setEditedExercise(null);
    setIsEditModalOpen(false);
  };

  const handleRefresh = () => {
    window.location.reload();
  };

  useEffect(() => {
    fetchAllExercises();
  }, []);

  return (
    <div>
      <div className='title-button-container'>
        <h1>Exercise</h1>
        <button className='create-button' onClick={() => setIsCreateModalOpen(true)}>Create Exercise</button>
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

      {isCreateModalOpen && (
        <div className="modal">
          <div className="modal-content">
            <h3>Create Exercise</h3>
            <form onSubmit={handleCreate}>
              <label>
                Exercise Name:
                <input
                  type="text"
                  value={newExercise.name}
                  onChange={(event) =>
                    setNewExercise({
                      ...newExercise,
                      name: event.target.value
                    })
                  }
                />
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
              <label>
                Muscle Group:
                <input
                  type="text"
                  value={newExercise.muscleGroup === null ? '' : String(newExercise.muscleGroup)}
                  onChange={(event) =>
                    setNewExercise({
                      ...newExercise,
                      muscleGroup: event.target.value === '' ? null : String(event.target.value)
                    })
                  }
                />
              </label>
              <div>
                <button type="submit">Save</button>
                <button onClick={handleCloseCreateModal}>Cancel</button>
              </div>
            </form>
          </div>
        </div>
      )}

      {isEditModalOpen && editedExercise && (
        <div className="modal">
          <div className="modal-content">
            <h3>Edit Exercise</h3>
            <form onSubmit={handleUpdate}>
              <label>
                Exercise Name:
                <input
                  type="text"
                  value={editedExercise.name}
                  onChange={(event) =>
                    setEditedExercise({
                      ...editedExercise,
                      name: event.target.value
                    })
                  }
                />
              </label>
              <label>
                Max Weight:
                <input
                  type="number"
                  value={editedExercise.maxWeight === null ? '' : String(editedExercise.maxWeight)}
                  onChange={(event) =>
                    setEditedExercise({
                      ...editedExercise,
                      maxWeight: event.target.value === '' ? null : Number(event.target.value)
                    })
                  }
                />
              </label>
              <label>
                Muscle Group:
                <input
                  type="text"
                  value={editedExercise.muscleGroup === null ? '' : String(editedExercise.muscleGroup)}
                  onChange={(event) =>
                    setEditedExercise({
                      ...editedExercise,
                      muscleGroup: event.target.value === '' ? null : String(event.target.value)
                    })
                  }
                />
              </label>
              <div>
                <button type="submit">Save</button>
                <button onClick={handleCloseEditModal}>Cancel</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default Exercise;
