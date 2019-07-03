<#macro boilerplate>
<!doctype html>
<html lang="pt-BR">
    <!--
        Dashboard gerado pela aplicação IFPA-DashGen
        Trabalho de Conclusão de Curso - Graduação em Análise e Desenvolvimento de Sistemas
        Aluno Glauber Matteis Gadelha
        Turma C796NQ
        Ano 2019-1
    -->
  <head>
        <title>${dadosUsr.nomeDashboard}</title>
        <!-- MetaTags requeridas para definição da janela responsiva do Bootstrap -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!--CSS do Bootstrap e do DC.js -->
        <!-- Remarcado por conflito com atributos do css do dc.css em 2019-02-09
        <link rel="stylesheet" href="css/bootstrap.css"> -->
        <link rel="stylesheet" href="css/dc.css">

  </head>
  <body>
    <div class="container-fluid">
    
    <div class="row">
        <h1 align="center">${dadosUsr.nomeDashboard}</h1>
         <h3 align="center" id="dc-data-count"></h3> 
    </div>

    <!-- Div para renderizar tabela dinâmica-->
          
    <div class="row">
        
        <!-- Div para renderizar gráfico Pizza -->
        <div class="col-xs-12 col-md-4 col-lg-4" id="ifpagen-graph1"></div>
        <!-- Div para renderizar gráfico Barras -->
        <div class="col-xs-12 col-md-4 col-lg-4" id="ifpagen-graph2"></div>
        <!-- Div para renderizar gráfico Linhas -->
        <div class="col-xs-12 col-md-4 col-lg-4" id="ifpagen-graph3"></div>
    </div>
    
    <table class="table table-striped table-inverse table-responsive" id="ifpagen-table"></table>
</div>


    <p>Gerado automaticamente pelo sistema IFPA-DashGen</p>  
    <!-- Javascript necessário para o Bootstrap 4 e para os gráficos -->

    <!-- <script src="js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="js/bootstrap.js" type="text/javascript"></script> -->
    <script src="js/crossfilter.js" type="text/javascript"></script>
    <script src="js/d3.js" type="text/javascript"></script>
    <script src="js/dc.js" type="text/javascript"></script>
    <script type="text/javascript">
        <#nested>
    </script>
  </body>
</html>
</#macro>