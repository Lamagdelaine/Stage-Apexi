<?php

/**
 * Create by Vivien as part of his internship
 * for the title developer logiciel level 3
 */

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\DB;

/**
 * DB: table for the users (technician)
 *
 * Class glpi_users
 * @package App
 */
class glpi_users extends Model
{
    protected $table = "glpi_users";

    protected $fillable = ['realname', 'firstname', 'mobile', 'last_login', 'phone'];

    /**
     * Function to take in the DB: name, second and that puts it in hour
     *
     * @param $startTime
     * @param $endTime
     * @return \Illuminate\Support\Collection|string
     */
    public static function  getTimeAnnual($startTime, $endTime)
    {
        $times = DB::table    (  'glpi_users')
                ->select      ( 'glpi_users.name as tech',
                          DB::raw('SUM(glpi_tickets.actiontime)as time'))
                ->join        (   'glpi_tickets', 'glpi_users.id','=',
                                 'glpi_tickets.users_id_recipient')
                ->whereBetween(  'glpi_tickets.date', [ $startTime , $endTime])
                ->groupBy     ( 'glpi_users.id')
                ->orderBy     (  'time', 'desc')
                ->get();

        return $times;
    }
}
