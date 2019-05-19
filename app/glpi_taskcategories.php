<?php

/**
 * Create by Vivien as part of his internship
 * for the title developer logiciel level 3
 */

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\DB;

/**
 * DB: table for the intervention
 *
 * Class glpi_taskcategories
 * @package App
 */
class glpi_taskcategories extends Model
{
    protected $table = "glpi_taskcategories";

    /**
     * Actually not use
     *
     */
    public static function getTaskCategories()
    {

    }
}
