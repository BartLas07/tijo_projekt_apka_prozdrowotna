import './App.css'
import './index.css'
import{HashRouter as Router,Routes,Route} from 'react-router-dom'
import DietDayHome from './pages/DietDayHome.tsx'
import SearchIngredientPage from './pages/SearchIngredients.tsx'

function App() {

 
  
return (

<Router>
  <Routes>
  
    
    <Route path='/' element={<DietDayHome/>}/>
    <Route path='/searchIngredient/breakfast' element={<SearchIngredientPage/>}/>
    <Route path='/searchIngredient/lunch' element={<SearchIngredientPage/>}/>
    <Route path='/searchIngredient/dinner' element={<SearchIngredientPage/>}/>
    <Route path='/searchIngredient/snacks' element={<SearchIngredientPage/>}/>
  </Routes>
</Router>
)
  
}

export default App
