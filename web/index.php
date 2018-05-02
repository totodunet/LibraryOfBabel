<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="http://www.piproject.org/decimals/js/convert_ascii_decimal.js"></script>
<script type="text/javascript">
	function dec_to_ascii(nbr){
		var chaine="";
		for(var i=0;i<nbr.length;i+=10)
			chaine+=String.fromCharCode(nbr[i]+nbr[i+1]+nbr[i+2]+nbr[i+3]+nbr[i+4]+nbr[i+5]+nbr[i+6]+nbr[i+7]+nbr[i+8]+nbr[i+9]);
		return chaine;
	}
	$(document).ready(function(){
		$("#mot").text(dec_to_ascii($("#mot").text()));
	});
</script>
</head>
<body>
<?php
$text="\"Thomas.\"";
$response=shell_exec("java -jar ../dist/LibraryOfBabel.jar search ".$text);
$response=json_decode($response,true,512,JSON_BIGINT_AS_STRING);
echo "<p id='mot'>".$response["pageContent"]."</p>";
?>
</body>
</html>