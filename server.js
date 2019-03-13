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
    res.send(`<!DOCTYPE html>
        <html>
            <head>
                <title>TBatimento cardiaco</title>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
                <body>
                    <h1>cardiaco Graph</h1>
                    <h4>Date: <span id="date"></span></h4>
                    <div class="chart-container" style="position: relative; width:75vw; margin: auto;">
                        <canvas id="myChart"></canvas>
                    </div>
                    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
                        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
                        <script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.0.4/socket.io.js"></script>
                        <script>
                            var socket = io.connect('http://localhost:4000'); //connect to server
                            var ctx = document.getElementById('myChart').getContext('2d');
            var chart = new Chart(ctx, {
                                // The type of chart we want to create
                                type: 'line',
                            // The data for our dataset
                data: {
                                labels: [],
                datasets: [{
                                label: "Temperature",
                            borderColor: "#FF5733",
                            data: [],
                            fill: false,
                            pointStyle: 'circle',
                            backgroundColor: '#3498DB',
                            pointRadius: 5,
                            pointHoverRadius: 7,
                            lineTension: 0,
                        }]
                        },
                        // Configuration options go here
                options: {}

                            });
            parser.on('card', function(data) { //As a temp data is received 
                                console.log(data.card);
                            document.getElementById('date').innerHTML = data.date; //update the date
                if(chart.data.labels.length != 15) { //If we have less than 15 data points in the graph
                                chart.data.labels.push(data.time);  //Add time in x-asix
                            chart.data.datasets.forEach((dataset) => {
                                dataset.data.push(data.card); //Add temp in y-axis
                            });
                        }
                else { //If there are already 15 data points in the graph.
                                chart.data.labels.shift(); //Remove first time data
                            chart.data.labels.push(data.time); //Insert latest time data
                    chart.data.datasets.forEach((dataset) => {
                                dataset.data.shift(); //Remove first temp data
                            dataset.data.push(data.card); //Insert latest temp data
                        });
                    }
                    chart.update(); //Update the graph.
                });
        </script>
    </body>
                    <style>
                        h1 {
                            text - align: center;
                        font-family: 'Lato', sans-serif;
                    }
        h4 {
                            text - align: center;
                        font-family: 'Lato', sans-serif;
                    }
        p {
                            text - align: center;
                        font-family: 'Lato', sans-serif;
                    }
    </style>
</html>`)
})