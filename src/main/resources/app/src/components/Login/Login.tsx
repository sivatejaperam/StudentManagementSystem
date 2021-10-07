
import { Container, Grid, TextField, Button, makeStyles } from '@mui/material';
import axios, { AxiosRequestConfig } from 'axios';
import React from 'react';
import { withRouter } from 'react-router';



class Login extends React.Component<any, any>{

    constructor(props: any) {
        super(props);
        this.state = {
            username: '',
            password: '',

        }
    }

    handleSubmit = (event: any) => {
        event.preventDefault();
        const data = {
            'username': this.state.username,
            'password': this.state.password
        }
        
        axios.post('http://localhost:8080/api/auth/login', data)
            .then(resp => {
                this.props.history.push('/main')
            }).catch(error => {
                console.log("error", error);

            }).finally(() => {
                this.setState ({
                    username: '',
                    password: '',

                });
            })

    }

    onUsernameChange = (e: any) => {
        this.setState({
            username: e.target.value
        })
    }

    onPassWoredChange = (e: any) => {
        this.setState({
            password: e.target.value
        })
    }

    render() {
        return (
            <Container maxWidth="xs" style={{ marginTop: 20 }}>
                <form onSubmit={this.handleSubmit}>
                    <Grid container spacing={3}>
                        <Grid item xs={12}>
                            <Grid container spacing={2}>
                                <Grid item xs={12}>
                                    <TextField
                                        fullWidth
                                        label="Email"
                                        name="email"
                                        size="small"
                                        variant="outlined"
                                        value={this.state.username}
                                        onChange={this.onUsernameChange} />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        fullWidth
                                        label="Password"
                                        name="password"
                                        size="small"
                                        type="password"
                                        variant="outlined"
                                        value={this.state.password}
                                        onChange={this.onPassWoredChange}
                                    />
                                </Grid>
                            </Grid>
                        </Grid>
                        <Grid item xs={12}>
                            <Button color="primary" fullWidth type="submit" variant="contained">
                                Log in
                            </Button>
                        </Grid>
                    </Grid>
                </form>
            </Container>
        )
    }
}

export default withRouter(Login);