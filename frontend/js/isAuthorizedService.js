const isAuthorized = async () => {
    try {

        const response = await fetch('http://localhost:8080/api/v1/authentication', {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': localStorage.getItem('token')
            },
          })

        return response.json();
    } catch(e) {
        console.log(e)
        return Promise.reject(e);
    }
}