import React from 'react'
import reactDom from 'react-dom';
import { Link } from "react-router-dom";
const CreateProjectButton = () => {
    return (
        <React.Fragment>
            <Link to ="/addProject" className="btn btn-lg btn-info">
                Create a Project
            </Link>
        </React.Fragment>
    );
};

export default CreateProjectButton;