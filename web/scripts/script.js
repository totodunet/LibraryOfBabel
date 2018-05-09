var dim;
var introductionHtml;

$(document).ready(function(){
	dim=$('#dimension').val();
	introductionHtml=$('#content').html();
	$('#formDimension').submit(function(){swapDimension();return false;});
	$('#formSearch').submit(function(){searchText();return false;});
	$('#formBrowse').submit(function(){goPage();return false;});
});

function dec_to_ascii(nbr){
	var chaine="";
	for(var i=0;i<nbr.length;i+=10)
		chaine+=String.fromCharCode(nbr[i]+nbr[i+1]+nbr[i+2]+nbr[i+3]+nbr[i+4]+nbr[i+5]+nbr[i+6]+nbr[i+7]+nbr[i+8]+nbr[i+9]);
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

function goPage(){
	$.ajax({
		method:'post',
		url:'scripts/browseBabel.php',
		dataType:'json',
		data:{
			dimension:dim,
			area:ascii_to_dec($('#area').val()),
			avenue:$('#avenue').val(),
			building:$('#building').val(),
			floor:$('#floor').val(),
			room:$('#room').val(),
			bookcase:$('#bookcase').val(),
			shelf:$('#shelf').val(),
			book:$('#book').val(),
			page:$('#page').val()
		},
		success:function(data){
			$('#content').html(dec_to_ascii(data.pageContent));
		},
		error:function(err){
			$('#content').html('<p>Il y a eu comme un petit un problème.</p><p>Merci de réitérer ou de réduire le nom de la zone.</p><p>Si le problème persiste toujours, merci d\'envoyer un mail à piproject@totodu.net en indiquant l\'adresse de la page désirée.</p>');
		}
	});
}

function searchText(){
	$.ajax({
		method:'post',
		url:'scripts/searchBabel.php',
		dataType:'json',
		data:{
			
		},
		success:function(data){
			$('#content').html(dec_to_ascii(data));
		},
		error:function(err){
			$('#content').html('<p>Il y a eu comme un petit un problème.</p><p>Merci de réitérer ou de réduire votre texte ou bien de donner la possibilité d\'effectuer une recherche sur toutes les dimensions.</p><p>Si le problème persiste toujours, merci d\'envoyer un mail à piproject@totodu.net avec le texte.</p>');
		}
	});
}

function swapDimension(){
	dim=$('#dimension').val();
	$('#content').html(introductionHtml);
	$('span .dimension').each(function(){
		console.log($(this).val());
		$(this).text(dim);
	});
	$('form').each(function(){
		$(this).get(0).reset();
	});
}