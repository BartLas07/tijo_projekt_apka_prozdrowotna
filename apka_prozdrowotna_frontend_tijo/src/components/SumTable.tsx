import React from 'react';

interface SumData {
  protein: number;
  sodium: number;
  carbohydrates: number;
  fats: number;
  cholesterol: number;
  sugar: number;
  fiber: number;
  calories: number;
}

interface SumTableProps {
  mealName: string;
  sumData: SumData;
  containerStyle?: React.CSSProperties;
}

const SumTable: React.FC<SumTableProps> = ({ mealName, sumData, containerStyle }) => {
  // Helper function to round numbers to two decimal places
  const formatValue = (value: number) => value.toFixed(2);

  return (
    <div style={{ ...sumTableContainerStyle, ...containerStyle }}>
      <div style={sumHeaderStyle}>
        <h3>Suma składników {mealName}</h3>
      </div>
      <table style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr
            style={{
              borderBottom: '1px solid black',
              fontWeight: 'bold',
              textAlign: 'left',
            }}
          >
            <th style={{ padding: '6px' }}>Białko</th>
            <th style={{ padding: '6px' }}>Sód</th>
            <th style={{ padding: '6px' }}>Węglowodany</th>
            <th style={{ padding: '6px' }}>Tłuszcz</th>
            <th style={{ padding: '6px' }}>Cholesterol</th>
            <th style={{ padding: '6px' }}>Cukier</th>
            <th style={{ padding: '6px' }}>Błonnik</th>
            <th style={{ padding: '6px' }}>Kalorie</th>
          </tr>
        </thead>
        <tbody>
          <tr style={{ borderBottom: '1px solid #e0e0e0' }}>
            <td style={{ padding: '6px' }}>{formatValue(sumData.protein)}g</td>
            <td style={{ padding: '6px' }}>{formatValue(sumData.sodium)}g</td>
            <td style={{ padding: '6px' }}>{formatValue(sumData.carbohydrates)}g</td>
            <td style={{ padding: '6px' }}>{formatValue(sumData.fats)}g</td>
            <td style={{ padding: '6px' }}>{formatValue(sumData.cholesterol)}g</td>
            <td style={{ padding: '6px' }}>{formatValue(sumData.sugar)}g</td>
            <td style={{ padding: '6px' }}>{formatValue(sumData.fiber)}g</td>
            <td style={{ padding: '6px' }}>{formatValue(sumData.calories)}</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

// Updated styles for the sum table container and header
const sumTableContainerStyle: React.CSSProperties = {
  background: 'white',
  borderRadius: '12px',
  padding: '10px',
  display: 'flex',
  flexDirection: 'column',
  flex: '1',
};

const sumHeaderStyle: React.CSSProperties = {
  backgroundColor: '#7ad557',
  padding: '15px',
  borderTopLeftRadius: '12px',
  borderTopRightRadius: '12px',
  fontSize: '16px',
  color: '#fff',
  fontWeight: 'bold',
  marginBottom: '10px',
};

export default SumTable;
