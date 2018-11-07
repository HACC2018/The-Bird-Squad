<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('main');
});

//{index} refers to the index in the for loop originating from the first ajax call when filtering
Route::get('image/{formID}/{index}', 'ImageController@GetImage');

Route::post('/', 'MainController@RequestReports');

Route::get('auto/{plantName}', 'MainController@AutoCompletePlantName');

Route::post('api', 'ApiController@FormInsert');

Route::post('apiImage', 'ApiController@ImageInsert');

?>