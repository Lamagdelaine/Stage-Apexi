<?php

/**
 * Create by Vivien as part of his internship
 * for the title developer logiciel level 3
 */

namespace App\Http\Controllers;

use App\glpi_tickets;

/**
 * Class BillingController
 * @package App\Http\Controllers
 */
class BillingController extends Controller
{
    /**
     * Function that displays its maintenance time in the months
     *
     * @return \Illuminate\Contracts\View\Factory|\Illuminate\View\View
     */
    public function homeBilling()
    {
        $timesMonths = glpi_tickets::getTimeMonth();

        return view('billing')
            ->with('timesMonths', $timesMonths);
    }
}
