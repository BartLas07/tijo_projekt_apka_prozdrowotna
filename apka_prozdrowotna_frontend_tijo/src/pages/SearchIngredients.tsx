import Select from 'react-select';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { useLocation } from 'react-router-dom';

const GET_INGREDIENTS_URL: string = 'http://localhost:8080/getIngradientList';

const POST_BREAKFAST_INGRADIENTS: string = 'http://localhost:8080/postIngredientToMealPeriod/breakfast';
const POST_DINNER_INGRADIENTS: string = 'http://localhost:8080/postIngredientToMealPeriod/dinner';
const POST_LUNCH_INGRADIENTS: string = 'http://localhost:8080/postIngredientToMealPeriod/lunch';
const POST_SNACKS_INGRADIENTS: string = 'http://localhost:8080/postIngredientToMealPeriod/snacks';

type Ingredient = {
  value: string;
  label: string;
};

type IngredientWithQuantity = Ingredient & {
  mealIngredientQuantityInGrams: number;
};

const ingredientList: Ingredient[] = [];

function SearchIngredientPage() {
  const location = useLocation();

  const [selectedOption, setSelectedOption] = useState<Ingredient | null>(null);
  const [ingredientOptions, setIngredientOptions] = useState(ingredientList);
  const [quantity, setQuantity] = useState<number | ''>('');
  const [warning, setWarning] = useState<string | null>(null);

  const getALLIngredients = () => {
    return axios.get(GET_INGREDIENTS_URL);
  };

  const handleSubmitFromSearch = async () => {
    if (!selectedOption || !quantity) {
      setWarning('Wybierz składnik i wpisz ilość w gramach.');
      return;
    }
    setWarning(null); 

    const postData: IngredientWithQuantity = {
      ...selectedOption,
      mealIngredientQuantityInGrams: Number(quantity),
    };

    let postUrl: string = POST_DINNER_INGRADIENTS;
    if (location.pathname === '/searchIngredient/breakfast') postUrl = POST_BREAKFAST_INGRADIENTS;
    if (location.pathname === '/searchIngredient/lunch') postUrl = POST_LUNCH_INGRADIENTS;
    if (location.pathname === '/searchIngredient/dinner') postUrl = POST_DINNER_INGRADIENTS;
    if (location.pathname === '/searchIngredient/snacks') postUrl = POST_SNACKS_INGRADIENTS;

    try {
      const res = await fetch(postUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(postData),
      });
      const data = await res.json();
      console.log('Przesłano składnik:', postData);
    } catch (error) {
      console.log('Wystąpił błąd podczas przesyłania składnika.');
    }
  };

  useEffect(() => {
    getALLIngredients().then((response) => {
      setIngredientOptions(response.data);
    });
  }, []);

  return (
    <>
      <div
        style={{
          textAlign: 'center',
          height: '400px',
          width: '400px',
          background: '#7ad557',
          marginLeft: 'auto',
          marginRight: 'auto',
          paddingTop: '80px',
          borderRadius: '12px',
          boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)',
          borderStyle: 'solid',
          borderColor: 'green',
        }}
      >
        <span style={{ fontWeight: 'bold' }}>Wybierz składnik i wpisz ilość (w gramach)</span>
        <div style={{ maxWidth: '200px', marginTop: '20px', marginLeft: 'auto', marginRight: 'auto' }}>
          <Select
            options={ingredientOptions}
            onChange={(option) => setSelectedOption(option as Ingredient)}
            placeholder="Wyszukaj..."
          />
        </div>
        <input
          type="number"
          placeholder="Wpisz ilość w gramach"
          value={quantity}
          onChange={(e) => setQuantity(Number(e.target.value))}
          style={{
            display: 'block',
            margin: '20px auto',
            padding: '10px',
            borderRadius: '5px',
            border: '1px solid #ccc',
          }}
        />
        {warning && <p style={{ color: 'red' }}>{warning}</p>}
        <button onClick={handleSubmitFromSearch} style={{ background: '#ccc2c2' }}>
          Zatwierdź
        </button>
        <p>
          Typ posiłku: {JSON.stringify(location.pathname).replace('"', '').replace('"', '').replace('/searchIngredient/', '')}
        </p>
        <Link to="/">
          <button style={{ background: '#ccc2c2' }}>Powrót do strony głównej</button>
        </Link>
      </div>
    </>
  );
}

export default SearchIngredientPage;
