<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <meta name="viewport"    content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">

    <title>Satff Apexi</title>

    <link rel="icon" type="image/png" href="img/favicon.ico">

    <!-- Bootstrap itself -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles -->
    <link rel="stylesheet" href="css/style.css">

    <!-- Fonts -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Wire+One' rel='stylesheet' type='text/css'>
</head>

<nav class="mainmenu">
    <div class="container">
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Menu
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="home"><strong>Dashboard</strong></a>
                <a class="dropdown-item" href="billing"><strong>Facturation</strong></a>
                <a class="dropdown-item" href="#users"><strong>Equipe Apexi</strong></a>
            </div>
        </div>
    </div>
</nav>

<section class="section" id="head">
    <div class="container">
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
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="col-md-5>">
        @if(session()->has('ok'))
            <div class="alert alert-success alert-dismissible">{!! session('ok') !!}</div>
        @endif
        </div>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title" style="color: #1b1e21">Staff Apexi</h3>
            </div>
            <div class="content table-responsive table-full-width">
                <table class="table" style="color: #b21f2d">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nom</th>
                        <th scope="col">Prénom</th>
                        <th scope="col">Technicien</th>
                        <th scope="col">Mobile</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Derniére connexion</th>
                    </tr>
                    </thead>
                    <tbody>
                    @foreach ($users as $user)
                        <tr>
                            <td>{!! $user->id !!}</td>
                            <td class="text" style="color: #020009"><strong>{!! $user->realname   !!}</strong></td>
                            <td class="text" style="color: #020009"><strong>{!! $user->firstname  !!}</strong></td>
                            <td class="text" style="color: #020009"><strong>{!! $user->name  !!}</strong></td>
                            <td class="text" style="color: #020009"><strong>{!! $user->mobile     !!}</strong></td>
                            <td class="text" style="color: #020009"><strong>{!! $user->phone !!}</strong></td>
                            <td class="text" style="color: #020009"><strong>{!! $user->last_login !!}</strong></td>
                        </tr>
                    @endforeach
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>


<!-- Load js libs only when the page is loaded. -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</body>
</html>