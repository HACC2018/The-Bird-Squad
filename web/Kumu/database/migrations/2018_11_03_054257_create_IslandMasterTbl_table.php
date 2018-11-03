<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;

class CreateIslandMasterTblTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('IslandMasterTbl', function(Blueprint $table)
		{
			$table->integer('IslandID', true);
			$table->string('IslandName', 50);
			$table->string('IslandNameEquiv', 50);
		});
	}


	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('IslandMasterTbl');
	}

}
