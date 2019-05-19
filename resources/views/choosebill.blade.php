<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <meta name="viewport"    content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">

    <title>Choose-Bill</title>
    <link rel="icon" type="image/png" href="img/favicon.ico">

    <!-- Bootstrap itself -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles -->
    <link rel="stylesheet" href="css/style.css">

    <!-- Fonts -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Wire+One' rel='stylesheet' type='text/css'>

</head>
<body id="page">
<nav class="mainmenu">
    <div class="container">
        <div id="menu" class="dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Menu
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="home"><strong>Dashboard</strong></a>
                <a class="dropdown-item" href="billing"><strong>Facturation</strong></a>
                <a class="dropdown-item" href="users"><strong>Equipe Apexi</strong></a>
            </div>
        </div>
    </div>
</nav>

<section class="section" id="head">
    <div id="time" class="container">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-md-offset-1 col-lg-offset-1 text-center">
                <div class="header">
                    <h4 class="title" style="color: #020009"><strong>Nombre d'heures ce mois-ci</strong></h4>
                    <p class="category" style="color: #020009">Source: GLPI</p>
                </div>
                <div class="content">
                    @if(session()->has('ok'))
                        <div class="alert alert-success alert-dismissible">{!! session('ok') !!}</div>
                    @endif
                    <div id="recap_glpi">
                        @foreach($timesMonths as $timeMonth)
                            <h4 class="title" style="color: #020009"><strong>{!! $timeMonth->time !!}</strong></h4>
                        @endforeach
                    </div>
                    <a href="javascript:window.print();"><button id="print" class="btn btn-info" type="submit" value="Validation" style="margin-left: 1%">Imprimer</button></a>
                </div>
            </div>
        </div>
    </div>
</section>

<div id="view" class="col-md-12">
    @if(session()->has('ok'))
        <div class="alert alert-success alert-dismissible">{!! session('ok') !!}</div>
    @endif
    <div class="panel panel-primary">
        <div  id="title" class="panel-heading">
            <h3 class="panel-title" style="color: #040505">Facturation par client</h3>
        </div>
        <form id="entity" name="choice">
            <div>
                <select class="btn btn-primary" name="entity">
                    @foreach ($identitys as $identity)
                        <option id="box" value="{{ $identity->entity }}">{{ $identity->entity }}</option>
                    @endforeach
                    <input id="id" class="btn btn-info" type="submit" value="Validation" style="margin-left: 1%">
                </select>
            </div>
        </form>
        <div class="content table-responsive table-full-width" style="color: #b21f2d">
            <table class="table">
                <thead>
                    <tr>
                        <th>Numéro</th>
                        <th>Technicien</th>
                        <th>Entité</th>
                        <th>Titre</th>
                        <th>Contenu</th>
                        <th>Catégorie</th>
                        <th>Date</th>
                        <th>Heures</th>
                    </tr>
                </thead>
                <tbody>
                @foreach ($chooses as $choose)
                    <tr>
                        <td class="text" style="color: #020009"><strong>{!! $choose->numbertickets !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $choose->tech          !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $choose->entity        !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $choose->title         !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $choose->content       !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $choose->category      !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $choose->solvedate     !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $choose->hour          !!}</strong></td>
                    </tr>
                @endforeach
                </tbody>
            </table>
        </div>
        <div class="container">
            <div id="cadremd6" class="col-md-24">
                <h4 class="optionheures" style="color: #020009"><strong>Total des heures TeamViewer :
                @foreach($teammaints as $teammaint)
                    {!! $teammaint->teamtime !!}</strong></h4>
                @endforeach
            </div>
        </div>
        <div class="container">
            <div id="cadremd6" class="col-md-24" style="page-break-before: avoid">
                <h3 class="optionheures" style="color: #020009"><strong>Total des heures toutes interventions :
                @foreach($hourbills as $hourbill)
                    {!! $hourbill->totaltime !!}</strong></h3>
                @endforeach
            </div>
        </div>
    </div>
</div>

<!-- Load js libs only when the page is loaded. -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</body>
</html>