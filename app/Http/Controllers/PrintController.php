<?php

/**
 * Create by Vivien as part of his internship
 * for the title developer logiciel level 3
 */

namespace App\Http\Controllers;

use App\glpi_entities;

/**
 * Class PrintController
 * @package App\Http\Controllers
 */
class PrintController extends Controller
{
    /**
     *  Functiun view all bills for print
     *
     * @return \Illuminate\Contracts\View\Factory|\Illuminate\View\View
     */
    public function printBills()
    {
        $prints = glpi_entities::getAllBills();

        return view('print')
            ->with('prints', $prints);
    }
}
