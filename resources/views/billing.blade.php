<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <meta name="viewport"    content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">

    <title>Billing</title>

    <link rel="icon" type="image/png" href="img/favicon.ico">

    <!-- Bootstrap itself -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles -->
    <link rel="stylesheet" href="css/style.css">

    <!-- Fonts -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Wire+One' rel='stylesheet' type='text/css'>
</head>

<body class="theme-invert">
<nav class="mainmenu">
    <div class="container">
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Menu
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="home"><strong>Dashboard</strong></a>
                <a class="dropdown-item" href="#billing"><strong>Facturation</strong></a>
                <a class="dropdown-item" href="users"><strong>Equipe Apexi</strong></a>
            </div>
        </div>
    </div>
</nav>
</body>
<section class="section" id="head">
    <div class="container">
           <div class="row">
               <div class="col-md-48 text-center">
                   <div class="header">
                       <h4 class="title"><strong>Nombre d'heures ce mois-ci</strong></h4>
                       <p class="category">Source: GLPI</p>
                   </div>
                   <div class="content">
                       @if(session()->has('ok'))
                           <div class="alert alert-success alert-dismissible">{!! session('ok') !!}</div>
                       @endif
                       <div id="recap_glpi">
                           @foreach($timesMonths as $timeMonth)
                               <h4 class="title"><strong>{!! $timeMonth->time !!}</strong></h4>
                           @endforeach
                       </div>
                   </div>
               </div>
           </div>
    </div>

    <div class="container">
        <div class="col-sm-4 ">
            <div class="thumbnail">
                <img src="img/img7.jpg" alt="">
                <div class="caption">
                    <h3>Facturation Client</h3>
                    <p><a href="choosebill" class="btn btn-info" role="button">Afficher</a></p>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="thumbnail">
                <img src="img/img6.jpg" alt="">
                <div class="caption">
                    <h3>Facturation Globale</h3>
                    <p><a href="allbills" class="btn btn-danger" role="button">Afficher</a></p>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="thumbnail">
                <img src="img/img13.jpg" alt="">
                <div class="caption">
                    <h3>Exportation Excel</h3>
                    <p><a href="export" class="btn btn-success" role="button">Exporter</a></p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Load js libs only when the page is loaded. -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</body>
</html>