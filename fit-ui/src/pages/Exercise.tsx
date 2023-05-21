import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const api = axios.create({
  baseURL: '/'
});

interface Exercise {
  id: number;
  name: string;
  maxWeight: number;
}

function Exercise() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [exercises, setExercises] = useState<Exercise[]>([]);

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

  const handleRefresh = () => {
    window.location.reload();
  };

  const handleEdit = (exerciseId: number) => {
    // Implement edit logic here
  };

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // Implement form submission logic here
  };

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    // Implement form input change logic here
  };

  const handleCloseModal = () => {
    // Implement close modal logic here
  };

  useEffect(() => {
    fetchAllExercises();
  }, []);

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
                  <button onClick={() => {handleDelete(exercise.id); handleRefresh();}}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Modal for editing exercise */}
      {/* Rest of the code... */}
    </div>
  );
}

export default Exercise;
