var express = require("express");
var fs = require("fs");
var app = express();
var bodyParser = require("body-parser");

// var exec = require("exec");
// const { exec } = require("node:child_process");

const { execFile } = require("child_process");

app.use(bodyParser.json());

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));

app.get("/", (req, res) => {
  console.log("Get request.");
  const child = execFile(
    "python",
    ["Frames_Extractor.py"],
    (error, stdout, stderr) => {
      if (error) {
        throw error;
      }
      console.log(stdout);
    }
  );
});

const port = 3000;

app.listen(port);
console.log("Listening at port:  ", `${port}`);
