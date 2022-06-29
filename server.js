var express = require("express");
var fs = require("fs");
var app = express();
var bodyParser = require("body-parser");

const fileupload = require("express-fileupload");

const path = require("path" );
var http = require("http")
// var PythonShell = require("python-shell");
// const spawn = require('child process'). spawn
// const process = spawn("python" ,[" ./verify.py"])
app.set ('view engine' ,'ejs');
app.use (fileupload());

// var exec = require("exec");
// const { exec } = require("node:child_process");

const { execFile } = require("child_process");

app.use(bodyParser.json());

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));

app.get("/", (req, res, next) => {

  const child = execFile("python",["Frames_Extractor.py"], (error, stdout, stderr) => {
      if (error || stderr) {
        console.log("Frames error:",error);
        console.log("Frames Std error:",stderr);
      }
      console.log("Executing key points.")
      const child2 = execFile("node",["main.js"],(err,stdout, stderr) =>{
        if (err|| stderr) {
          console.log('Key poinst err: ', err);
          console.log('Key points stderr: ', stderr);
          return;
        }
        console.log("Executing csv.")
        const child3 =execFile("python",["convert_to_csv.py"],(err,stdout, stderr) =>{
          if (err|| stderr) {
            console.log('csv stderr: ', stderr);
            console.log('csv err: ', err);
            return;
          }
        })
      })
      console.log(stdout);
    }
  );

  res.status(200);
  // res.render ("index")
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

    const port = 3001;

    app.listen(port);
    console.log("Listening at port:  ", `${port}`);