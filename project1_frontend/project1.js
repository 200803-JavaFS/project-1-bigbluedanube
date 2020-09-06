// PokeAPI endpoint
const url = 'http://localhost:8080/project1';

document.getElementById("login").addEventListener("click", loginFunction);

async function loginFunction() {
    let uName = document.getElementById("username").value;
    let pwd = document.getElementById("password").value;

    let newUser = {
        username: uName,
        password: pwd
    };

    
}
