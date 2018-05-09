
const express = require('express');
const app = express();         
const bodyParser = require('body-parser');
const port = 50169; //porta padrão
const sql = require('tedious');
//const connStr = "Server=Regulus;Database=BD16189;User Id=16189;Password=professorageraldina;";
var Connection = require('tedious').Connection;
var TYPES = require('tedious').TYPES;
  var config = {
    userName: 'BD16189',
    password: 'professorageraldina',
    server: 'Regulus',
  };

  var connection = new Connection(config);

//fazendo a conexão global
 connection.on('connect', function(err) {
    // If no error, then good to go...
      //abrir server
    }
  );

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
const router = express.Router();
router.get('/', (req, res) => res.json({ message: 'Funcionando!' }));
app.use('/', router);

var Request = require('tedious').Request;
router.get('/GetHorarios/:ds&:cod', (req, res) =>{
    console.log(req.params.ds)  ; 
    console.log(req.params.cod)  ; 
    if(req.params.ds && req.params.cod){
        var sql = 'selectHorario_sp';
    request = new Request(sql, function(err, rowCount) {
      if (err) {
        console.log(err);
      } else {
        console.log(rowCount + ' rows');
      }
         
    });
        request.on('row', function(columns) {
      data.push({
          'horarioInicio': columns[0],
          'horarioFim': columns[1]
      })
        console.log("1: "+data);
    })
    var data = [];
    console.log(req.params.ds)  ; 
    console.log(req.params.cod)  ; 
    console.log("2: "+data);
    request.addParameter('diaSemana', TYPES.Int, req.params.ds);
    request.addParameter('codMonitor', TYPES.Int, req.params.cod);
    connection.callProcedure(request);
    console.log("3:"+data);
    res.json(data);
    }
})

    app.listen(port);
    console.log('A API está no ar');