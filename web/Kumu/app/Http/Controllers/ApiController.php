<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use Image;
use DB;
use Log;

class ApiController extends Controller
{
	public function ImageInsert(Request $request){
		ini_set('log_errors_max_len', 0);
		Log::info(print_r($request->post(), true));

		$image = $request->image;

		Log::info(print_r($image, true));
		$imageName = time().'.'.$image->getClientOriginalName();
		Log::info(print_r('image name ' . $imageName, true));
		
		Image::make($image->getRealPath())->resize(350, 350)->save( public_path('/' . $imageName ) );

		Log::info('here4');
		$resultSet = DB::select('exec ImageInsert_proc ?,?,?,?', [
			$request->formid,
			$imageName,
			$request->latitude,
			$request->longitude
		]);
		Log::info('here5');

		return $resultSet[0]->status;
	}

    //Filter with post
	public function FormInsert(Request $request){
        $form = new \stdClass();
		$jsonForm = json_decode($request->json);

		//dateCreated
		$form->dateCreated = NULL;
		if(isset($jsonForm->dateCreated)){
			$form->dateCreated = $jsonForm->dateCreated;
		}

		//wildOrOutplanted
		$form->wildOrOutplanted = 'none';
		if(isset($jsonForm->wildOrOutplanted)){
			$form->wildOrOutplanted = $jsonForm->wildOrOutplanted;
		}

		//taxonName
		$form->taxonName = 'none';
		if(isset($jsonForm->taxonName)){
			$form->taxonName = $jsonForm->taxonName;
		}

		//observerName
		$form->observerName = NULL;
		if(isset($jsonForm->observerName)){
			$form->observerName = $jsonForm->observerName;
		}

		//organizationName
		$form->organizationName = NULL;
		if(isset($jsonForm->organizationName)){
			$form->organizationName = $jsonForm->organizationName;
		}

		//island
		$form->island = '3';
		if(isset($jsonForm->island)){
			switch ($jsonForm->island){
				case 'Hawaii':
					$form->island = 1;
					break;
				case 'Maui':
					$form->island = 2;
					break;
				case 'Oahu':
					$form->island = 3;
					break;
				case 'Kauai':
					$form->island = 4;
					break;
				case 'Molokai':
					$form->island = 5;
					break;
				case 'Lanai':
					$form->island = 6;
					break;
				case 'Niihau':
					$form->island = 7;
					break;
				case 'Kahoolawe':
					$form->island = 8;
					break;
			}
		}

		//areaCode
		$form->areaCode = 'none';
		if(isset($jsonForm->areaCode)){
			$form->areaCode = $jsonForm->areaCode;
		}

		//refSite
		$form->refSite = 'none';
		if(isset($jsonForm->refSite)){
			$form->refSite = $jsonForm->refSite;
		}
		//locationNotes
		$form->locationNotes = NULL;
		if(isset($jsonForm->locationNotes)){
			$form->locationNotes = $jsonForm->locationNotes;
		}

		//numMaturePlants
		$form->numMaturePlants = NULL;
		if(isset($jsonForm->numMaturePlants)){
			$form->numMaturePlants = $jsonForm->numMaturePlants;
		}

		//numImmaturePlants
		$form->numImmaturePlants = NULL;
		if(isset($jsonForm->numImmaturePlants)){
			$form->numImmaturePlants = $jsonForm->numImmaturePlants;
		}
		//numSeedlings
		$form->numSeedlings = NULL;
		if(isset($jsonForm->numSeedlings)){
			$form->numSeedlings = $jsonForm->numSeedlings;
		}

		
		//plantTagged
		$form->plantTagged = 0;
		if(isset($jsonForm->plantTagged)){
			$form->plantTagged = $jsonForm->plantTagged;
		}

		//plantGender
		$form->plantGender = NULL;
		if(isset($jsonForm->plantGender)){
			$form->plantGender = $jsonForm->plantGender;
		}

		//plantHeight
		$form->plantHeight = NULL;
		if(isset($jsonForm->plantHeight)){
			$form->plantHeight = $jsonForm->plantHeight;
		}

		//plantBaseDiameter
		$form->plantBaseDiameter = NULL;
		if(isset($jsonForm->plantBaseDiameter)){
			$form->plantBaseDiameter = $jsonForm->plantBaseDiameter;
		}
		//plantAge
		$form->plantAge = NULL;
		if(isset($jsonForm->plantAge)){
			$form->plantAge = $jsonForm->plantAge;
		}

		//plantReproductive
		$form->plantReproductive = NULL;
		if(isset($jsonForm->plantReproductive)){
			$form->plantReproductive = $jsonForm->plantReproductive;
		}

		//plantVigor
		$form->plantVigor = NULL;
		if(isset($jsonForm->plantVigor)){
			$form->plantVigor = $jsonForm->plantVigor;
		}

		//amountImmatureFruit
		$form->amountImmatureFruit = NULL;
		if(isset($jsonForm->amountImmatureFruit)){
			$form->amountImmatureFruit = $jsonForm->amountImmatureFruit;
		}
		//amountMatureFruit
		$form->amountMatureFruit = NULL;
		if(isset($jsonForm->amountMatureFruit)){
			$form->amountMatureFruit = $jsonForm->amountMatureFruit;
		}

		//amountCuttings
		$form->amountCuttings = NULL;
		if(isset($jsonForm->amountCuttings)){
			$form->amountCuttings = $jsonForm->amountCuttings;
		}

		//amountAirLayers
		$form->amountAirLayers = NULL;
		if(isset($jsonForm->amountAirLayers)){
			$form->amountAirLayers = $jsonForm->amountAirLayers;
		}

		//amountFlowers
		$form->amountFlowers = NULL;
		if(isset($jsonForm->amountFlowers)){
			$form->amountFlowers = $jsonForm->amountFlowers;
		}
		

		//percentVegative
		$form->percentVegative = NULL;
		if(isset($jsonForm->percentVegative)){
			$form->percentVegative = $jsonForm->percentVegative;
		}

		//percentBuds
		$form->percentBuds = NULL;
		if(isset($jsonForm->percentBuds)){
			$form->percentBuds = $jsonForm->percentBuds;
		}

		//percentFlower
		$form->percentFlower = NULL;
		if(isset($jsonForm->percentFlower)){
			$form->percentFlower = $jsonForm->percentFlower;
		}

		//percentImmatureFruit
		$form->percentImmatureFruit = NULL;
		if(isset($jsonForm->percentImmatureFruit)){
			$form->percentImmatureFruit = $jsonForm->percentImmatureFruit;
		}

		//percentMatureFruit
		$form->percentMatureFruit = NULL;
		if(isset($jsonForm->percentMatureFruit)){
			$form->percentMatureFruit = $jsonForm->percentMatureFruit;
		}

		//percentHealthy
		$form->percentHealthy = NULL;
		if(isset($jsonForm->percentHealthy)){
			$form->percentHealthy = $jsonForm->percentHealthy;
		}

		//percentModerate
		$form->percentModerate = NULL;
		if(isset($jsonForm->percentModerate)){
			$form->percentModerate = $jsonForm->percentModerate;
		}

		//percentPoor
		$form->percentPoor = NULL;
		if(isset($jsonForm->percentPoor)){
			$form->percentPoor = $jsonForm->percentPoor;
		}

		//percentDead
		$form->percentDead = NULL;
		if(isset($jsonForm->percentDead)){
			$form->percentDead = $jsonForm->percentDead;
		}

		//percentFullSun
		$form->percentFullSun = NULL;
		if(isset($jsonForm->percentFullSun)){
			$form->percentFullSun = $jsonForm->percentFullSun;
		}

		//percentPartialSun
		$form->percentPartialSun = NULL;
		if(isset($jsonForm->percentPartialSun)){
			$form->percentPartialSun = $jsonForm->percentPartialSun;
		}

		//percentPartialShade
		$form->percentPartialShade = NULL;
		if(isset($jsonForm->percentPartialShade)){
			$form->percentPartialShade = $jsonForm->percentPartialShade;
		}

		//percentDeepShade
		$form->percentDeepShade = NULL;
		if(isset($jsonForm->percentDeepShade)){
			$form->percentDeepShade = $jsonForm->percentDeepShade;
		}

		//observatoryClosure
		$form->observatoryClosure = NULL;
		if(isset($jsonForm->observatoryClosure)){
			$form->observatoryClosure = $jsonForm->observatoryClosure;
		}

		//observatoryHeight
		$form->observatoryHeight = NULL;
		if(isset($jsonForm->observatoryHeight)){
			$form->observatoryHeight = $jsonForm->observatoryHeight;
		}
		//understoryClosure
		$form->understoryClosure = NULL;
		if(isset($jsonForm->understoryClosure)){
			$form->understoryClosure = $jsonForm->understoryClosure;
		}

		//soilDrainage
		$form->soilDrainage = NULL;
		if(isset($jsonForm->soilDrainage)){
			$form->soilDrainage = $jsonForm->soilDrainage;
		}

		//topography
		$form->topography = NULL;
		if(isset($jsonForm->topography)){
			$form->topography = $jsonForm->topography;
		}

		//aspect
		$form->aspect = NULL;
		if(isset($jsonForm->aspect)){
			$form->aspect = $jsonForm->aspect;
		}

		//assocObsSpecies
		$form->assocObsSpecies = NULL;
		if(isset($jsonForm->assocObsSpecies)){
			$form->assocObsSpecies = $jsonForm->assocObsSpecies;
		}

		//assocUndSpecies
		$form->assocUndSpecies = NULL;
		if(isset($jsonForm->assocUndSpecies)){
			$form->assocUndSpecies = $jsonForm->assocUndSpecies;
		}

		//substrate
		$form->substrate = NULL;
		if(isset($jsonForm->substrate)){
			$form->substrate = $jsonForm->substrate;
		}

		//threats
		$form->threats = NULL;
		if(isset($jsonForm->threats)){
			$form->threats = $jsonForm->threats;
		}

		//threatManagementNotes
		$form->threatManagementNotes = NULL;
		if(isset($jsonForm->threatManagementNotes)){
			$form->threatManagementNotes = $jsonForm->threatManagementNotes;
		}

		$resultSet = DB::select('exec FormInsert_proc ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?', [
			$form->dateCreated,
			$form->wildOrOutplanted,
			$form->taxonName,
			$form->observerName,
			$form->organizationName,
			$form->island,
			$form->areaCode,
			$form->refSite,
			$form->locationNotes,
			$form->numMaturePlants,
			$form->numImmaturePlants,
			$form->numSeedlings,
			$form->plantTagged,
			$form->plantGender,
			$form->plantHeight,
			$form->plantBaseDiameter,
			$form->plantAge,
			$form->plantReproductive,
			$form->plantVigor,
			$form->amountImmatureFruit,
			$form->amountMatureFruit,
			$form->amountCuttings,
			$form->amountAirLayers,
			$form->amountFlowers,
			$form->percentVegative,
			$form->percentBuds,
			$form->percentFlower,
			$form->percentImmatureFruit,
			$form->percentMatureFruit,
			$form->percentHealthy,
			$form->percentModerate,
			$form->percentPoor,
			$form->percentDead,
			$form->percentFullSun,
			$form->percentPartialSun,
			$form->percentDeepShade,
			$form->observatoryClosure,
			$form->observatoryHeight,
			$form->understoryClosure,
			$form->soilDrainage,
			$form->topography,
			$form->aspect,
			$form->assocObsSpecies,
			$form->assocUndSpecies,
			$form->substrate,
			$form->threats,
			$form->threatManagementNotes
		]);
		
		if($resultSet[0]->status == 'true'){
			return $resultSet[0]->FormID;
		} else {
			return 'error';
		}

    }
}
