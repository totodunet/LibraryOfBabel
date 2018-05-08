function dec_to_ascii(nbr){
	var chaine="";
	for(var i=0;i<nbr.length;i+=3)
		chaine+=String.fromCharCode(nbr[i]+nbr[i+1]+nbr[i+2]+nbr[i+3]+nbr[i+4]+nbr[i+5]+nbr[i+6]+nbr[i+7]+nbr[i+8]+nbr[i+9]);
	return chaine;
}
function ascii_to_dec(str){
	var nombre="";
	var digit;
	for(var i=0;i<str.length;i++){
		digit=str.charCodeAt(i).toString();
		nombre+="0".repeat(3-digit.length)+digit;
	}
	return nombre;
}
function goPage(){
	$.ajax({
		method:'post',
		url:'browseBabel.php',
		dataType:'json',
		success:function(data){
			$('#content').html(dec_to_ascii(data));
		},
		error:function(err){
			goPage();
		}
	});
	return false;
}
function searchText(){
	$.ajax({
		method:'post',
		url:'searchBabel.php',
		dataType:'json',
		success:function(data){
			$('#content').html(dec_to_ascii(data));
		},
		error:function(err){
			searchText();
		}
	});
	return false;
}