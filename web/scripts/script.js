var dim;
var introductionHtml;
var resultatRecherche="<p style='color:green;'>Il me semble avoir lu ce passage quelque part oui. Vous le trouverez dans la zone <span id='sArea'></span>, <span id='sAvenue'></span> avenue, bâtiment <span id='sBuilding'></span> au <span id='sFloor'></span>, dans la salle n°<span id='sRoom'></span>, bibliothèque <span id='sBookcase'></span>, <span id='sShelf'></span> étagère, livre <span id='sBook'></span> à la page <span id='sPage'></span> :</p><div id='page'></div>";

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
			$('#content').html(data);
			$('#content').html(dec_to_ascii(data.pageContent));
		},
		error:function(err){
			$('#content').html('<p>Il y a eu comme un petit problème.</p><p>Merci de réitérer ou de réduire le nom de la zone.</p><p>Si le problème persiste toujours, merci d\'envoyer un mail à piproject@totodu.net en indiquant l\'adresse de la page désirée.</p>');
		}
	});
}

function searchText(){
	$.ajax({
		method:'post',
		url:'scripts/searchBabel.php',
		dataType:'json',
		data:{
			dimension:dim,
			text:ascii_to_dec($('#text').val())
		},
		success:function(data){
			$('#content').html(resultatRecherche);
			$('#sBook').text(data.book);
			$('#sPage').text(data.page);
			$('#sArea').text(dec_to_ascii(data.area));
			$('#sAvenue').text(data.avenue);
			$('#sBuilding').text(data.building);
			$('#sFloor').text(data.floor);
			$('#sRoom').text(data.room);
			$('#sBookcase').text(data.bookcase);
			$('#sShelf').text(data.shelf);
			$('#page').html(dec_to_ascii(data.pageContent));
			$('#area').val(dec_to_ascii(data.area));
			$('#book').val(data.book);
			$('#page').val(data.page);
			$('#avenue').val(data.avenue);
			$('#building').val(data.building);
			$('#floor').val(data.floor);
			$('#room').val(data.room);
			$('#bookcase').val(data.bookcase);
			$('#shelf').val(data.shelf);
		},
		error:function(err){
			$('#content').html('<p>Il y a eu comme un petit problème.</p><p>Merci de réitérer ou de réduire votre texte ou bien de donner la possibilité d\'effectuer une recherche sur toutes les dimensions.</p><p>Si le problème persiste toujours, merci d\'envoyer un mail à piproject@totodu.net avec le texte.</p>');
		}
	});
}

function swapDimension(){
	dim=$('#dimension').val();
	$('#content').html(introductionHtml);
	$('.dim').text(dim);
	$('form').each(function(){
		$(this).get(0).reset();
	});
}