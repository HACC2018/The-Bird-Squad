<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use DB;

class ImageController extends Controller
{
	public function GetImage($formID, $index){
		$resultSet = DB::select('exec GetImageFromForm ?',[$formID]);

		$returnSet = array('index' => $index);
		foreach($resultSet as $val) {
			array_push(
				$returnSet, array(
					'ImageName' => $val->ImageName,
					'Latitude' => $val->Latitude,
					'Longitude' => $val->Longitude
				));
		}
		return json_encode($returnSet);
	}
}
