var http = require('http');
var fs = require('fs');
var port = 3000;
var users = ["test1", "test2", "test3"];

function serveStaticFile(res, path, contentType, responseCode) {
  if (!responseCode) responseCode = 200;
  var filename = "../docs" + path;
  console.log("Filename: " + filename);
  fs.readFile(filename, function(err, data) {
    if (err) {
      res.writeHead(500, {'Content-Type':'text/plain'});
      res.end('500-Internal Error');
      console.log(err);
    }
    else {
      res.writeHead(responseCode,{'Content-Type':contentType});
      res.end(data);
    }
  });
}

http.createServer(function(req, res) {
  var path = req.url.replace(/\/?(?:\?.*)?$/,'').toLowerCase();
    console.log("Req url: " + req.url);
    console.log("Path: " + path);
    switch(path) {
      case '':
      case '/vote':
      case '/vote.html':
        serveStaticFile(res,'/vote.html','text/html');
        break;
      case '/js/vote.js':
        serveStaticFile(res,'/js/vote.js','application/javascript');
        break;
      case '/js/add_me.js':
        var name=req.url.replace(/\/js\/add_me.js\?name=/,'');
	users = users + " " + name;
        serveStaticFile(res,'/js/add_me.js','application/javascript');
        break;
      case '/js/voters':
        //serveStaticFile(res,'/js/voters','text/html');
        res.writeHead(200,{'Content-Type':'text/javascript'});
        //var u=users.join(" ");
        res.end(users);
        break;
      default:
        //serveStaticFile(res,'/public/404.html','text/html',404);
        res.writeHead(404,{'Content-Type': 'text/plain'});
        res.end('No such file or directory');
        break;
    }
}).listen(3000);
console.log('Сервер запущен на localhost:'+port+'; нажмите CTRL+C для завершения...')
