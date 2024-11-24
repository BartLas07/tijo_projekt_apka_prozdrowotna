import { Link } from 'react-router-dom';
import IngredientTable from './IngredientTable';
import Button from './Button';
import { mealContainerStyle } from '../styles/styles';
import { ingredient } from '../custom_types/ingredient';

interface MealSectionProps {
  mealType: 'breakfast' | 'lunch' | 'dinner' | 'snacks';
  ingredients: ingredient[];
  deleteIngredient: (mealType: 'breakfast' | 'lunch' | 'dinner' | 'snacks', id: number | undefined) => void;
}

const mealLabels = {
  breakfast: 'Śniadanie',
  lunch: 'Lunch',
  dinner: 'Obiad',
  snacks: 'Przekąska',
};

const MealSection = ({ mealType, ingredients, deleteIngredient }: MealSectionProps) => (
  <div style={mealContainerStyle}>
    <Link to={`/searchIngredient/${mealType}`}>
      <Button text={`➕ Dodaj ${mealLabels[mealType]}`} />
    </Link>
    <IngredientTable ingredients={ingredients} mealType={mealType} deleteIngredient={deleteIngredient} />
  </div>
);

export default MealSection;
