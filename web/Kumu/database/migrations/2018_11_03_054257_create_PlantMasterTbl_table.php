<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;

class CreatePlantMasterTblTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('PlantMasterTbl', function(Blueprint $table)
		{
			$table->bigInteger('PlantID', true);
			$table->string('PlantTaxaName');
			$table->string('PlantCommonName')->nullable();
			$table->string('PlantFedStatus', 10);
		});
	}


	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('PlantMasterTbl');
	}

}
