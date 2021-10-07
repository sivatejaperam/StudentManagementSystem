
import './App.css';
import Header from '../Header';
import Main from '../Main';
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import Profile from '../Profile';
import Login from '../Login/Login';
import Register from '../Login/Register';
import NoMatch from '../NoMatch';

function App() {
  return (
    <Router>
      <Header/>
      <Switch>
        <Route exact path="/">
          <Redirect to="/login"  /> 
        </Route>
        <Route exact path="/login">
          <Login/>     
        </Route>
        <Route exact path="/register">
          <Register/>     
        </Route>
        <Route exact path="/profile" >
          <Profile/>
        </Route>
        <Route exact path="/main">
          <Main />
        </Route>
        <Route path="*">
            <NoMatch />
          </Route>
        </Switch>
    </Router>
  );
}

export default App;
