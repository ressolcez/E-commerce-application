const store = document.querySelector(".topbar__rightBox__account");
const btn = document.getElementById("btn");

if(localStorage.getItem('token')) {
isAuthorized()
.then(response => {

  if(response.response === 'pass'){
    
        store.innerHTML = `
        <img src = "images/user.png"/>
        <a>
            <span style = "cursor:pointer" onclick = "logutUser()" class = "topbar__rightBox_spanItem">Wyloguj się</span>
        </a>
        `;

  }else{

    store.innerHTML =  `
    <img src = "images/user.png"/>
    <a href = "login.html">
        <span class = "topbar__rightBox_spanItem">Zaloguj</span>
    </a>
    `
  }

}).catch(error =>{
    console.log(error);
})
}else{

  store.innerHTML =  `
  <img src = "images/user.png"/>
  <a href = "login.html">
      <span class = "topbar__rightBox_spanItem">Zaloguj</span>
  </a>
  `

}
