import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const CaloriePoolForm: React.FC = () => {
  const [age, setAge] = useState('');
  const [weight, setWeight] = useState('');
  const [height, setHeight] = useState('');
  const [gender, setGender] = useState('male'); 
  const [activityLevel, setActivityLevel] = useState('low');
  const [weightPreference, setWeightPreference] = useState('reduction');
  const navigate = useNavigate();

  const handleBack = () => {
    navigate('/');
  };

  const handleSubmit = async () => {
    const ageNumber = parseInt(age, 10);
    const weightNumber = parseFloat(weight);
    const heightNumber = parseFloat(height);

    if (!isNaN(ageNumber) && !isNaN(weightNumber) && !isNaN(heightNumber) && heightNumber > 0) {
      try {
        const response = await axios.post('http://localhost:8080/api/postCaloriePool', {
          gender: gender,
          age: ageNumber,
          weight: weightNumber,
          height: heightNumber,
          activityLevel: activityLevel,
          weightPreference: weightPreference,
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
      <h3 style={{ marginTop: 0 }}>Oblicz pulę kalorii</h3>

      <div style={{ display: 'flex', alignItems: 'center', marginBottom: '8px' }}>
        <label style={{ width: '100px' }}>Płeć:</label>
        <select
          value={gender}
          onChange={(e) => setGender(e.target.value)}
          style={{ flex: 1 }}
        >
          <option value="male">Mężczyzna</option>
          <option value="female">Kobieta</option>
        </select>
      </div>

      <div style={{ display: 'flex', alignItems: 'center', marginBottom: '8px' }}>
        <label style={{ width: '100px' }}>Wiek (lata):</label>
        <input
          type="number"
          value={age}
          onChange={(e) => setAge(e.target.value)}
          style={{ flex: 1 }}
          placeholder="Podaj wzrost"
        />
      </div>

      <div style={{ display: 'flex', alignItems: 'center', marginBottom: '8px' }}>
        <label style={{ width: '100px' }}>Wzrost (m):</label>
        <input
          type="number"
          value={height}
          onChange={(e) => setHeight(e.target.value)}
          style={{ flex: 1 }}
          placeholder="Podaj wzrost"
        />
      </div>

      

      <div style={{ display: 'flex', alignItems: 'center', marginBottom: '8px' }}>
        <label style={{ width: '100px' }}>Waga (kg):</label>
        <input
          type="number"
          value={weight}
          onChange={(e) => setWeight(e.target.value)}
          style={{ flex: 1 }}
          placeholder="Podaj wagę"
        />
      </div>

      <div style={{ display: 'flex', alignItems: 'center', marginBottom: '8px' }}>
        <label style={{ width: '100px' }}>Aktywność:</label>
        <select
          value={activityLevel}
          onChange={(e) => setActivityLevel(e.target.value)}
          style={{ flex: 1 }}
        >
          <option value="low">Niska</option>
          <option value="moderate">Umiarkowana</option>
          <option value="high">Wysoka</option>
        </select>
      </div>

      <div style={{ display: 'flex', alignItems: 'center', marginBottom: '16px' }}>
        <label style={{ width: '100px' }}>Preferencja:</label>
        <select
          value={weightPreference}
          onChange={(e) => setWeightPreference(e.target.value)}
          style={{ flex: 1 }}
        >
          <option value="reduction">Redukcja wagi</option>
          <option value="stabilization">Stabilizacja wagi</option>
          <option value="gain">Wzrost wagi</option>
        </select>
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

export default CaloriePoolForm;
