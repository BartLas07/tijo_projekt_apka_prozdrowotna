export const PersonnInfo =(props: { name: string ; tel:number ; city:string;})=> (
    <>
  <h2>{props.name}</h2>
  <h3>{props.tel}</h3>
  <h3>{props.city}</h3>
    </>
  );