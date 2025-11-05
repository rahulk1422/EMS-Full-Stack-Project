import { Route, Routes } from 'react-router-dom'
import './App.css'
import Footer from './components/Footer'
import Header from './components/Header'
import ListEmployee from './components/ListEmployee'
import AddEmployee from './components/AddEmployee'

function App() {


  return (
    <>
    <Header/>
    <Routes>
        <Route path='/' element={<ListEmployee/>}/>
        <Route path='employees' element={ <ListEmployee/>}/>
        <Route path='/add-employee'  element={<AddEmployee/>}/>
        <Route path='/edit-employee/:id' element={<AddEmployee/>}/>
    </Routes>
     <Footer/>
    </>
  )
}

export default App
