<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/
Route::get('/', function () {
    return view('welcome');
});


Route::get ('/home', 'HomeController@showTime');
Route::post('/home/{date}', 'HomeController@showTime');

Route::get ('/users', 'UsersController@list');

Route::get ('/billing', 'BillingController@homeBilling');

Route::get ('/allbills', 'AllBillsController@allBills');

Route::get ('/print', 'PrintController@printBills');

Route::get ('/choosebill', 'ChooseBillController@chooseBill');
Route::post('/choosebill/{entity}', 'ChooseBillController@chooseBill');

Route::get ('/export', 'ExportController@exportBilling');

Route::get ('/entities', 'CustomerBillingController@customerBilling');

