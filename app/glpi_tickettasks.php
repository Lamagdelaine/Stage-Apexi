<?php

/**
 * Create by Vivien as part of his internship
 * for the title developer logiciel level 3
 */

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\DB;

/**
 *
 * Class glpi_tickettasks
 * @package App
 */
class glpi_tickettasks extends Model
{
    protected $table = "glpi_tickettasks";

    /**
     * Not used but planned for future evolutions
     */
    public static function getTicketTasks()
    {
        $test = DB::select('SELECT glpi_tickets.id as numbertickets, 
                                    glpi_users.name as tech, glpi_entities.name as entity, 
                                    glpi_tickets.name as title, glpi_tickets.content as content, 
                                    glpi_taskcategories.name as category, solvedate, 
                                    SEC_TO_TIME(glpi_tickets.actiontime) as hour, 
                                    glpi_taskcategories.id as idcat
                                    FROM glpi_tickets, glpi_entities, glpi_users, glpi_tickettasks, glpi_taskcategories
                                    WHERE glpi_tickets.entities_id=glpi_entities.id
                                    AND glpi_users.id=glpi_tickets.users_id_recipient
                                    AND glpi_tickettasks.tickets_id=glpi_tickets.id 
                                    AND glpi_tickettasks.taskcategories_id=glpi_taskcategories.id
                                    AND status=5
                                    ORDER BY glpi_entities.name');

        return $test;
    }
}
