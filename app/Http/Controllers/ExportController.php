<?php

/**
 * Create by Vivien as part of his internship
 * for the title developer logiciel level 3
 */

namespace App\Http\Controllers;

use App\Exports\BillsExport;
use Input;
use Excel;

/**
 * Class ExportController
 * @package App\Http\Controllers
 */
class ExportController extends Controller
{
    public function exportBilling()
    {
       return Excel::download( new BillsExport, 'facturation.xlsx');
    }
}
