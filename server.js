'use strict';
const express = require('express');
const port = process.env.PORT || 8080;
const host = process.env.HOST || '127.0.0.1';

//carregar bibliotecas globais
//const cors = require('cors');
//const bodyParser = require('body-parser');
//const expressSanitizer = require('express-sanitizer');
//const expressValidator = require('express-validator');

//iniciar a aplicação
var app = express();
//app.use(bodyParser.json(), bodyParser.urlencoded({ extended: true }));
//app.use(expressSanitizer());
//app.use(expressValidator());
var server = app.listen(port, function (err) {
    if (!err) {
        console.log('Your app is listening on ' + host + 'and port ' + port);
    } else { console.log(err); }
});

//sensores
var io = require('socket.io')(server);
const SerialPort = require('serialport');
const Readline = SerialPort.parsers.Readline;
const portSensor = new SerialPort('COM5');
const parser = portSensor.pipe(new Readline({ delimiter: '\r\n' }));
//parser.on('data');

parser.on('data', (card) => {
    console.log(card);
    var today = new Date();
    io.sockets.emit('card', { date: today.getDate() + "-" + today.getMonth() + 1 + "-" + today.getFullYear(), time: (today.getHours()) + ":" + (today.getMinutes()), card: card });
});

app.get("/", function (req, res) {
    res.send("")
})