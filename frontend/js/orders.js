if(localStorage.getItem('token')) {
    isAuthorized()
    .then(response => {

      if(response.response === 'pass'){
    
        getOrdersUser().then(order =>{
            const wrapper = document.querySelector('.orders__wrapper');

            order.forEach( orders => {

                wrapper.innerHTML += `

                <div class = "single__order"> 
                <div class = "orders__wrapper__Title">
                    <div class = "orders__wrapper__title__cont__first" >
                        <span class = "order__state__desc_date"> Data zamówienia: </span> <span class = "order__desc__date">${orders.date} </span>
                    </div>
                    <div class = "orders__wrapper__title__cont">
                        <span class = "order__state__desc"> Identyfikator zamówienia: </span> <span class="order__desc">${orders.id} </span>
                    </div>
                    <div class = "orders__wrapper__title__cont">
                        <span class = "order__state__desc"> Status zamówienia: </span> <span class="order__desc">${orders.status} </span>
                    </div>
                    <div class = "orders__wrapper__title__cont">
                        <span class = "order__state__desc"> Całkowita cena zamówienia: </span> <span class="order__desc">${orders.totalPrice} PLN </span>
                    </div>
                </div>

                <div class = "single${orders.id}">
                    
                    
                   
                </div>
                    </div>
            `
            getProductInOrder(orders.id).then(or =>{
                or.forEach(order => {
                    const wrapper2 = document.querySelector('.single'+orders.id);
                    wrapper2.innerHTML += `
                    <div class ="single__product__wrapper">
                    <div class ="orders__wrapper__image">
                            <img style = "width: 70%" src = ${order.image}>
                        </div>
                
                        <div class ="orders__wrapper__name">
                            <div class = "orders__wraper__desc_cont">
                                <span class = "order__state__desc">Nazwa: </span>
                            </div>
                            <div>
                                <span>${order.name}</span>
                            </div>
                        </div>
                
                        <div class ="orders__wrapper__price">
                            <div class = "orders__wraper__desc_cont">
                                <span class = "order__state__desc">Cena: </span>
                            </div>
                            <div>
                                <span>${order.price} PLN</span>
                            </div>
                        </div>
                
                        <div class ="orders__wrapper__qunatity">
                            <div class = "orders__wraper__desc_cont">
                                <span class = "order__state__desc">Ilość: </span>
                            </div>
                            <div>
                                <span>${order.quantity}</span>
                            </div>
                        </div>
                        </div>
                    `
                })
                    
            })


            } );




        }
        ).catch(error =>{
            console.log(error)
        })

        }else{


        }

    
    })
    
    }else{
      console.log("Brak dostępu")
    }


    const getOrdersUser = async () => {

        try {
          const response = await fetch('http://localhost:8080/api/v1/orders/OrdersUser/' + localStorage.getItem('user_id'))
          return response.json();
      } catch(e) {
          return Promise.reject(e);
      }
  
  }

  const getProductInOrder = async (orderId) => {

    try {
      const response = await fetch('http://localhost:8080/api/v1/productInOrder/' + orderId)
      return response.json();
  } catch(e) {
      return Promise.reject(e);
  }

}