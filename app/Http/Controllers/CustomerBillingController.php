<?php

/**
 * Create by Vivien as part of his internship
 * for the title developer logiciel level 3
 */

namespace App\Http\Controllers;

use App\glpi_entities;
use App\glpi_tickettasks;

/**
 * Class CustomerBillingController
 * @package App\Http\Controllers
 */
class CustomerBillingController extends Controller
{
    /**
     * Test function for future scalability
     */
    public function customerBilling()
    {
        $entities   = glpi_entities::all();

        $tikettasks = glpi_tickettasks::all();

        dd($entities, $tikettasks);
    }
}
