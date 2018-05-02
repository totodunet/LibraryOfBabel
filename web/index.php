<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function dec_to_ascii(nbr){
		var chaine="";
		for(var i=0;i<nbr.length;i+=3)
			chaine+=String.fromCharCode(nbr[i]+nbr[i+1]+nbr[i+2]/*+nbr[i+3]+nbr[i+4]+nbr[i+5]+nbr[i+6]+nbr[i+7]+nbr[i+8]+nbr[i+9]*/);
		return chaine;
	}
	function ascii_to_dec(str){
		var nombre="";
		var digit;
		for(var i=0;i<str.length;i++){
			digit=str.charCodeAt(i).toString();
			nombre+="0".repeat(10-digit.length)+digit;
		}
		return nombre;
	}
	$(document).ready(function(){
		$("#mot").text(dec_to_ascii($("#mot").text()));
		$("#mot").text(dec_to_ascii("776876977077177"));
	});
</script>
</head>
<body>
<?php
$text="\"1\"";
$response=shell_exec("java -jar ../dist/LibraryOfBabel.jar search ".$text);
$response=json_decode($response,true,512,JSON_BIGINT_AS_STRING);
echo $response["pageContent"];
echo "<p id='mot'>".$response["pageContent"]."</p>";
?>
</body>
</html>