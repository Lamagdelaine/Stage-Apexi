<?php

namespace App\Http\Controllers;

use App\Profil;

/**
 * Class ProfilController
 * @package App\Http\Controllers
 */
class ProfilController extends Controller
{
	public function index() {}
	
	public function creer()
    {
		// code
		return redirect('Profil');
	}
	
	public function show($id)
    {
		$profil = Profil::findOrFail($id)->get();
		
		return view('show_profil')->with('profil', $profil);
	}
}
