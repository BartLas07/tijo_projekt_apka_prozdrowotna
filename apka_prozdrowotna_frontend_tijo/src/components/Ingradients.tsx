import { Ingrad } from "../custom_types/ingrad";
export const Ingrads = (props: { Ingrad: Ingrad }) => (
  <div style={{ display: 'flex', gap: '10px', fontSize: '14px' }}> 
    <h2>{props.Ingrad.ingredient}</h2>
    <h3>{props.Ingrad.sodium}</h3>
    <h3>{props.Ingrad.carbohydrates}</h3>
  </div>
);