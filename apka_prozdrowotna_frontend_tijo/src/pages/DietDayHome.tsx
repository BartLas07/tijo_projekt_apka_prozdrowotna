import { useState, useEffect } from 'react';
import axios from 'axios';
import MealSection from '../components/MealSection';
import SumTable from '../components/SumTable';
import {
  containerStyle,
  headerStyle,
  innerContainerStyle,
  sumTableWrapperStyle,
  rightSideRectangleStyle,
} from '../styles/styles';
import {
  outerFlexContainerStyle,
  leftSideMealSectionsStyle,
  mealSectionItemStyle,
  mealSumItemStyle,
  pieChartContainerStyle,
  pieChartCenterStyle,
  pieChartContainerStyle2,
} from '../styles/DietDayHomeStyles';
import { ingredient } from '../custom_types/ingredient';
import { Pie } from 'react-chartjs-2';
import { Chart, ArcElement, Legend } from 'chart.js';

Chart.register(ArcElement, Legend);

type IngredientsForMealPeriod = {
  breakfast: ingredient[];
  lunch: ingredient[];
  dinner: ingredient[];
  snacks: ingredient[];
};

type MealPeriod = keyof IngredientsForMealPeriod;

function DietDayHome() {
  const [selectedMealPeriod, setSelectedMealPeriod] = useState<IngredientsForMealPeriod>({
    breakfast: [],
    lunch: [],
    dinner: [],
    snacks: [],
  });

  const addIngredient = (mealType: MealPeriod, ingredient: ingredient) => {
    setSelectedMealPeriod((prev) => ({
      ...prev,
      [mealType]: [...prev[mealType], ingredient],
    }));
  };

  const initialSum = {
    protein: 0,
    sodium: 0,
    carbohydrates: 0,
    fats: 0,
    cholesterol: 0,
    sugar: 0,
    fiber: 0,
    calories: 0,
  };

  const [breakfastSum, setBreakfastSum] = useState(initialSum);
  const [lunchSum, setLunchSum] = useState(initialSum);
  const [dinnerSum, setDinnerSum] = useState(initialSum);
  const [snacksSum, setSnacksSum] = useState(initialSum);
  const [totalSum, setTotalSum] = useState(initialSum);

  const fetchIngredients = async (url: string) =>
    await axios.get(url).then((response) => response.data);

  const mealPeriodListToFetch: MealPeriod[] = ['breakfast', 'lunch', 'dinner', 'snacks'];

  useEffect(() => {
    mealPeriodListToFetch.forEach((mealPeriod) => {
      fetchIngredients(`http://localhost:8080/getIngredients/${mealPeriod}`).then(
        (ingredients: ingredient[]) => {
          ingredients.forEach((ing) => addIngredient(mealPeriod, ing));
        }
      );
    });
  }, []);

  useEffect(() => {
    setBreakfastSum(roundSum(computeSum(selectedMealPeriod.breakfast)));
  }, [selectedMealPeriod.breakfast]);

  useEffect(() => {
    setLunchSum(roundSum(computeSum(selectedMealPeriod.lunch)));
  }, [selectedMealPeriod.lunch]);

  useEffect(() => {
    setDinnerSum(roundSum(computeSum(selectedMealPeriod.dinner)));
  }, [selectedMealPeriod.dinner]);

  useEffect(() => {
    setSnacksSum(roundSum(computeSum(selectedMealPeriod.snacks)));
  }, [selectedMealPeriod.snacks]);

  // Obliczanie sumy zbiorczej
  useEffect(() => {
    const total = computeTotalSum([breakfastSum, lunchSum, dinnerSum, snacksSum]);
    setTotalSum(roundSum(total));
  }, [breakfastSum, lunchSum, dinnerSum, snacksSum]);

  const computeSum = (ingredients: ingredient[]) => {
    if (ingredients.length === 0) {
      return { ...initialSum };
    } else {
      return ingredients.reduce(
        (acc, ingredient) => ({
          protein: acc.protein + ingredient.protein,
          sodium: acc.sodium + ingredient.sodium,
          carbohydrates: acc.carbohydrates + ingredient.carbohydrates,
          fats: acc.fats + ingredient.fats,
          cholesterol: acc.cholesterol + ingredient.cholesterol,
          sugar: acc.sugar + ingredient.sugar,
          fiber: acc.fiber + ingredient.fiber,
          calories: acc.calories + ingredient.calories,
        }),
        { ...initialSum }
      );
    }
  };

  const roundSum = (sumData: typeof initialSum) => {
    const roundedSum = { ...sumData };
    for (const key in roundedSum) {
      // @ts-ignore
      roundedSum[key] = parseFloat(roundedSum[key].toFixed(2));
    }
    return roundedSum;
  };

  const computeTotalSum = (sums: typeof initialSum[]) => {
    return sums.reduce(
      (acc, sum) => ({
        protein: acc.protein + sum.protein,
        sodium: acc.sodium + sum.sodium,
        carbohydrates: acc.carbohydrates + sum.carbohydrates,
        fats: acc.fats + sum.fats,
        cholesterol: acc.cholesterol + sum.cholesterol,
        sugar: acc.sugar + sum.sugar,
        fiber: acc.fiber + sum.fiber,
        calories: acc.calories + sum.calories,
      }),
      { ...initialSum }
    );
  };

  const deleteIngredient = async (
    mealType: MealPeriod,
    mealPeriodIngredientId: number | undefined
  ) => {
    if (mealPeriodIngredientId === undefined) return;

    const deleteUrl = `http://localhost:8080/deleteIngredient/${mealType}/${mealPeriodIngredientId}`;

    try {
      const response = await fetch(deleteUrl, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' },
      });
      if (!response.ok) throw new Error(`Server error: ${response.status}`);

      // Aktualizujemy stan selectedMealPeriod, usuwając dany składnik
      setSelectedMealPeriod((prev) => ({
        ...prev,
        [mealType]: prev[mealType].filter(
          (ingredient) => ingredient.mealPeriodIngredientId !== mealPeriodIngredientId
        ),
      }));
    } catch (error) {
      console.error(`Error deleting ingredient for ${mealType}:`, error);
    }
  };

  const ingredientsMap: Record<MealPeriod, ingredient[]> = {
    breakfast: selectedMealPeriod.breakfast,
    lunch: selectedMealPeriod.lunch,
    dinner: selectedMealPeriod.dinner,
    snacks: selectedMealPeriod.snacks,
  };

  // Funkcja do generowania danych do wykresu
  const getMacroChartData = (sumData: typeof initialSum) => {
    const totalMacros = sumData.protein + sumData.carbohydrates + sumData.fats;

    const proteinPercentage = totalMacros ? (sumData.protein / totalMacros) * 100 : 0;
    const carbsPercentage = totalMacros ? (sumData.carbohydrates / totalMacros) * 100 : 0;
    const fatsPercentage = totalMacros ? (sumData.fats / totalMacros) * 100 : 0;

    return {
      totalMacros,
      data: {
        labels: ['Białka', 'Węglowodany', 'Tłuszcze'],
        datasets: [
          {
            data: [proteinPercentage, carbsPercentage, fatsPercentage],
            backgroundColor: ['#A3C1DA', '#F7A1A3', '#F9E79F'],
            hoverBackgroundColor: ['#A3C1DA', '#F7A1A3', '#F9E79F'],
          },
        ],
      },
    };
  };

  const chartOptions = {
    plugins: {
      legend: {
        display: true,
        position: 'bottom' as const,
      },
    },
    maintainAspectRatio: false,
  };

  // Lista posiłków do wyświetlenia
  const mealSums = [
    { name: 'śniadania', sumData: breakfastSum },
    { name: 'obiadu', sumData: dinnerSum },
    { name: 'lunchu', sumData: lunchSum },
    { name: 'przekąsek', sumData: snacksSum },
  ];

  const mealTypes: MealPeriod[] = ['breakfast', 'dinner', 'lunch', 'snacks'];

  // Przygotowanie danych do wykresu zbiorczego
  const { totalMacros: totalSumMacros, data: totalChartData } = getMacroChartData(totalSum);

  return (
    <div style={containerStyle}>
      <h2 style={headerStyle}>Mój dziennik żywieniowy</h2>
      <div style={innerContainerStyle}>
        {/* MealSections z większym prostokątem po prawej stronie */}
        <div style={outerFlexContainerStyle}>
          {/* Lewa strona: MealSections */}
          <div style={leftSideMealSectionsStyle}>
            {mealTypes.map((mealType, index) => (
              <div key={index} style={mealSectionItemStyle}>
                <MealSection
                  mealType={mealType}
                  ingredients={ingredientsMap[mealType]}
                  deleteIngredient={deleteIngredient}
                />
              </div>
            ))}
          </div>
          {/* Prawa strona: Większy prostokąt */}
          <div style={rightSideRectangleStyle}>
            <SumTable mealName="wszystkich posiłków" sumData={totalSum} />
            {totalSumMacros > 0 ? (
              <div style={pieChartContainerStyle}>
                <Pie data={totalChartData} options={chartOptions} />
              </div>
            ) : (
              <p>Brak wykresu dla sumy zera elementów</p>
            )}
          </div>
        </div>

        {/* Sumy pod resztą zawartości */}
        <div
          style={{
            display: 'flex',
            justifyContent: 'space-between',
            flexWrap: 'wrap',
            marginTop: '20px',
          }}
        >
          {mealSums.map((meal, index) => {
            const { totalMacros, data } = getMacroChartData(meal.sumData);
            return (
              <div key={index} style={mealSumItemStyle}>
                <div style={sumTableWrapperStyle}>
                  <SumTable mealName={meal.name} sumData={meal.sumData} />
                  <div style={pieChartCenterStyle}>
                    {totalMacros > 0 ? (
                      <div style={pieChartContainerStyle2}>
                        <Pie data={data} options={chartOptions} />
                      </div>
                    ) : (
                      <p>Brak wykresu dla sumy zera elementów</p>
                    )}
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}

export default DietDayHome;
