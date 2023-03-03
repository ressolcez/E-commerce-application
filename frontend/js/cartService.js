let price1 = 0;


if(localStorage.getItem('token')) {
    isAuthorized()
    .then(response => {
    
      if(response.response === 'pass'){
        
            getCartItems().then(data =>{

              const wrapper = document.querySelector('.cart__item__wrapper');

              data.forEach( product => {

                wrapper.innerHTML += `
                <div class = "cart__item">
                <div class = "cart__item__image">
                    <img src="${product.product.image}" style = "object-fit: cover; height:200px;"/>
                </div>

                <div class = "cart__item__desc"> 
                <div class = "cart__item__name"> 
                    <span>${product.product.name}</span>
                </div>

                <div class = "cart__item__price"> 
                        <span>PLN ${product.product.price}</span>
                    </div>
                </div>

                <div class= "cart__item__counter">
                    <div class = "inside">
                    <span onclick = "addPlus(${product.cart.id},${product.product.id}, ${product.cartProduct.id}, '+')" class = "btn__plus">+</span>
                    <span class = "counter__number${product.product.id}">${product.cartProduct.quantity}</span>
                    <span onclick = "addPlus(${product.cart.id},${product.product.id}, ${product.cartProduct.id}, '-')" class = "btn__minus">-</span>
                    </div>

                </div>
            </div>
                `

          
                getFinalPrice(product.cart.id)
                .then(cart => {
                    price1 = cart.totalPrice;
                    const cartProducts = document.querySelector('#final__price')
                    cartProducts.innerHTML =  Math.round(cart.totalPrice * 100) / 100 + ' zł'
             
                })
            });

          

            }).catch(error =>{
              console.log("Blad w zwrcaniu danych w koszyku",error)
            })


      }else{
  
        history.back()
      }
    
    }).catch(error =>{
        console.log(error);
    })
    }else{
        history.back()
    }



    const getQuantity = (cart_product_id) => {
      return new Promise( (resolve, reject) => {
          fetch('http://localhost:8080/api/v1/cartProduct/' + cart_product_id)
              .then( async result => {
                  const data = await result.json();
                  resolve(data);
              } )
              .catch( err => {
                  reject(err);
              } );
      } );
  }

  const getFinalPrice = (cart_id) => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/v1/cart/' + cart_id)
            .then( async result => {

                const data = await result.json();
                resolve(data);


            } )
            .catch( err => {
                reject(err);
            } );
    } );
}

const deleteCartProduct = (cart_product_id) => {
  return new Promise( (resolve, reject) => {
      fetch('http://localhost:8080/api/v1/cartProduct/' + cart_product_id, {
          method: 'DELETE'
      })
          .then( async result => {

          const data = await result.json();
          resolve(data);


      } )
      .catch( err => {
          reject(err);
      } );
} );
}


    const addPlus = async (cartId,product_id,cart_product_id,sign) => {

          await fetch('http://localhost:8080/api/v1/cartProduct/' + sign, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({cartId: cartId, productId: product_id, quantity: 1})
        }).then(
                     
        ).then( json => {

           getQuantity(cart_product_id)
           .then(cart_product => {
              const cartProducts = document.querySelector('.counter__number'+ product_id)
              console.log(cartProducts)
              if(cart_product.quantity === 0) {
                deleteCartProduct(cart_product_id)
                .then( deleted => {
                    console.log("USUNIETO");
                })
                document.location.reload(true)
            } else {
            cartProducts.innerHTML = cart_product.quantity
            }
           })

           getFinalPrice(cartId)
           .then(cart => {
               const cartProducts = document.querySelector('#final__price')
               cartProducts.innerHTML =  Math.round(cart.totalPrice * 100) / 100 + ' zł'
        
           })

        }).catch(error => {
            console.log(error);
        });
}



  const getCartItems = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/v1/productInCart/' + localStorage.getItem('user_id'))
      return response.json();
    } catch(e) {
        return Promise.reject(e);
    }
  }


  const addAddressData = () => {


    const in_street = document.getElementById("in_street").value;
    const in_city = document.getElementById("in_city").value;
    const postal_code = document.getElementById("postal_code").value;

    console.log(in_street,in_city,postal_code)
    
     document.getElementById("street").innerHTML = in_street;
     document.getElementById("city").innerHTML = in_city;
     document.getElementById("code").innerHTML = postal_code;


  }


  const addCartData = () => {


    const in_owner = document.getElementById("in_owner").value;
    const in_number = document.getElementById("in_number").value;
    const in_endDate = document.getElementById("in_endDate").value;

    console.log(in_endDate)


     document.getElementById("owner_name").innerHTML = in_owner;
     document.getElementById("cart_number").innerHTML =  in_number;
     document.getElementById("date_sp").innerHTML = in_endDate;


  }


  const addOrder = async () => {

    const street = document.getElementById("street").innerText;
    const city = document.getElementById("city").innerText;
    const code = document.getElementById("code").innerText;

    const in_owner = document.getElementById("in_owner").value;
    const in_number = document.getElementById("in_number").value;
    const in_endDate = document.getElementById("in_endDate").value;


    const address = street + " " + city + " " + code;

    console.log(street === "--")

    if(street === "--" || city === '--' || code === '--' || in_owner === "--" || in_number === '--' || in_endDate === '--') {
        console.log("uzupelnij pola")
        return;
    }


    const todayDate = new Date().toISOString().slice(0, 10);

    console.log(address, todayDate, localStorage.getItem('user_id'), )

    await fetch('http://localhost:8080/api/v1/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            status: 'KOMPLETOWANIE',
            date: todayDate,
            totalPrice: price1,
            address: address,
            usersId: localStorage.getItem('user_id')
        })
    }).then(
       
    ).then( json => {
        getOrderId()
        .then(order_id => {
          getCartItems()
            .then(cart => {
                console.log(cart[0].cart)
                    for(let i = 0; i<cart.length; i++) {
                        console.log(cart)
                    fetch('http://localhost:8080/api/v1/orderProduct', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            quantity: cart[i].cartProduct.quantity,
                            price: cart[i].product.price,
                            ordersId: order_id[0].id,
                            productId: cart[i].product.id
                        })
                    })
                }
            })
        })
    }).catch(error => {
        console.log(error);
    });
    
}


const getOrderId = () => {
  return new Promise( (resolve, reject) => {
      fetch('http://localhost:8080/api/v1/orders')
          .then( async result => {
              const data = await result.json();
              resolve(data);
          } )
          .catch( err => {
              reject(err);
          } );
  } );
}



 


