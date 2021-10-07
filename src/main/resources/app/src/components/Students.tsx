import { Table } from '@mui/material';
import axios from 'axios';
import React from 'react';


class Students extends React.Component<any, any>{

    constructor(props:any){
        super(props);
        this.state={
            students:[]
        }
    }

    componentDidMount() {
        axios.get('/api/student/10/0')
            .then((response:any) => {
                console.log("resonse ", response);
                
                this.setState({
                    students: response.data.content
                })
            });
    }

    render() {
        // const studentsData = this.state.students.map((student:any) => {
        //     return (
        //         <tr>
        //         <td>{student.id}</td>
        //         <td>{student.name}</td>
        //         <td>{student.branch.name}</td>
        //         <td>{student.subjects.join(",")}</td>
        //         </tr>       
        //     )
        // })
        return (
            <div>
                <h1>Student List</h1>
                <Table>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Subjects</th>
                            <th>Branch</th>
                        </tr>
                    </thead>
                    <tbody>
                       {/* {studentsData} */}
                    </tbody>
                </Table>
            </div>

        )
    }
}


export default Students;