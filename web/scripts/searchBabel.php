<?php
if(!empty($_POST)){
	$result=shell_exec("java -jar ../../dist/LibraryOfBabel.jar search "+$_POST['text']);
	$result_json=json_decode($result);
}
?>