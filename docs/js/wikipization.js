let dics = {
    'Молекулярный докинг' : 'https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D0%BB%D0%B5%D0%BA%D1%83%D0%BB%D1%8F%D1%80%D0%BD%D1%8B%D0%B9_%D0%B4%D0%BE%D0%BA%D0%B8%D0%BD%D0%B3',
    'Окно Овертона' : 'https://ru.wikipedia.org/wiki/%D0%9E%D0%BA%D0%BD%D0%BE_%D0%9E%D0%B2%D0%B5%D1%80%D1%82%D0%BE%D0%BD%D0%B0',
    'Kanji' : 'https://google.com',
    'докинг' : 'https://google.com',
};

function add_wiki() {
    let els = document.getElementsByTagName('p');
    for (el of els) {
        if (el.innerHTML != undefined) {
            let text = el.innerHTML;
            for (let wort in dics) {
                text = text.replace(wort, `<a href="${dics[wort]}">${wort}</a>`);
                console.log(`Replaced ${wort} on ${dics[wort]}`);
            }
            el.innerHTML = text;
        }
    }
}

add_wiki();