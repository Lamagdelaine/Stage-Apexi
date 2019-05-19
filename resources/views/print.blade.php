<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <meta name="viewport"    content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">

    <title>All-Bills</title>

    <link rel="icon" type="image/png" href="img/favicon.ico">

    <!-- Bootstrap itself -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles -->
    <link rel="stylesheet" href="css/style.css">

    <!-- Fonts -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Wire+One' rel='stylesheet' type='text/css'>
</head>

<body class="theme-invert" onload="window.print();">

<nav class="mainmenu">
    <div class="container">
        <div id="menu" class="dropdown">
            <a href="allbills"><button id="print" class="btn btn-info" type="submit" value="Validation">Retour</button></a>
        </div>
    </div>
</nav>

<div id="view" class="col-md-12">
    @if(session()->has('ok'))
        <div class="alert alert-success alert-dismissible">{!! session('ok') !!}</div>
    @endif
    <div class="panel panel-primary">
        <div id="page" class="content table-responsive table-full-width" style="color: #b21f2d">
            <table class="table">
                <thead>
                <tr>
                    <th>Numéro</th>
                    <th>Entité</th>
                    <th>Technicien</th>
                    <th>Titre</th>
                    <th>Contenu</th>
                    <th>Catégorie</th>
                    <th>Date</th>
                    <th>Heures</th>
                </tr>
                </thead>
                <tbody>
                @for($i =0; $i<count($prints);$i++)
                    @php
                        $print = $prints[$i];

                        $count = count($prints)-1;

                        if($i === $count){
                            $next = null;
                        }else{
                            $next = $prints[$i+1];
                        }
                    @endphp
                    <tr>
                        <td class="text" style="color: #020009"><strong>{!! $print->numbertickets !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $print->entity        !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $print->tech          !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $print->title         !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $print->content       !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $print->category      !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $print->solvedate     !!}</strong></td>
                        <td class="text" style="color: #020009"><strong>{!! $print->hour          !!}</strong></td>
                    </tr>
                    @php
                        if(is_null($next)){
                                $teammaints = DB::select('SELECT SEC_TO_TIME(SUM(glpi_tickets.actiontime)) as teamtime
                                                         FROM glpi_tickets, glpi_entities, glpi_tickettasks
                                                         WHERE glpi_tickets.entities_id=glpi_entities.id
                                                         AND glpi_tickettasks.tickets_id=glpi_tickets.id
                                                         AND glpi_entities.id = :id
                                                         AND status=5
                                                         AND (glpi_tickettasks.taskcategories_id=7)
                                                         + (glpi_tickettasks.taskcategories_id=5)',['id' => $print->id]);

                                $hourbills = DB::table('glpi_entities')
                                      ->select(DB::raw('SEC_TO_TIME(SUM(actiontime)) as totaltime'))
                                      ->join  (  'glpi_tickets', 'glpi_entities.id','=',
                                                 'glpi_tickets.entities_id')
                                      ->where ('glpi_entities.id', '=', [$print->id])
                                      ->where ('glpi_tickets.status', '=', '5')
                                      ->get();
                    @endphp
                    <tr style="height: 20px;"></tr>
                    <th><h6>Maintenance Teamviewer:</h6></th>
                    @foreach($teammaints as $teammaint)
                        <th class="text" style="color: #020009"><h6><strong>{!! $teammaint->teamtime !!}</strong></h6></th>
                        </tr>
                    @endforeach
                    <tr style="height: 20px;"></tr>
                    <th><h6>Temps total:</h6></th>
                    @foreach($hourbills as $hourbill)
                        <th class="text" style="color: #020009"><h6><strong>{!! $hourbill->totaltime !!}</strong></h6></th>
                        </tr>
                        <tr id="page_break"></tr>
                    @endforeach
                    @php
                        }elseif($next->id != $print->id){

                            $teammaints = DB::select('SELECT SEC_TO_TIME(SUM(glpi_tickets.actiontime)) as teamtime
                                                      FROM glpi_tickets, glpi_entities, glpi_tickettasks
                                                      WHERE glpi_tickets.entities_id=glpi_entities.id
                                                      AND glpi_tickettasks.tickets_id=glpi_tickets.id
                                                      AND glpi_entities.id = :id
                                                      AND status=5
                                                      AND (glpi_tickettasks.taskcategories_id=7)
                                                      + (glpi_tickettasks.taskcategories_id=5)',['id' => $print->id]);

                            $hourbills = DB::table('glpi_entities')
                                  ->select(DB::raw('SEC_TO_TIME(SUM(actiontime)) as totaltime'))
                                          ->join  (  'glpi_tickets', 'glpi_entities.id','=',
                                                     'glpi_tickets.entities_id')
                                          ->where ('glpi_entities.id', '=', [$print->id])
                                          ->where ('glpi_tickets.status', '=', '5')
                                          ->get();

                    @endphp
                    <tr style="height: 20px;"></tr>
                        <th>Maintenance Teamviewer:</th>
                    @foreach($teammaints as $teammaint)
                        <th class="text" style="color: #020009"><strong>{!! $teammaint->teamtime !!}</strong></th>
                    </tr>
                    @endforeach
                    <tr style="height: 20px;"></tr>
                        <th>Temps total:</th>
                    @foreach($hourbills as $hourbill)
                        <th class="text" style="color: #020009;"><strong>{!! $hourbill->totaltime !!}</strong></th>
                    </tr>
                    @endforeach
                    <tr id="page_break" style="height: 40px"></tr>
                    <tr>
                        <th id="table">Numéro</th>
                        <th id="table">Entité</th>
                        <th id="table">Technicien</th>
                        <th id="table">Titre</th>
                        <th id="table">Contenu</th>
                        <th id="table">Catégorie</th>
                        <th id="table">Date</th>
                        <th id="table">Heures</th>
                    </tr>
                    @php
                        }
                    @endphp
                @endfor
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Load js libs only when the page is loaded. -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</body>
</html>