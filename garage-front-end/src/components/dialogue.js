import { Typography ,Button, Container } from "@mui/material"
import ClientAddedLogo from "../assets/logoAddedClient.png"
export default function ({message , messageButton , handleClose}){
return(
    <Container sx={{display:"flex" , flexDirection:"column",justifyContent:"center" , alignItems:"center" , gap:3}}>
      <img src={ClientAddedLogo} alt="Logo"  style={{ width: "100px", height: "100px" }} />
     <Typography variant="h5" sx={{fontWeight:"bold"}}>
        {message}
     </Typography>
     <Typography variant="body2" sx={{fontWeight:"light" ,textAlign:"center" , width: "70%"}}>
        Your information has been securely Saved and is Ready for invoices
     </Typography>
     <Button  variant="contained"  onClick={handleClose} sx={{ padding : "2rem , 2rem"}} >
      {messageButton}
     </Button>
     </Container>
  
)
}