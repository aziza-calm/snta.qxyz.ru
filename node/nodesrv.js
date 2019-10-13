var http=require('http');
var fs=require('fs');
let port=3000;
function serveStaticFile(res,path,contentType,responseCode) {
 if (!responseCode) responseCode=200;
 var filename="docs"+path;
 console.log(filename);
 fs.readFile(filename, function(err,data) {
  if (err) {
   res.writeHead(500, {'Content-Type':'text/plain'});
   res.end('500-Internal Error');
  }
  else {
   res.writeHead(responseCode,{'Content-Type':contentType});
   res.end(data);
  }
 });
}
http.createServer(function(req,res){
 var path=req.url.replace(/\/?(?:\?.*)?$/,'').toLowerCase();
 console.log(path);
 switch(path) {
  case '/vote.html':
  case '/vote':
  case '/':
          serveStaticFile(res,'/vote.html','text/html');
          break;
  case '/js/vote.js':
          serveStaticFile(res,'/js/vote.js','application/javascript');
          break;
  case '/js/voters':
          serveStaticFile(res,'/js/voters','text/plain');
          break;
 // case 'img

  default:
          //serveStaticFile(res,'/public/404.html','text/html',404);
          console.log("No such file or directory");
          break;
 }
}).listen(3000);
console.log('Сервер запущен на localhost:'+port+'; нажмите CTRL+C для завершения...')

