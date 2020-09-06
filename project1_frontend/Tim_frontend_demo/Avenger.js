const url = "http://localhost:8080/HelloJackson/";

document.getElementById("login-btn").addEventListener("click", loginFunc);

async function loginFunc() {
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    let user = {
        username : usern,
        password : userp
    }

    let resp = await fetch(url + "login", {
        method : "POST",
        body : JSON.stringify(user)
    })

    if(resp.status === 200){
        console.log(resp);
        document.getElementById("login-row").innerText = "YOU HAVE LOGGED IN.";
        let button = document.createElement("button");
        button.className = "btn btn-success";
        button.id = "findAllBtn";
        button.innerText = "Find All Avengers";
        button.onclick = findAllFunc;
        document.getElementById("table-row").appendChild(button);
    } else {
        document.getElementById("login-row").innerText = "Login failed.";
    }
}

async function findAllFunc() {
    // most of this is debugging that happened in the last hour of lecture. Something went very, very wrong on Tim's browser and these are some ways to try and mitigate that. As of 17:14 on Friday, we're still working on it.

    const myHeaders = new Headers();

    myHeaders.append("Access-Control-Allow-Credentials", true);

    let resp = await fetch(url + "avenger", {
        mode: "no-cors",
        credentials : "include",
        headers: myHeaders
    });


    if(resp === 200) {
        let data = await resp.json;
        console.log(data);          // sanity check.
    }
}