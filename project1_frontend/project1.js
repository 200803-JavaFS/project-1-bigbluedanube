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
        mosesPage();
    } else {
        console.log("Ya dun goofed. Check your login credentials.");
        document.getElementById("loginSection").innerText = "Your login attempt has failed.";
        // This could possibly go to a Failure Page, which has a button that says, "take me back"
    }
}

// It's called "mosesPage" because it divides the users into two sections... get it?
async function mosesPage(){
    let resp2 = await fetch(url + "getUser", {   // resp2 is the incoming data from our Tomcat server--it's a JSON string of our logged-in user's information.
        method : "GET",
        credentials: "include"
        })

    let data = await resp2.json();
    console.log(data);
    if(data.userRoleId.userRoleId === 4){
        window.location.href = "finManPage.html";
    } else if (data.userRoleId.userRoleId === 3) {
        window.location.href = "employeePage.html";
    } else {
        window.location.href = "project1.html";
        console.log("Okay... sooooo... that was weird.");
    }
}

async function logout(){
    let resp2 = await fetch(url + "logout", {
    })
    if(resp.status === 200){
        window.localation.href = "project1.html";
    }
}

async function approveOrDeny(){
    let resp2 = await fetch(url + "logout", {
    })
    if(resp.status === 200){
        window.localation.href = "project1.html";
    }
}



// ORPHANED CODE.
// window.location.href = "successPage.html";