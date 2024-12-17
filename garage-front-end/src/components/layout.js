import React from "react";
import PropTypes from "prop-types";
import Box from "@mui/material/Box";
import Divider from "@mui/material/Divider";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import GridViewIcon from "@mui/icons-material/GridView";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import Toolbar from "@mui/material/Toolbar";
import { Outlet, useNavigate } from "react-router-dom";
import DirectionsCarIcon from "@mui/icons-material/DirectionsCar";
import PeopleOutlineIcon from "@mui/icons-material/PeopleOutline";
import HomeRepairServiceIcon from "@mui/icons-material/HomeRepairService";
import Logo from "../assets/logo.png";

const drawerWidth = 60;

const ResponsiveDrawer = ({ window }) => {
  const navigate = useNavigate();
  const [mobileOpen, setMobileOpen] = React.useState(false);

  const routes = [
    { path: "/client", icon: PeopleOutlineIcon },
    { path: "/vehicle", icon: DirectionsCarIcon },
    { path: "/workshop", icon: HomeRepairServiceIcon },
  ];

  const drawerStyles = {
    boxSizing: "border-box",
    width: drawerWidth,
    backgroundColor: "black",
    color: "white",
  };

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

  const container =
    window !== undefined ? () => window().document.body : undefined;

  const drawer = (
    <div>
      <Box
        sx={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          padding: "16px",
        }}
      >
        <img src={Logo} alt="Logo" style={{ width: "50px", height: "50px" }} />
      </Box>
      <Divider sx={{ backgroundColor: "white" }} />
      <List>
        {routes.map(({ path, icon: Icon }, index) => (
          <ListItem key={index} disablePadding>
            <ListItemButton
              onClick={() => navigate(path)}
              sx={{
                minHeight: 48,
                justifyContent: "center",
                px: 2.5,
                display: "flex",
                alignItems: "center",
              }}
            >
              <ListItemIcon
                sx={{
                  color: "white",
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                }}
              >
                <Icon />
              </ListItemIcon>
            </ListItemButton>
          </ListItem>
        ))}
      </List>
    </div>
  );

  return (
    <Box sx={{ display: "flex" }}>
      <Box
        component="nav"
        sx={{ width: { sm: drawerWidth }, flexShrink: { sm: 0 } }}
        aria-label="mailbox folders"
      >
        <Drawer
          container={container}
          variant="temporary"
          open={mobileOpen}
          onClose={handleDrawerToggle}
          ModalProps={{ keepMounted: true }}
          sx={{
            display: { xs: "block", sm: "none" },
            "& .MuiDrawer-paper": drawerStyles,
          }}
        >
          {drawer}
        </Drawer>
        <Drawer
          variant="permanent"
          sx={{
            display: { xs: "none", sm: "block" },
            "& .MuiDrawer-paper": drawerStyles,
          }}
          open
        >
          {drawer}
        </Drawer>
      </Box>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          p: 3,
          width: { sm: `calc(100% - ${drawerWidth}px)` },
        }}
      >
        <Outlet />
      </Box>
    </Box>
  );
};

ResponsiveDrawer.propTypes = {
  window: PropTypes.func,
};

export default ResponsiveDrawer;
