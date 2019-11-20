"use strict"

function after_reg() {
    let text = `<p>This button will show you voters list</p>`;
    let button_voters = `
    <button id="voters" onclick="get_voters()">Get voters</button>
    `;
    return text + button_voters;
}

async function send_name() {
    let newname = name1.value;
    maindiv.innerHTML = after_reg();
    let server_url = `./js/add_me.js?name=` + newname;
    let response = await fetch(server_url);
    if (response.ok) {
        let div = document.createElement('div');
        div.innerHTML = response.text();
        maindiv.append(div);
    }
}

function get_voters() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if ((this.readyState == 4)) {
            let div = document.createElement('div');
            div.innerHTML = this.responseText;
            maindiv.append(div);
        }
    };
    let server_url = "./js/voters";
    xhttp.open("GET", server_url, true);
    xhttp.send();
}