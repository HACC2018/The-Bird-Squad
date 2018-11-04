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
		
		//Default to all
		$num = -1;

		if(is_numeric($it->filter_island)){
			$num = $it->filter_island;
		}
		
		$resultSet = DB::select('exec FindForm_Filter ?,?,?',[$num, $it->filter_plant_age, $it->filter_plant]);
		$returnSet = array();
		foreach($resultSet as $val) {
			array_push(
				$returnSet, array(
					'FormID' => $val->FormID,
					'TaxaName' => $val->PlantTaxaName,
					'CommonName' => $val->PlantCommonName,
					'PlantType' => $val->PlantType,
					'FedStatus' => $val->PlantFedStatus,
					'LocationNotes' => $val->LocationNotes
				));
		}
		return json_encode($returnSet);
	}
}
