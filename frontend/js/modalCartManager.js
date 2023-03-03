getProducts()
    .then( products => {
        const wrapper = document.querySelector('.list__products');
        products.forEach( product => {
            wrapper.innerHTML += `
            <div class = "product">
            <div class = "product__image">
                <img style = "object-fit: cover; height:200px;" src = "${product.image}">
            </div>
            <div class="product__content">
                <div class = "product__content__title">
                    <a href="items.html?product=${product.id}">
                     <span>${product.name}</span>
                    </a>
                </div>
                <div class = "product__content__btn__price">
                    <div class = "product__price">
                        <span>${product.price} PLN</span>
                    </div>
                    <div class = "product__btn">
                        <button id = "btn" onclick="getModalAddCart()" style = "display: flex; justify-content: center; align-items: center;" > <img src = "images/add.png"/> </button>
                    </div>
                </div>
            </div>  
            <div class = "modal" >
                <div>
                    <div class = "modal__title">
                    ${product.name}
                    </div>
                    <div class="modal__text">
                    ${product.description}
                        
                    </div>
                </div>
            </div>          
        </div>
            `
        } );
    } );
