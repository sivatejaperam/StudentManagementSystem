import axios from 'axios';
import React from 'react';
import { Table } from 'react-bootstrap';
import Students from './Students';


class Main extends React.Component<any, any>{

   

    render() {
        return (
            <div>
                <Students/>
            </div>

        )
    }
}


export default Main;