
import { AppBar, Box, Button, IconButton, Toolbar, Typography } from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import { RouteComponentProps, useLocation, withRouter } from "react-router";
import Logout from "./Login/Logout";

const Header = () => {
    const route:string = useLocation().pathname;
    const isLoginPage = route && (route.toLowerCase() === '/login');
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        SMSystem
                    </Typography>
                    {isLoginPage ? null
                    :     <Logout />
                }
                </Toolbar>
            </AppBar>
        </Box>
    )
};


export default withRouter(Header);