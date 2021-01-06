const fs = require("fs");
const http = require("http");



const server = http.createServer((req, res) => {
  const url = req.url;

  if ((/\.(jpg)$/i).test(url)) {
    res.writeHead(200, { "Context-Type": "image/jpg" });
    (function (res, path) {
      setTimeout(() => {
        res.write(fs.readFileSync(path));
        res.end();
      }, 3000);
    })(res, url.substring(1));
  } else {
    res.end();
  }
});

server.listen(3000);
