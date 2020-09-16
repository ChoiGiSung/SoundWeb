import React from 'react';
//import 'bootstrap/dist/css/bootstrap.min.css';
import { HashRouter, Route } from "react-router-dom";
import Navigation from "./Navigation";
//import Home from "./Home";
import Detail from "./Detail";
import MainComp from "./MainComp";


function App() {
  //return <div>ddM</div>;//<div clssName="App"/>
    return(
        <div clssName="App">
            <HashRouter>
              <Navigation />

                <Route path="/movie-detail" exact={true} component={Detail} />
                 <Route path="/MainComp"  component={MainComp} />
             </HashRouter>
        </div>
    );
}
//<Route path="/" exact={true} component={Home} />
export default App;
