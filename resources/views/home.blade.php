<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <meta name="viewport"    content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">

    <title>Home</title>

    <link rel="icon" type="image/png" href="img/favicon.ico">

    <!-- Bootstrap itself -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

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
                <a class="dropdown-item" href="#home"><strong>Dashboard</strong></a>
                <a class="dropdown-item" href="billing"><strong>Facturation</strong></a>
                <a class="dropdown-item" href="users"><strong>Equipe Apexi</strong></a>
            </div>
        </div>
    </div>
</nav>

<section class="section" id="head">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-md-offset-1 col-lg-offset-1 text-center">
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
</section>

<div class="container">
     <div class="col-md-24">
         @if(session()->has('ok'))
             <div class="alert alert-success alert-dismissible">{!! session('ok') !!}</div>
         @endif
         <div class="panel panel-primary">
             <div class="panel-heading">
                 <h3 class="panel-title">Techniciens</h3>
             </div>
             <form>
                 <select class="btn btn-primary" name="date">
                     @foreach ($years as $year)
                         <option value="{{ $year->year }}">{{ $year->year }}</option>
                     @endforeach
                     <input id="date" class="btn btn-danger" type="submit" value="Validation" style="margin-left: 1%">
                 </select>
             </form>
             <div class="content table-responsive table-full-width">
                 <table class="table" style="color: #b21f2d">
                     <thead>
                     <tr>
                         <th>Technicien:</th>
                         <th>Jours:</th>
                         <th>Heures:</th>
                         <th>Minutes:</th>
                     </thead>
                     <tbody>
                     @foreach($technicians as $tech)
                         <tr>
                            <td class="text" style="color: #020018"><strong>{!! $tech->tech  !!}</strong></td>
                            <td class="text" style="color: #020014"><strong>{!! $tech->days  !!}</strong></td>
                            <td class="time" style="color: #020011"><strong>{!! $tech->hours !!}</strong></td>
                            <td class="time" style="color: #020011"><strong>{!! $tech->minutes !!}</strong></td>
                         </tr>
                     @endforeach
                     </tbody>
                 </table>
             </div>
         </div>
     </div>
</div>

<!-- Load js libs only when the page is loaded. -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</body>
</html>