<?php

/**
 * Create by Vivien as part of his internship
 * for the title developer logiciel level 3
 */

namespace App\Http\Controllers;

use App\glpi_entities;
use App\glpi_tickets;
use Illuminate\Http\Request;

/**
 * Class ChooseBillController
 * @package App\Http\Controllers
 */
class ChooseBillController extends Controller
{
    /**
     *  Function view bill with entity
     *
     * @param Request $request
     * @return \Illuminate\Contracts\View\Factory|\Illuminate\View\View
     */
    public function chooseBill(Request $request)
    {
        if($request->entity != null)
        {
            $id = $request->entity.'' ;
        }else{
            $id = '';
        }

        $chooses     = glpi_entities::getChooseBills($id);

        $hourbills   = glpi_entities::getHourBill($id);

        $teammaints  = glpi_entities::getTeamMaintenance($id);

        $identitys   = glpi_entities::getEntity();

        $timesMonths = glpi_tickets::getTimeMonth();

        return view('choosebill')
            ->with('chooses', $chooses)
            ->with('hourbills', $hourbills)
            ->with('teammaints', $teammaints)
            ->with('identitys', $identitys)
            ->with('timesMonths', $timesMonths);
    }
}
