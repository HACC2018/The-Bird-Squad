<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;

class CreateUserTblTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('UserTbl', function(Blueprint $table)
		{
			$table->bigInteger('UID', true);
			$table->string('Username');
			$table->string('Password');
			$table->string('FirstName', 100);
			$table->string('LastName', 100);
		});
	}


	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('UserTbl');
	}

}
