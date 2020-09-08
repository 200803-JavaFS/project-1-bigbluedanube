// Project1 endpoint
const url = 'http://localhost:8080/project1/';

// document.getElementById("login").addEventListener("click", loginFunction);

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
        let data = await resp.json();
        sessionStorage.setItem("userId", data);
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
        document.getElementById("addOne").hidden = true;

    } else if (data.userRoleId.userRoleId === 3) {
        window.location = "employeePage.html";
    } else {
        window.location.href = "project1.html";
        // console.log("Okay... sooooo... that was weird. What happened?");
    }
}

async function logout(){
    let resp = await fetch(url + "logout", {
    })
    if(resp.status === 200){
        window.location.href = "project1.html";
    } else {
        window.location.href = "project1.html";
        //https://awoiaf.westeros.org/index.php/Iron_Bank_of_Braavos
    }
}


async function allReimbursements(){
    document.getElementById("ironbody").innerText ="";
    let resp = await fetch(url + "reimbursement", {     // This is the GET request--"GET all reimbursements for me."
        method : "GET",
        credentials: 'include',
    });

    if (resp.status === 200){
        let data = await resp.json();

        for (let reimbursement of data){
            console.log(reimbursement);
            let row = document.createElement("tr");
            let cell = document.createElement("td");
            a = document.createElement('a');
            a.href = url + "/reimbursement/" + reimbursement.reimbId; // Insted of calling setAttribute 
            a.innerHTML = reimbursement.reimbId // <a>INNER_TEXT</a>
            cell.appendChild(a); // Append the link to the div
            row.appendChild(cell);
            let cell2 = document.createElement("td");
            cell2.innerHTML = reimbursement.reimbAmount;
            row.appendChild(cell2);
            let cell3 = document.createElement("td");
            cell3.innerHTML = reimbursement.reimbDescription;
            row.appendChild(cell3);
            let cell4 = document.createElement("td");
            cell4.innerHTML = new Date(reimbursement.reimbSubmitted);
            row.appendChild(cell4);
            let cell5 = document.createElement("td");
            cell5.innerHTML = new Date(reimbursement.reimbResolved);
            row.appendChild(cell5);
            let cell6 = document.createElement("td");
            console.log(reimbursement.reimbAuthor.username);        // this keeps logging as "null".
            cell6.innerHTML = reimbursement.reimbAuthor.username;   ///////
            row.appendChild(cell6);
            let cell7 = document.createElement("td");
            console.log(reimbursement.reimbResolver);
            if(reimbursement.reimbResolver != null){
                cell7.innerHTML = reimbursement.reimbResolver.username;
            } else {
                console.log("Your Reimbursement Resolver is null.")
            }
            row.appendChild(cell7);
            let cell8 = document.createElement("td");
            cell8.innerHTML = reimbursement.reimbStatusFk.reimbStatus;
            row.appendChild(cell8);
            let cell9 = document.createElement("td");
            console.log(reimbursement.reimbTypeFk);
            cell9.innerHTML = reimbursement.reimbTypeFk.reimbType;
            row.appendChild(cell9);
            document.getElementById("ironbody").appendChild(row);
        }
    }
}

async function AddFunc(){
    document.getElementById("ironbody2").innerText ="";
    let myId = sessionStorage.getItem("userId");

    let reId = myId;
    let reAmt = document.getElementById("reimbAmount").value;
    let rDescr = document.getElementById("descriText").value;
    // let rSubmitted = document.getElementById("reimbSubmitted").value;
    // let rResolved = document.getElementById("reimbResolved").value;
    // let rAuthor = document.getElementById("usernameInput1").value;
    // let rResolver = document.getElementById("reimbResolver").value;
    // let rStat = document.getElementById("reimbStatus").value;
    let reType = document.getElementById("rTypeString").value;

    let reimbursement = {
        rAuthorId : reId,
        rAmt : reAmt,
        rDescription : rDescr,
        // reimbSubmitted : rSubmitted,
        // reimbResolved : rResolved,
        // rAuthorId : rAuthor,
        // reimbResolver : rResolver,
        // reimbStatus : rStat,
        rType : reType
    }


    let resp = await fetch(url + "reimbursement", {     // This is the POST request--"POST my reimbursement for me."
        method: 'POST',
        body: JSON.stringify(reimbursement),
        credentials: "include"
    })

    if(resp.status === 201){
        getByUser();
    } else {
        document.getElementById("ironbody2").innerText = "Reimbursement could not be added.";
    }
}

async function getByUser(){
    document.getElementById("ironbody2").innerText ="";
    let resp = await fetch(url + "getByAuthor", {
    method: 'GET',
    credentials: "include"
})
console.log(resp);

// follow the path of information with sysouts and consol.logs 
// and find out where it's breaking, 
// it sounds like it's probably a spelling error somewhere

if (resp.status === 200){
    let data = await resp.json();
    for (let reimbursement of data){
    let row = document.createElement("tr");
    let cell = document.createElement("td");
    cell.innerHTML = reimbursement.reimbAmount;
    row.appendChild(cell);
    let cell2 = document.createElement("td");
    cell2.innerHTML = reimbursement.reimbDescription;
    row.appendChild(cell2);
    }
}}

async function resolve(){
    let myId = sessionStorage.getItem("userId");
    
    let reimbursement = {
        rId : document.getElementById("input1").value,          // NUMBER 1
        // reimbAmount : rAmt,
        // reimbDescription : rDescr,
        // reimbSubmitted : rSubmitted,
        // reimbResolved : rResolved,
        // reimbAuthor : rAuthor,
        resolverId : myId,                                      // NUMBER 3
        rStatus : document.getElementById("input2").value,      // NUMBER 2
        // reimbTypeFk : rType
    }
    console.log(reimbursement);

    let resp = await fetch(url + "resolve", {
        method: 'POST',
        body: JSON.stringify(reimbursement),
        credentials: "include"
    })
    if(resp.status === 201){
        allReimbursements();    
    } else {
        console.log("Update Failed.");
    }
}

async function getById(){
    let resp = await fetch(url + "getOne", {
        method: 'GET',
        body: JSON.stringify(reimbursement),
        credentials: "include"
    })
    if(resp.status === 201){
        document.getElementById("reimbStatus").value = "APPROVED";    
    } else {
        document.getElementById("reimbStatus").innerText = "DENIED";
    }
}

async function getByStatus(statusString){
    let resp = await fetch(url + "getByStatus" + statusString, {
    })
    if(resp.status === 200){
    }
}