
 const registerUser = async () => {

  const login = document.getElementById('login');
  const password = document.getElementById('password');
  const name = document.getElementById('name');
  const surname = document.getElementById('surname');
  const phone = document.getElementById('phone');
  const adres = document.getElementById('adres');

     await fetch('http://localhost:8080/api/v1/user', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({login: login.value, password: password.value, name: name.value,surname: surname.value,phone: phone.value ,address: adres.value})
      }).then( result => {
        if(result.status === 500 || result.status === 400){
          document.getElementById("user__state").style.display="block";
        }else{
          location.replace("login.html")
        }
      }
     ).catch(error => {
        console.log(error);
      });
}

  
    

