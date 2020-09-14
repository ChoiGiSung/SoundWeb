import React from "react";
import { Link } from "react-router-dom";
import "./Navigation.css";

function Navigation() {
    return (
        <div className="nav">
            <Link to="/about">Home</Link>
            <Link to="/MainComponent">About</Link>
            <Link to="/main">About2</Link>
        </div>
        );
}

export default Navigation;