const retrieveCurrentProduct = () => {

    const urlParams = new URLSearchParams(window.location.search);
    const product = urlParams.get('product');
    return product;
}


const retrieveProducts = async () => {
  try {
      const response = await fetch('http://localhost:8080/api/v1/product/'+ retrieveCurrentProduct())
      return response.json();
  } catch(e) {
      return Promise.reject(e);
  }
}


