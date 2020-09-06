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
    let data = await resp.JSON();   // this is the incoming data from our Tomcat server--it's a JSON string of our logged-in user's information.
    console.log(data);              // curious to see what happens here. So far, nothing...?
    window.location.href = "successPage";
    let uId = data.userId;
    sessionStorage.setItem("userId", userId);       // I could like th understand why this is a thing.
    let uRole = data.UserRoles.userRoleId;
}


//         window.location.href = "employeePage.html";
//         window.location.href = "finManPage.html";
