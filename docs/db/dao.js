var modules = [
    { "std" : {"state" : "N"},
        "code" : "CURS1",
        "name" : "Бухгалтерский учет, анализ и аудит",
        "stunden" : 520,
        "dtype" : "ПП",
        "eduform" : "dist",
        "authors" : [1, 2],
        "price" : { "min" : 1, "max" : 12000},
        "owner" : [1, 2],
        "content" : [0, 1, 2],
    },
    { "std" : {"state" : "N"},
        "code" : "CURS2",
        "name" : "Бухгалтерский учет, анализ и аудит",
        "stunden" : 520,
        "dtype" : "ПП",
        "eduform" : "ft",
        "authors" : [2, 3],
        "price" : { "min" : 1, "max" : 32000},
        "owner" : [1, 2],
        "content" : [0, 1, 2, 3],
    },
    { "std" : {"state" : "N"},
        "code" : "CURS3",
        "name" : "Бухгалтерский учет, анализ и аудит",
        "stunden" : 252,
        "dtype" : "ПК",
        "eduform" : "dist",
        "authors" : [1, 3],
        "price" : { "min" : 1, "max" : 8000},
        "owner" : [1, 2],
        "content" : [0, 1],
    },
];

var authors = [
    {
        "std" : {"ZSTA" : "N", "ZOID" : 0},
        "name" : "lol",
        "categories" : [0, 1],
    },
    {
        "std" : {"ZSTA" : "N", "ZOID" : 1},
        "name" : "mok",
        "categories" : [0, 1],
    },
];

var orgs = [
    {
        "std" : {"ZSTA" : "N", "ZOID" : 0},
        "name" : "org1",
    },
    {
        "std" : {"ZSTA" : "N", "ZOID" : 1},
        "name" : "org2",
    },
];

var categories = [
    {
        "std" : {"ZSTA" : "N", "ZOID" : 0},
        "zname" : "BIO",
        "name" : "биология",
    },
    {
        "std" : {"ZSTA" : "N", "ZOID" : 1},
        "zname" : "ARCHDEV",
        "name" : "строительство",
    },
];

var dic = {
    "ZSTA" : ['N', 'D','I' ],
    "dtype" : [ 'ПП', 'ПК', 'БАКАЛАВР', 'МАГИСТР','МОДУЛЬ','ЛЕКЦИЯ','ЛАБА','ТЕСТ','ЭКЗАМЕН'],
    "dtype_courses" : ['ПП', 'ПК', 'БАКАЛАВР', 'МАГИСТР'],
    "eduform" : ['DIST', 'FT']
};

// JSON.stringify(modules);
// JSON.stringify(authors);
// JSON.stringify(categories);
// JSON.stringify(dic);