if(localStorage.getItem('token')) {
    isAuthorized()
    .then(response => {
    
      if(response.response === 'pass'){
        
            console.log("zalogowany")
    


      }else{
    
        console.log("niezalogowany")

      }
    
    }).catch(error =>{
        console.log(error);
    })
    }else{
    
        console.log("niezalogowany")

    
    }