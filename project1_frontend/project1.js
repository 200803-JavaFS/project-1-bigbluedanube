// Project1 endpoint
const url = 'http://localhost:8080/project1/';

document.getElementById("login").addEventListener("click", loginFunction);

async function loginFunction() {
    let uName = document.getElementById("username").value;
    let pwd = document.getElementById("password").value;

    let user = {
        username: uName,
        password: pwd
    };

    let resp = await fetch(url + "login", {
        method : "POST",
        body : JSON.stringify(user),
        credentials: "include"
    });

    if(resp.status === 200){
        console.log("You made it to the response.status === 200 part");
        document.getElementById("loginSection").innerText = "You have successfully logged in!";
        window.location.href = "finManPage.html";
    } else {
        document.getElementById("loginSection").innerText = "Your login attempt has failed.";
        // This could possibly go to a Failure Page, which has a button that says, "take me back"
    }

}
