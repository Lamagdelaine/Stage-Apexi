<?php
/**
 * Created by PhpStorm.
 * User: Vivien
 * Date: 02/07/2018
 * Time: 09:58
 */

namespace App\Exports;

use App\glpi_tickets;
use Maatwebsite\Excel\Concerns\FromCollection;
use Maatwebsite\Excel\Concerns\ShouldAutoSize;
use Maatwebsite\Excel\Concerns\WithHeadings;


class BillsExport implements FromCollection, ShouldAutoSize, WithHeadings
{
    /**
     * Export for excel via the MySQL query
     * in the glpi_tickets template
     * @return \Illuminate\Support\Collection
     */
    public function collection()
    {
        $excel = glpi_tickets::getID()->map(function ($item){
            return get_object_vars($item);
        });

        return $excel;
    }

    /**
     * Function that allows to have a header
     * above each column of the excel file
     * @return array
     */
    public function headings(): array
    {
        return [
            'Numéro de ticket',
            'Technicien',
            'Entité',
            'Titre',
            'Contenu',
            'Date de résolution',
            'Temps de maintenance'
        ];
    }
}
