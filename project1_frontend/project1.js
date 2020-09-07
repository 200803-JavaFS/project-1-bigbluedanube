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
        // console.log("Okay... sooooo... that was weird. What happened?");
    }
}

async function logout(){
    let resp = await fetch(url + "logout", {
    })
    if(resp.status === 200){
        window.localation.href = "project1.html";
    } else {
        window.localation.href = "https://awoiaf.westeros.org/index.php/Iron_Bank_of_Braavos";
        // Clearly, you're in need of a good decompressiopn session. How about you read our brochure?
    }
}

async function getByStatus(statusString){
    let resp = await fetch(url + "getByStatus" + statusString, {
    })
    if(resp.status === 200){
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

        // let utcSeconds = reimbursement.reimbSubmitted;
        // let d = new Date(0);
        // d.setUTCSeconds(utcSeconds);

        for (let reimbursement of data){
            console.log(reimbursement);
            let row = document.createElement("tr");
            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.reimbId;
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
            cell5.innerHTML = reimbursement.reimbResolved;
            row.appendChild(cell5);
            let cell6 = document.createElement("td");
            console.log(reimbursement.reimbAuthor.username);     ////////
            cell6.innerHTML = reimbursement.reimbAuthor.username;
            row.appendChild(cell6);
            let cell7 = document.createElement("td");
            console.log(reimbursement.reimbResolver);           ////////
            cell7.innerHTML = reimbursement.reimbResolver.username;
            row.appendChild(cell7);
            let cell8 = document.createElement("td");
            console.log(reimbursement.reimbStatus);             ////////
            cell8.innerHTML = reimbursement.reimbStatusFk.reimbStatus;
            row.appendChild(cell8);
            let cell9 = document.createElement("td");
            console.log(reimbursement.reimbTypeFk);             ////////
            cell9.innerHTML = reimbursement.reimbTypeFk.reimbType;
            row.appendChild(cell9);
            document.getElementById("ironbody").appendChild(row);
        }
    }
}

async function AddFunc(){

    // let rId = document.getElementById("reimbId").value;
    let rAmt = document.getElementById("reimbAmount").value;
    // let rSubmitted = document.getElementById("reimbSubmitted").value;
    // let rResolved = document.getElementById("reimbResolved").value;
    let rDescr = document.getElementById("reimbDescription").value;
    let rAuthor = document.getElementById("reimbAuthor").value;
    let rResolver = document.getElementById("reimbResolver").value;

    let rStat = document.getElementById("reimbStatusFk").value;
    let rType = document.getElementById("reimbTypeFk").value;


    let reimbursement = {
        // reimbId : rId,
        reimbAmount : rAmt,
        // reimbSubmitted : rSubmitted,
        // reimbResolved : rResolved,
        reimbDescription : rDescr,
        reimbAuthor : rAuthor,
        reimbResolver : rResolver,
        
        reimbStatusFk : rStat,
        reimbTypeFk : rType
    }

    console.log(reimbursement)

    let resp = await fetch(url + "reimbursement", {     // This is the POST request--"POST my reimbursement for me."
        method: 'POST',
        body: JSON.stringify(reimbursement),
        credentials: "include"
    })

    if(resp.status === 201){
        allReimbursements()
    } else {
        document.getElementById("loginSection").innerText = "Reimbursement could not be added.";
    }
}

async function resolve(){
    let resp = await fetch(url + "resolve", {
        method: 'POST',
        body: JSON.stringify(reimbursement),
        credentials: "include"
    })

    if(resp.status === 201){
        document.getElementById("reimbStatusFk").value = "APPROVED";    
    } else {
        document.getElementById().innerText = "DENIED";
    }

}

async function getByAuthor(){
}

async function viewAllResolved(){
}

async function user(){

}