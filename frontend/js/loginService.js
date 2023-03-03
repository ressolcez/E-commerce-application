
const loginUser = async () => {

  const login = document.getElementById('login');
  const password = document.getElementById('password');

    await fetch('http://localhost:8080/api/v1/authentication', {
       method: 'POST',
       headers: {
         'Content-Type': 'application/json'
       },
       body: JSON.stringify({login: login.value, password: password.value})
     }).then(response => response.json())
     
     .then( json => {

       if(json.userId !== -1 && json.token !== -1){
       localStorage.setItem('user_id', json.userId);
       localStorage.setItem('token', json.token);
       location.replace("home.html")

        
       }else{
          document.getElementById("user__state").style.display="block";
       }
    } 

    ).catch(error => {
       console.log(error);
     });
}



