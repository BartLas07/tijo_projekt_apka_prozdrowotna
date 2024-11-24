import { Ingradient } from "../custom_types/ingredient";
export const AddedNewIngredient =(props: { Ingradient: Ingradient})=> (
    <>
  
    <h2>{props.Ingradient.nameIngradient}</h2>
     <h3>{props.Ingradient.sodium}</h3>
     <h3>{props.Ingradient.carbohydrates}</h3>
    <h3>{props.Ingradient.carbohydrates}</h3>
    </>
  );