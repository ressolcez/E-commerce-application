  
const getModalAddCart = (productId) => { 
  if(localStorage.getItem('token')) {
    isAuthorized()
    .then(response => {

      if(response.response === 'pass'){
    
          getCartId().then(responseData =>{

                  addCartProduct(responseData.id, productId).then(response =>{
                    console.log(response);
                    
                  }).catch(error => {
                    console.log(error);
                  })

                  var x = document.getElementById("snackbar");
                  x.className = "show";
                  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);

            } 
          ).catch(error =>{
            console.log(error);
          })

        }else{

          console.log("Brak dostępu")

        }
    })
    
    }else{
      console.log("Brak dostępu")
    }

  }

 
  const getCartId = async () => {

        try {
          const response = await fetch('http://localhost:8080/api/v1/cart/getCart/' + localStorage.getItem('user_id'))
          return response.json();
      } catch(e) {
          return Promise.reject(e);
      }
  
  }

  const addCartProduct = async (cartId,productId) => {

        try {

          const response = await fetch('http://localhost:8080/api/v1/cartProduct/+', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({cartId: cartId, productId: productId, quantity: 1})
          })

          return response;
      } catch(e) {
          return Promise.reject(e);
      }

  }