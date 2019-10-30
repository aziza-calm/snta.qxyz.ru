"use strict"

function after_reg() {
    let text = `<p>This button will show you voters list</p>`;
    let button_voters = `
    <button id="voters" onclick="get_voters()">Get voters</button>
    `;
    console.log(text + button_voters);
    return text + button_voters;
}

function send_name() {
    let newname = name1.value;
    console.log(newname);
    maindiv.innerHTML = after_reg();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if ((this.readyState == 4)) {
            let div = document.createElement('div');
            div.innerHTML = this.responseText;
            maindiv.append(div);
            console.log(this.responseText);
        }
    };
    let server_url = `./js/add_me.js?name=` + newname;
    console.log(server_url);
    xhttp.open("GET", server_url, true);
    xhttp.send();
}

function get_voters() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if ((this.readyState == 4)) {
            document.getElementById("maindiv").innerHTML = this.responseText;
            console.log(this.responseText);
        }
    };
    let server_url = "./js/voters";
    xhttp.open("GET", server_url, true);
    xhttp.send();
}