<?php

/**
 * Create by Vivien as part of his internship
 * for the title developer logiciel level 3
 */

namespace App\Http\Controllers;

use App\glpi_tickets;
use App\glpi_users;

/**
 * Class UsersController
 * @package App\Http\Controllers
 */
class UsersController extends Controller
{
     /**
     * Function that lists of the technician
     *
     * @return \Illuminate\Contracts\View\Factory|\Illuminate\View\View
     */
    public function list()
    {
        $users       = glpi_users::all();

        $timesMonths = glpi_tickets::getTimeMonth();

        return view('users')
            ->with('users',$users)
            ->with('timesMonths', $timesMonths);
    }
}
