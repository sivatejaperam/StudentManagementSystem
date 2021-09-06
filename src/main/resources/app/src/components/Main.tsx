import axios from 'axios';
import React from 'react';
import Students from './Students';
import Profile from './Profile';


class Main extends React.Component<any, any>{

   

    render() {
        return (
            <div>
                <Students/>
                <Profile/>
            </div>

        )
    }
}


export default Main;