<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use DB;
use App\Form;
use App\Image;
use App\IslandMaster;
use App\Plants;
use App\User;

class MainController extends Controller
{
	//Filter with post
	public function RequestReports(Request $request){
		$it = json_decode($request->json);
		

		$res = Form::where(function($query) use ($it){
				$query->where([
				['IslandID', '=', $it->filter_island],
				['Age', '=', $it->filter_age]
			]);
		})->where(function($query) use ($it){
				$query->where('PlantTaxaName', '=', $it->filter_plant)->orWhere('PlantTaxaName', '=', $it->filter_plant);
		})->get();
		return 'ok';
	}
}
