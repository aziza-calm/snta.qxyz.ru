"use strict"

function after_reg() {
    let text = `<p>This button will show ypu voters list</p>`;
    let button_voters = `
    <button id="voters">Get voters</button>
    `;
    console.log(text + button_voters);
    return text + button_voters;
}

function send_name() {
    maindiv.innerHTML = after_reg();
    document.getElementById("voters").addEventListener("click", get_voters());
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