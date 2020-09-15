import React from "react";
import { Link } from "react-router-dom";
import "./Navigation.css";

function Navigation() {
    return (
        <div className="nav">
            <Link to="/about">Home</Link>
            <Link to="/MainComp">MainComp</Link>
        </div>
        );
}

export default Navigation;