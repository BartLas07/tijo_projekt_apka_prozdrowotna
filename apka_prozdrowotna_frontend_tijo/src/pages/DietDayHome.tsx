import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
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

  // **Oddzielne zmienne stanu dla bmi, caloriePool i recommendedHydration**
  const [bmi, setBmi] = useState<number | null>(null);
  const [caloriePool, setCaloriePool] = useState<number | null>(null);
  const [recommendedHydration, setRecommendedHydration] = useState<number | null>(null);

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

  const mealPeriodListToFetch: MealPeriod[] = ['breakfast', 'lunch', 'dinner', 'snacks'];

  useEffect(() => {


    const fetchBmi = async () => {
      try {
        const response = await axios.get('http://localhost:8080/getBmi');
        setBmi(response.data.bmi);
      } catch (error) {
        console.error('Error fetching BMI:', error);
      }
    };

    const fetchCaloriePool = async () => {
      try {
        const response = await axios.get('http://localhost:8080/getCaloriePool');
        setCaloriePool(response.data.caloriePool);
      } catch (error) {
        console.error('Error fetching calorie pool:', error);
      }
    };

    const fetchRecommendedHydration = async () => {
      try {
        const response = await axios.get('http://localhost:8080/getRecommendedHydration');
        setRecommendedHydration(response.data.recommendedHydration);
      } catch (error) {
        console.error('Error fetching recommended hydration:', error);
      }
    };

    fetchBmi();
    fetchCaloriePool();
    fetchRecommendedHydration();


    mealPeriodListToFetch.forEach((mealPeriod) => {
      axios
        .get(`http://localhost:8080/getIngredients/${mealPeriod}`)
        .then((response) => {
          response.data.forEach((ing: ingredient) => addIngredient(mealPeriod, ing));
        })
        .catch((error) => console.error(`Error fetching ingredients for ${mealPeriod}:`, error));
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

  useEffect(() => {
    const total = computeTotalSum([breakfastSum, lunchSum, dinnerSum, snacksSum]);
    setTotalSum(roundSum(total));
  }, [breakfastSum, lunchSum, dinnerSum, snacksSum]);

  const computeSum = (ingredients: ingredient[]) => {
    if (ingredients.length === 0) {
      return { ...initialSum };
    } else {
      return ingredients.reduce(
        (acc, ing) => ({
          protein: acc.protein + ing.protein,
          sodium: acc.sodium + ing.sodium,
          carbohydrates: acc.carbohydrates + ing.carbohydrates,
          fats: acc.fats + ing.fats,
          cholesterol: acc.cholesterol + ing.cholesterol,
          sugar: acc.sugar + ing.sugar,
          fiber: acc.fiber + ing.fiber,
          calories: acc.calories + ing.calories,
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
      setSelectedMealPeriod((prev) => ({
        ...prev,
        [mealType]: prev[mealType].filter(
          (i) => i.mealPeriodIngredientId !== mealPeriodIngredientId
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

  const mealSums = [
    { name: 'śniadania', sumData: breakfastSum },
    { name: 'obiadu', sumData: dinnerSum },
    { name: 'lunchu', sumData: lunchSum },
    { name: 'przekąsek', sumData: snacksSum },
  ];

  const mealTypes: MealPeriod[] = ['breakfast', 'dinner', 'lunch', 'snacks'];

  const { totalMacros: totalSumMacros, data: totalChartData } = getMacroChartData(totalSum);

  return (
    <div style={containerStyle}>
      <h2 style={headerStyle}>Mój dziennik żywieniowy</h2>
      <div style={innerContainerStyle}>
        <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '20px' }}>
          <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-start', textAlign: 'left' }}>
          <div style={{ display: 'flex', marginBottom: '16px' }}>
          <label style={{ width: '400px' }}>BMI:</label>
          <span>{bmi !== null && !isNaN(bmi) ?   bmi:'brak'}</span>
        </div>
        <div style={{ display: 'flex', marginBottom: '16px' }}>
          <label style={{ width: '400px' }}>Zalecana pula kalorii (w kcal):</label>
          <span>{caloriePool !== null && !isNaN(caloriePool) ? caloriePool:'brak' }</span>
        </div>
        <div style={{ display: 'flex', marginBottom: '16px' }}>
          <label style={{ width: '400px' }}>Rekomendowana ilość nawodnienia (w litrach):</label>
          <span>{recommendedHydration !== null && !isNaN(recommendedHydration) ? recommendedHydration:'brak'}</span>
        </div>

          </div>

          <div style={{ display: 'flex', gap: '16px' }}>
            <Link to="/bmiForm">
              <button>Oblicz BMI</button>
            </Link>
            <Link to="/caloriePoolForm">
              <button>Oblicz zalecaną pulę kalorii</button>
            </Link>
            <Link to="/recommendedHydrationForm">
              <button>Oblicz rekomendowane nawodnienie</button>
            </Link>
          </div>
        </div>

        <div style={outerFlexContainerStyle}>
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
