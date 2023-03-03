retrieveProducts()
    .then( products => {
        const wrapper = document.querySelector('.container');
        
            wrapper.innerHTML += `
            <div class="left-column">
            <img  src="${products.image}">
          </div>
        
          <div class="right-column">
            <div class="product-description">
              <h1>${products.name}</h1>
              <p>${products.description}</p>
            </div>
            <div class="product-price">
              <span>${products.price} PLN</span>
              <btn onclick="getModalAddCart(${products.id})" class="cart-btn">Kup</btn>
            </div>
            
          </div>
      </div>
            `
    } );