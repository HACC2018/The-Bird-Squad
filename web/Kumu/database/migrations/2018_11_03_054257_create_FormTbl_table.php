<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;

class CreateFormTblTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('FormTbl', function(Blueprint $table)
		{
			$table->bigInteger('FormID', true);
			$table->bigInteger('UID');
			$table->string('PlantType', 50);
			$table->string('PlantTaxaName');
			$table->integer('IslandID');
			$table->string('PopulationReferenceArea', 10);
			$table->string('PopulationReferenceSite', 10);
			$table->string('LocationNotes')->nullable();
			$table->integer('NumberMaturePlants')->nullable();
			$table->integer('NumberImmaturePlants')->nullable();
			$table->integer('NumberSeedlings')->nullable();
			$table->boolean('PlantTagged')->nullable();
			$table->string('Sex', 50)->nullable();
			$table->decimal('HeightInMeters', 10, 4)->nullable();
			$table->decimal('BasalDiameterCM', 10, 4)->nullable();
			$table->string('Age', 50)->nullable();
			$table->string('ReproductiveStatus', 50)->nullable();
			$table->string('Vigor', 50)->nullable();
			$table->integer('AmountImmatureFruitCollected')->nullable();
			$table->integer('AmountMatureFruitCollected')->nullable();
			$table->integer('AmountCuttingsCollected')->nullable();
			$table->integer('AmountAirLayersCollected')->nullable();
			$table->integer('AmountFlowersCollected')->nullable();
			$table->integer('PercPlantsVegetative')->nullable();
			$table->integer('PercPlantsFlowerBuds')->nullable();
			$table->integer('PercPlantsInFlower')->nullable();
			$table->integer('PercPlantsImmatureFruit')->nullable();
			$table->integer('PercPlantsMatureFruit')->nullable();
			$table->integer('PercPlantsHealthy')->nullable();
			$table->integer('PercPlantsModerate')->nullable();
			$table->integer('PercPlantsPoor')->nullable();
			$table->integer('PercPlantsDead')->nullable();
			$table->integer('PercPlantsFullSun')->nullable();
			$table->integer('PercPlantsPartialSun')->nullable();
			$table->integer('PercPlantsPartialShade')->nullable();
			$table->integer('PercPlantsDeepShade')->nullable();
			$table->string('OverstoryClosure', 80)->nullable();
			$table->string('OverstoryHeight', 80)->nullable();
			$table->string('UnderstoryClosure', 80)->nullable();
			$table->string('SoilDranage', 80)->nullable();
			$table->string('Topography', 50)->nullable();
			$table->string('Aspect', 20)->nullable();
			$table->string('AssociatedOverstorySpecies', 100)->nullable();
			$table->string('AssociatedUnderstorySpecies', 100)->nullable();
			$table->string('Substrate', 50)->nullable();
			$table->string('Threats')->nullable();
			$table->string('ThreatNotes', 500)->nullable();
		});
	}


	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('FormTbl');
	}

}
