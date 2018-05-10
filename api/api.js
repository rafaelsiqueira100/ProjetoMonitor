
const express = require('express');
const app = express();         
const bodyParser = require('body-parser');
const port = 50169; //porta padrão
const sql = require('tedious');
var datetime = require('node-datetime');
//const connStr = "Server=Regulus;Database=BD16189;User Id=16189;Password=professorageraldina;";
var Connection = require('tedious').Connection;
var TYPES = require('tedious').TYPES;
  var config = {
    userName: 'BD16189',
    password: 'professorageraldina',
    server: 'Regulus',
  };

  var connection = new Connection(config);
    var data = [];
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
    console.log(req.params.cod)  ; 
    console.log(req.params.ds)  ; 
    if(req.params.ds && req.params.cod){
        var sql = 'selectHorario_sp';
    request = new Request(sql, function(err, rowCount) {
      if (err) {
        console.log(err);
      } else {
        console.log(rowCount + ' rows');
        console.log("3:"+data);
        res.json(data);
          data = [];
      }
         
    });
        
        request.on('row', function(columns) {
            console.log('entrou no loop');
            var hI = columns[0].value;
            var hF = columns[1].value;
            hI = String(hI);
            hF = String(hF);
            var dI = datetime.create(hI);
            var dF = datetime.create(hF);
            var sI = dI.format('H:M:S');
            var sF = dF.format('H:M:S');
            var hI = String(Number(sI.substring(0,2))+2);
            var hF = String(Number(sF.substring(0,2))+2);
            if(hI.length == 1)
                hI = '0'+ hI;
            if(hF.length == 1)
                hF = '0'+ hF;
            sI = hI + sI.substring(2,8);
            sF = hF + sF.substring(2,8);
      data.push({
          'horarioInicio': sI,
          'horarioFim': sF
      })
            console.log(sF);
           //.format('H:M:S')
            console.log(sI);
        
    })
    
    console.log(req.params.ds)  ; 
    console.log(req.params.cod)  ; 
    console.log("2: "+data);
    request.addParameter('diaSemana', TYPES.Int, req.params.ds);
    request.addParameter('codMonitor', TYPES.Int, req.params.cod);
    connection.callProcedure(request);
    
    }
})

    app.listen(port);
    console.log('A API está no ar');