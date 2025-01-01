import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const RecommendedHydrationForm: React.FC = () => {
  const [weight, setWeight] = useState('');
  const navigate = useNavigate();

  const handleBack = () => {
    navigate('/');
  };

  const handleSubmit = async () => {
    const weightNumber = parseFloat(weight);

    if (!isNaN(weightNumber) && weightNumber > 0) {
      try {
        const response = await axios.post('http://localhost:8080/api/postRecommendedHydration', {
          weight: weightNumber,
        });
        console.log('Odpowiedź serwera:', response.data);
      } catch (error) {
        console.error('Błąd podczas wysyłania żądania:', error);
      }
    } else {
      console.log('Niepoprawne dane wejściowe!');
    }
  };

  return (
    <div
      style={{
        border: '1px solid #ccc',
        padding: '16px',
        width: '300px',
        borderRadius: '4px',
        background: '#d3d3d3',
      }}
    >
      <h3 style={{ marginTop: 0 }}>Oblicz rekomendowane nawodnienie (w litrach):</h3>

      <div style={{ display: 'flex', alignItems: 'center', marginBottom: '16px' }}>
        <label style={{ width: '100px' }}>Masa ciała:</label>
        <input
          type="number"
          value={weight}
          onChange={(e) => setWeight(e.target.value)}
          style={{ flex: 1 }}
          placeholder="Podaj masę ciała (kg)"
        />
      </div>

      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <button onClick={handleBack} style={{ marginRight: '8px' }}>
          Powrót
        </button>
        <button onClick={handleSubmit}>Zatwierdź</button>
      </div>
    </div>
  );
};

export default RecommendedHydrationForm;
