<?php

/**
 * Create by Vivien as part of his internship
 * for the title developer logiciel level 3
 */

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\DB;

/**
 * DB: table for the intervention tickets
 *
 * Class glpi_tickets
 * @package App
 */
class glpi_tickets extends Model
{
    protected $table = "glpi_tickets";

    protected $fillable =['actiontime'];

    /**
     * Function to take the Years in combobox
     *
     * @return \Illuminate\Support\Collection
     */
    public static function getYears()
    {
        $years = DB::table('glpi_tickets')
                ->select(DB::raw('DISTINCT YEAR(date) as year'))
                ->orderBy('year', 'desc')
                ->get();

        return $years;
    }

    /**
     *  Function that recovers time working
     *
     * @return array
     */
    public static function getTimeMonth()
    {
        $timesMonths = DB::table('glpi_tickets')
                ->select(DB::raw('SEC_TO_TIME(SUM(actiontime)) as time'))
                ->where( 'status', '=', '5')
                ->get();

        return $timesMonths;
    }

    /**
     * function to Export file in .xlsx
     * @return \Illuminate\Support\Collection
     */
    public static function getID()
    {
        $id = DB::table('glpi_tickets')
            ->select ('glpi_tickets.id as numbertickets',
                             'glpi_users.name as tech', 'glpi_entities.name as entity',
                             'glpi_tickets.name as title', 'glpi_tickets.content', 'solvedate',
                DB::raw('SEC_TO_TIME(glpi_tickets.actiontime) as hour'))
            ->join   (  'glpi_entities', 'glpi_entities.id','=',
                       'glpi_tickets.entities_id')
            ->join   (  'glpi_users', 'glpi_users.id','=',
                       'glpi_tickets.users_id_recipient')
            ->where  ( 'glpi_tickets.status', '=', '5')
            ->orderBy( 'glpi_entities.id')
            ->get();

        return $id;
    }
}
