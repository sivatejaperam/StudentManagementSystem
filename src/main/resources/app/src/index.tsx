import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './components/App/App';
import AuthService from './AuthService';

AuthService.setUpAxiosInterceptors();

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

