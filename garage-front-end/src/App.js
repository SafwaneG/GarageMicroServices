import React from "react";
import Layout from "./components/layout";
import Client from "./pages/client/client";
import Vehicle from "./pages/vehicle/vehicle";
import Workshop from "./pages/workshop/workshop";
import {
  Route,
  createBrowserRouter,
  createRoutesFromElements,
  RouterProvider,
} from "react-router-dom";

const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/" element={<Layout />}>
        <Route path="client" element={<Client />} />
        <Route path="vehicle" element={<Vehicle />} />
        <Route path="workshop" element={<Workshop />} />
      </Route>
    </>
  )
);

export default function App() {
  return (
    <>
      <RouterProvider router={router} />
    </>
  );
}
