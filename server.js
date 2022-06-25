var express = require("express");
var fs = require("fs");
var app = express();
var bodyParser = require("body-parser");

const fileupload = require("express-fileupload");

const path = require("path" );
var http = require("http")
var PythonShell = require("python-shell");
const spawn = require('child process'). spawn
const process = spawn("python" ,[" ./verify.py"])
app.set ('view engine' ,'ejs');
app.use (fileupload());

// var exec = require("exec");
// const { exec } = require("node:child_process");

const { execFile } = require("child_process");

app.use(bodyParser.json());

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));

app.get("/", (req, res, next) => {
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

  res.status(200);
  res.render ("index")
  // TODO: execute node script
});




app.post ("/upload" ,async(req,res,next)=>{

    try{

        const file = req.files.mFile
        const videotype = req.body.filename

        const timeunique = new Date().getTime().tostring()


        const filename = "Gautham.mp4";
        const savepath = path.join(dirname,"public", "uploads", "GESTURE_PRACTICE_"+videotype+"_"+timeunique+"_"+filename)

        console.log(savepath);
        await file.mv(savepath)

        res.send ({
        success:true,
        message: "File uploaded"
        });

        res.redirect('/');

    } catch (e) {
        console.log(e);
      }
    })

    const port = 3000;

    app.listen(port);
    console.log("Listening at port:  ", `${port}`);