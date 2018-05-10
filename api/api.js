
const express = require('express');
const app = express();         
const bodyParser = require('body-parser');
const porta = 50169; //porta padrão
const sql = require('tedious');
var datetime = require('node-datetime');
//const connStr = "Server=Regulus;vetorHorariosbase=BD16189;User Id=16189;Password=professorageraldina;";
var conexao = require('tedious').Connection;
var TYPES = require('tedious').TYPES;
  var config = {
    userName: 'BD16189',
    password: 'professorageraldina',
    server: 'Regulus',
  };

  var conexao = new conexao(config);
    var vetorHorarios = [];
//fazendo a conexão global
 conexao.on('connect', function(err) {
    // If no error, then good to go...
      //abrir server
    }
  );

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
const rota = express.router();
rota.get('/', (req, res) => res.json({ message: 'Funcionando!' }));
app.use('/', rota);

var Request = require('tedious').Request;
rota.get('/GetHorarios/:ds&:cod', (req, res) =>{
    
    if(req.params.ds && req.params.cod){
        var sql = 'selectHorario_sp';
    request = new Request(sql, function(err, rowCount) {
      if (err) {
        console.log(err);
      } else {
        
        res.json(vetorHorarios);
          vetorHorarios = [];
      }
         
    });
        
        request.on('row', function(columns) {
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
      vetorHorarios.push({
          'horarioInicio': sI,
          'horarioFim': sF
      })
        
    })
    
    request.addParameter('diaSemana', TYPES.Int, req.params.ds);
    request.addParameter('codMonitor', TYPES.Int, req.params.cod);
    conexao.callProcedure(request);
    
    }
})

    app.listen(porta);
    console.log('A API está no ar');