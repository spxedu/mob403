<?php
 header('Content-Type: application/json; charset=utf-8');
// echo "API ";
// echo '<pre>';
// print_r( $_GET);
if(!isset($_GET['res']))
    die('Resource notfound');

$file = $_GET['res'];
// kiểm tra tồn tại file
$file_path = __DIR__.'/'.$file.'.php';

if( file_exists(  $file_path   ) )
    require_once $file_path;
else
    die('File notfound: ' . $file );