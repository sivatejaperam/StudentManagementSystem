import { Button } from "@mui/material";
import React from "react";

class Logout extends React.Component<any, any> {

    handleLogout(){
        this.props.history.push("/login");

    }

    render(){
        return (
            <Button color="inherit" onClick={this.handleLogout}>Logout</Button>
        )
    }
    
}

export default Logout;