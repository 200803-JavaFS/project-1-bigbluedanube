let button = document.getElementById('btn');
button.addEventListener("click", ajaxFunc);

let pokepic = document.getElementById("pokepic");
let pokename = document.getElementById("pokename");
let poketype = document.getElementById("poketype");
let pokenum = document.getElementById("pokenum");

function ajaxFunc(){
    let num = document.getElementById("field").value;           // this was originally called 'number', but that was a keyword, so we changed it to num b/c Best Practices.
    
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            let data = JSON.parse(xhr.responseText);            // the test of the body of the response that came back. We are calling that 'data'.
            renderHTML(data);
        }
    }

    xhr.open("GET", "https://pokeapi.co/api/v2/pokemon/" + num + "/");
    xhr.send();                                                             // There's no body here because this is a GET request.
}

function renderHTML(data){
    pokepic.setAttribute("src", data.sprites.front_default);    // front_default is one of the attributes of each Pokemon in the PokeAPI.
    pokename.innerText = data.name;
    poketype.innerText = data.types[0].type.name;
    if(data.types[1]){                                          // if it comes back as something it will be true, if null, it will be false.
        poketype.append(", ");
        poketype.append(data.types[1].type.name);
    }
    pokenum.innerText = data.id;                                // this gives the Pokenumber.
}