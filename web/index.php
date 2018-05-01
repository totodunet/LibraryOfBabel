<?php
$text="\"Tout\"";
$response=shell_exec("java -jar \"-Dfile.encoding=ANSI\" ../dist/LibraryOfBabel.jar search ".$text);
echo $response;
?>