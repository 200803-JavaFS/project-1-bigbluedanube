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
        document.getElementById("loginSection").innerText = "Login success!";
        mosesPage();
    } else {
        console.log("Ya dun goofed. Check your login credentials.");
        document.getElementById("loginSection").innerText = "Your login attempt has failed.";
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
        console.log("Okay... sooooo... that was weird. What happened?");
    }
}

async function logout(){
    let resp2 = await fetch(url + "logout", {
    })
    if(resp.status === 200){
        window.localation.href = "project1.html";
    } else {
        window.localation.href = "https://awoiaf.westeros.org/index.php/Iron_Bank_of_Braavos";
        // Clearly, you're in need of a good decompressiopn session. How about you read our brochure?
    }
}

async function getByStatus(statusString){
    let resp3 = await fetch(url + "getByStatus" + statusString, {
    })
    if(resp3.status === 200){

    }
}


async function allReimbursements(){
    document.getElementById("ironbody").innerText ="";

}

async function resolve(){
}

async function getByAuthor(){
}

async function viewAllResolved(){
}

async function user(){
}


// ORPHANED CODE.
// window.location.href = "successPage.html";