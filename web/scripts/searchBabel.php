<?php
if(!empty($_POST)){
	$result=shell_exec("java -jar ../../dist/LibraryOfBabel.jar search "+$_POST['text']);
	print($result);
}
?>