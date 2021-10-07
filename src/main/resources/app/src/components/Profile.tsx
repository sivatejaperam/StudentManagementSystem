import { Button, Table } from '@mui/material';
import axios from 'axios';
import React from 'react';

class Profile extends React.Component<any, any>{

    constructor(props: any) {
        super(props);
        this.state = {
            selectedfile: null,
            users: []
        }
        this.handleChange = this.handleChange.bind(this);
        this.uploadFile = this.uploadFile.bind(this);
    }

    componentDidMount() {
        axios.get('/api/user')
            .then((response) => {                
                this.setState({
                    users: response.data
                })
            });
    }

    handleChange = (event: any) => {
        this.setState(
            {
                selectedfile: event.target.files[0]
            });

    }

    uploadFile = () => {
        const formData = new FormData();
        const userId = this.state.users[0].id;
        formData.append("file", this.state.selectedfile)
        axios.post("/api/user/profile/image/"+ userId, formData)
            .then(resp =>{
                console.log("resp", resp);
                
            }).then(error =>{
                console.log("error", error);
                
            })
    }

    render() {
        const userColumns = this.state.users.map((user:any) => {
            return (
                <tr>
                <td>{user.id}</td>
                <td>{user.name}</td>
                <td>{user.email}</td>
                <td>{user.imageLocation}</td>
                </tr>       
            )
        });
        return (
            <div>
                <div>
                <h1>Users List</h1>
                <Table>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Profile</th>
                        </tr>
                    </thead>
                    <tbody>
                       {userColumns}
                    </tbody>
                </Table>
            </div>
                <input type="file" onChange={this.handleChange} />
                <Button value="upload"  onClick={this.uploadFile} />
            </div>
        )
    }
}

export default Profile;