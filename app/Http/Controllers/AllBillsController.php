<?php

/**
 * Create by Vivien as part of his internship
 * for the title developer logiciel level 3
 */

namespace App\Http\Controllers;

use App\glpi_entities;
use App\glpi_tickets;

/**
 * Class AllBillsController
 * @package App\Http\Controllers
 */
class AllBillsController extends Controller
{
    /**
     *  Functiun view all bills
     *
     * @return \Illuminate\Contracts\View\Factory|\Illuminate\View\View
     */
    public function allBills()
    {
        $bills       = glpi_entities::getAllBills();

        $timesMonths = glpi_tickets::getTimeMonth();

        return view('allbills')
            ->with('bills', $bills)
            ->with('timesMonths', $timesMonths);
    }
}
