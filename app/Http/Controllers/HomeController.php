<?php

/**
 * Create by Vivien as part of his internship
 * for the title developer logiciel level 3
 */

namespace App\Http\Controllers;

use App\glpi_users;
use App\glpi_tickets;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

/**
 * Class HomeController
 * @package App\Http\Controllers
 */
class HomeController extends Controller
{
    /**
     * Function that displays the name of the technician
     * and its maintenance time in the year
     *
     * @param Request $request
     * @return mixed
     */
    public function showTime(Request $request)
    {
        /**
         * Condition to have the choice
         * of years contained in the DB
         */
        if($request->date != null)
        {
            $startTime = $request->date  . '-01-01';
            $endTime   = $request->date  . '-12-31';
        }else{
            $startTime = date('Y') . '-01-01';
            $endTime   = date('Y') . '-12-31';
        }

        $technicians   = glpi_users::all();

        /**
         * Seconds recovery request according
         * to technicians
         */
        foreach ($technicians as $tech)
        {
            $rawSecondes = DB::table('glpi_tickets')
                ->where('users_id_recipient','=', $tech->id)
                ->whereBetween( 'glpi_tickets.date', [ $startTime , $endTime])
                ->sum('actiontime');

            $tech->setAttribute('rawSecondes', $rawSecondes);
        }

        $techs = glpi_users::getTimeAnnual($startTime, $endTime);

        /**
         * With the recovery of the seconds they are transformed
         * into days hours minutes because php blocks at 24h and
         * Mysql goes from -838: 59: 59 to 838: 59: 59 at the
         * level of the management of the hours outside the year
         * the technicians exceeds these values
         */
        foreach ($techs as $tech)
        {
            $rawSecondes   = $tech->time;
            $dt            = Carbon::now()->addSecond($rawSecondes);
            $dt_old        = Carbon::now();
            $days          = $dt->diffInDays($dt_old);
            $dt            = $dt->subDays($days);
            $hours         = $dt->diffInHours($dt_old);
            $dt            = $dt->subHours($hours);
            $minutes       = $dt->diffInMinutes($dt_old);

            $tech->days    = $days;
            $tech->hours   = $hours;
            $tech->minutes = $minutes;
        }

        $years       = glpi_tickets::getYears();

        $timesMonths = glpi_tickets::getTimeMonth();

        return view('home')
            ->with('years', $years)
            ->with('timesMonths', $timesMonths)
            ->with('technicians', $techs);
    }
}
