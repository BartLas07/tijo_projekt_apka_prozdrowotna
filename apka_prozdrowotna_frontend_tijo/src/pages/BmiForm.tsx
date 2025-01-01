import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const BmiForm: React.FC = () => {
  const [height, setHeight] = useState('');
  const [weight, setWeight] = useState('');
  const navigate = useNavigate();

  const handlePowrot = () => {
    navigate('/');
  };

  const handleZatwierdz = async () => {
    const heightNumber = parseFloat(height);
    const weightNumber = parseFloat(weight);

    if (!isNaN(weightNumber) && !isNaN(heightNumber) && heightNumber > 0) {
      try {
      
        const response = await axios.post('http://localhost:8080/api/postBmi', {
          weight: weightNumber,
          height: heightNumber,
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
        background:'#d3d3d3'
      }}
    >
      <h3 style={{ marginTop: 0 }}>Oblicz swoje BMI</h3>

      <div style={{ display: 'flex', alignItems: 'center', marginBottom: '8px' }}>
        <label style={{ width: '80px' }}>Wzrost:</label>
        <input
          type="number"
          value={height}
          onChange={(e) => setHeight(e.target.value)}
          style={{ flex: 1 }}
          placeholder="Podaj wzrost w metrach"
        />
      </div>

      <div style={{ display: 'flex', alignItems: 'center', marginBottom: '16px' }}>
        <label style={{ width: '80px' }}>Waga:</label>
        <input
          type="number"
          value={weight}
          onChange={(e) => setWeight(e.target.value)}
          style={{ flex: 1 }}
          placeholder="Podaj wagę w kg"
        />
      </div>

      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <button onClick={handlePowrot} style={{ marginRight: '8px' }}>
          Powrót
        </button>
        <button onClick={handleZatwierdz}>Zatwierdź</button>
      </div>
    </div>
  );
};

export default BmiForm;
