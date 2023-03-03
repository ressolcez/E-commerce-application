const getProducts = async () => {
    
    try {
        const response = await fetch('http://localhost:8080/api/v1/product')
        return response.json();
    } catch(e) {
        return Promise.reject(e);
    }

}