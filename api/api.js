
const express = require('express');
const app = express();         
const bodyParser = require('body-parser');
const port = 52189; //porta padrão
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

  

router.get('/GetHorarios/:ds?&cod?', (req, res) =>{
    if(req.params.ds && req.params.cod)
     var sql = "selectHorarios_sp";
    request = new Request(sql, function(err, rowCount) {
      if (err) {
        console.log(err);
      } else {
        console.log(rowCount + ' rows');
      }
    });
    var data = [];
    request.on('row', function(columns) {
      data.push({
          horarioFim: columns[0],
          horarioInicio: columns[1]
      })
        
    });
    request.addParameter('diaSemana', TYPES.Int, ds);
    request.addParameter('codMonitor', TYPES.Int, cod);
    connection.callProcedure(request);
    res.json(data);
    
    
})

    app.listen(port);
    console.log('A API está no ar');