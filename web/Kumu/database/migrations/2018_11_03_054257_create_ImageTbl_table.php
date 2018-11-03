<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;

class CreateImageTblTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('ImageTbl', function(Blueprint $table)
		{
			$table->bigInteger('ImageID', true);
			$table->bigInteger('FormID');
			$table->string('ImageName', 50);
			$table->decimal('Latitude', 10, 5);
			$table->decimal('Longitude', 10, 5);
		});
	}


	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('ImageTbl');
	}

}
