const express = require('express');
const textToBraille = require('./braille');
const brailleToText = require('./text');
const bodyParser = require('body-parser');
const app = express();

app.use(bodyParser.text());

app.post('/translate/json', (req, res) => {
    const {text} = req.body;
    if(text){
        const braille = textToBraille(text);
        res.status(200).json({code: braille});
    } else {
        res.status(422).json({error: "No text sent"});
    }
});

app.post('/translate/java', (req, res) => {
    const {text} = req.body;
    if(text){
        const braille = textToBraille(text);
        console.log(braille);

        res.status(200).send(braille);
    } else {
        res.status(422).json({error: "No text sent"});
    }
});

app.post('/translate/brailletotext/java', (req,res) => {
    const text = req.body;
    console.log("Request: " + text);
    if(text){
        const eng = brailleToText(text);
        console.log("Response: " + eng);
        res.send(eng).status(200);
    } else {
        res.status(422).send("Response: No text sent");
    }
});

app.listen('5000', ()=> console.log("Listening on 5000"));