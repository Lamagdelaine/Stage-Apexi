<?php

/**
 * Create by Vivien as part of his internship
 * for the title "developer logiciel level 3"
 */

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\DB;

/**
 * DB: table for the customers
 *
 * Class glpi_entities
 * @package App
 */
class glpi_entities extends Model
{
    protected $table = "glpi_entities";

     /**
     * Function to choose the entity with tickets status (status resolved)
     *
     * @return \Illuminate\Support\Collection
     */
    public static function getEntity()
    {
        $entity = DB::table(  'glpi_entities')
                ->select   ('glpi_entities.name as entity', 'glpi_entities.id as identity')
                ->join     (  'glpi_tickets', 'glpi_entities.id','=',
                             'glpi_tickets.entities_id')
                ->where    ( 'glpi_tickets.status','=','5')
                ->groupBy  ('glpi_entities.id')
                ->orderBy  ( 'glpi_entities.name', 'asc')
                ->get();

        return $entity;
    }

    /**
     *  Function : total hours of an entity select
     *
     * @param $id
     * @return \Illuminate\Support\Collection
     */
    public static function getHourBill($id)
    {
        $hourbills = DB::table('glpi_entities')
                ->select(DB::raw('SEC_TO_TIME(SUM(actiontime)) as totaltime'))
                ->join  (  'glpi_tickets', 'glpi_entities.id','=',
                          'glpi_tickets.entities_id')
                ->where ('glpi_entities.name', '=', [$id])
                ->where ('glpi_tickets.status', '=', '5')
                ->get();

        return $hourbills;
    }

    /**
     * Function : total teamviewer hours of an entity select
     *
     * @param $id
     * @return array
     */
    public static function getTeamMaintenance($id)
    {
        $teammaints = DB::select('SELECT SEC_TO_TIME(SUM(glpi_tickets.actiontime)) as teamtime
                                       FROM glpi_tickets, glpi_entities, glpi_tickettasks
                                       WHERE glpi_tickets.entities_id=glpi_entities.id
                                       AND glpi_tickettasks.tickets_id=glpi_tickets.id
                                       AND glpi_entities.name = :id
                                       AND status=5
                                       AND (glpi_tickettasks.taskcategories_id=7) 
                                       + (glpi_tickettasks.taskcategories_id=5)',['id' => $id]);

        return $teammaints;
    }

    /**
     * Function that has been retrieved all the intervention info from all the clients
     *
     * @return array
     */
    public static function getAllBills()
    {
        $taskbills = DB::select('SELECT glpi_entities.id as id, glpi_tickets.id as numbertickets, 
                                      glpi_users.name as tech, glpi_entities.name as entity, 
                                      glpi_tickets.name as title, glpi_tickets.content, 
                                      glpi_taskcategories.name as category, 
                                      solvedate, SEC_TO_TIME(glpi_tickets.actiontime) as hour                                                                          
                                      FROM  glpi_entities, glpi_tickets, glpi_users, glpi_tickettasks, 
                                      glpi_taskcategories
                                      WHERE glpi_tickets.entities_id=glpi_entities.id
                                      AND glpi_users.id=glpi_tickets.users_id_recipient
                                      AND glpi_tickettasks.tickets_id=glpi_tickets.id
                                      AND glpi_tickettasks.taskcategories_id=glpi_taskcategories.id
                                      AND status = 5
                                      ORDER BY glpi_entities.name');

       return $taskbills;
    }

    /**
     * Function to choose the interventions of an entity
     *
     * @param $id
     * @return array
     */
    public static function getChooseBills($id)
    {
        $choosebills = DB::select('SELECT status, glpi_tickets.id as numbertickets, 
                                        glpi_users.name as tech, glpi_entities.name as entity, 
                                        glpi_tickets.name as title,
                                        glpi_tickets.content, glpi_taskcategories.name as category, 
                                        solvedate, SEC_TO_TIME(glpi_tickets.actiontime) as hour
                                        FROM glpi_tickets, glpi_entities, glpi_users, 
                                        glpi_tickettasks, glpi_taskcategories
                                        WHERE glpi_tickets.entities_id=glpi_entities.id
                                        AND glpi_users.id=glpi_tickets.users_id_recipient
                                        AND glpi_tickettasks.tickets_id=glpi_tickets.id
                                        AND glpi_tickettasks.taskcategories_id=glpi_taskcategories.id
                                        AND glpi_entities.name = :id
                                        AND status=5', ['id' => $id]);

        return $choosebills;
    }
}
