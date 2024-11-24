import { ingredient } from '../custom_types/ingredient';
import Button from './Button';

interface IngredientTableProps {
  mealType: 'breakfast' | 'lunch' | 'dinner' | 'snacks';
  ingredients: ingredient[];
  deleteIngredient: (
    mealType: 'breakfast' | 'lunch' | 'dinner' | 'snacks',
    id: number | undefined
  ) => void;
}

const formatNumber = (value: number) => Number(value || 0).toFixed(2);

const IngredientTable = ({
  mealType,
  ingredients,
  deleteIngredient,
}: IngredientTableProps) => (
  <table
    style={{ width: '100%', marginTop: '10px', borderCollapse: 'collapse' }}
  >
    <thead>
      <tr
        style={{
          borderBottom: '1px solid black',
          fontWeight: 'bold',
          textAlign: 'left',
        }}
      >
        <th style={{ padding: '6px' }}>Składnik</th>
        <th style={{ padding: '6px' }}>Białko</th>
        <th style={{ padding: '6px' }}>Sód</th>
        <th style={{ padding: '6px' }}>Węglowodany</th>
        <th style={{ padding: '6px' }}>Tłuszcz</th>
        <th style={{ padding: '6px' }}>Cholesterol</th>
        <th style={{ padding: '6px' }}>Cukier</th>
        <th style={{ padding: '6px' }}>Błonnik</th>
        <th style={{ padding: '6px' }}>Kalorie</th>
        <th style={{ padding: '6px' }}></th>
      </tr>
    </thead>
    <tbody>
      {ingredients.map((ingredient, index) => (
        <tr key={index} style={{ borderBottom: '1px solid #e0e0e0' }}>
          <td style={{ padding: '6px' }}>{ingredient.ingredient}</td>
          <td style={{ padding: '6px' }}>
            {formatNumber(ingredient.protein)}g
          </td>
          <td style={{ padding: '6px' }}>
            {formatNumber(ingredient.sodium)}g
          </td>
          <td style={{ padding: '6px' }}>
            {formatNumber(ingredient.carbohydrates)}g
          </td>
          <td style={{ padding: '6px' }}>
            {formatNumber(ingredient.fats)}g
          </td>
          <td style={{ padding: '6px' }}>
            {formatNumber(ingredient.cholesterol)}g
          </td>
          <td style={{ padding: '6px' }}>
            {formatNumber(ingredient.sugar)}g
          </td>
          <td style={{ padding: '6px' }}>
            {formatNumber(ingredient.fiber)}g
          </td>
          <td style={{ padding: '6px' }}>
            {formatNumber(ingredient.calories)}g
          </td>
          <td>
            <Button
              text="Usuń"
              onClick={() =>
                deleteIngredient(mealType, ingredient.mealPeriodIngredientId)
              }
              customStyles={{ backgroundColor: '#e74c3c', padding: '5px 10px' }}
            />
          </td>
        </tr>
      ))}
    </tbody>
  </table>
);

export default IngredientTable;
