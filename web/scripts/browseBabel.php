<?php
if(!empty($_POST)){
	$result=shell_exec("java -jar ../../dist/LibraryOfBabel.jar move "+$_POST['dimension']+" "+$_POST['area']+" "+$_POST['avenue']+" "+$_POST['building']+" "+$_POST['floor']+" "+$_POST['room']+" "+$_POST['bookcase']+" "+$_POST['shelf']+" "+$_POST['book']+" "+$_POST['page']);
	$result_json=json_decode($result);
}
?>