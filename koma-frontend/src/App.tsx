import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { Button, Intent, Spinner } from "@blueprintjs/core";

class App extends Component {
  componentDidMount(): void {
    fetch('/api/recipes').then(response => response.json()).then(value => console.log(value))
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <Button intent={Intent.SUCCESS}>Hallo</Button>
          <Spinner />
          <img src={logo} className="App-logo" alt="logo"/>
          <p>
            xx Edit <code>src/App.tsx</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
      </div>
    );
  }
}

export default App;
