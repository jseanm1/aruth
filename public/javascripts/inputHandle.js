
function submitInput(){
	//get the user input data from the view using jQuery
	var context = $("#context").val();
	var word = $("#target").val();
	$( "#result" ).show(); 
	// we use ajax to send a post request to back-end and send a json response to front-end
	// https://www.airpair.com/js/jquery-ajax-post-tutorial
	
	$.ajax({
		 url: '/tryDisambiguatePost', 
		 data: JSON.stringify({"context": context,"target": word}), 
		 type: 'post', 
		 contentType: 'application/json',
		 dataType: 'json', 		// use this if the response type is Json	 
		 //dataType: 'html',   // use this if the response type is html	 
		 processData: false,
		 success:  function( data){
			 		console.log(data); // to print response data to the console
			 		
			 		/*var output;			 		
			 		for (var property in data) {
			 			  output = property + ' : ' + data[property]+'; ';
			 		}
			 		alert(output);*/
			 			
						//$('#result').html( data ); 
			 			var sense = data["sense"];
			 			//alert(sense);
			 			//$('#result2').empty().append( "<p>The correct sense :"+ sense +"</p>" );
			 			$('#result-show').val( ""+ sense +"" ); 
				   }, 
		//error: function( jqXhr, textStatus, errorThrown ){ console.log( errorThrown ); } 
		error: function(data){ 
			if(data["responseText"] == "100001"){
				console.log( data["responseText"] ); 
				$('#result-show').val( " අරුත පැහැදිලි කිරීමට තරම් ප්‍රමණවත් දත්ත නොමැත " ); 
			} else if(data["responseText"] == "000005"){
				console.log( data["responseText"] ); 
			}
		}
		 
   }); 
}


function getAllSenses(){
	//get the user input data from the view using jQuery
	var context = $("#context").val();
	var word = $("#target").val();
	//$( "#result" ).show(); 
	// we use ajax to send a post request to back-end and send a json response to front-end
	// https://www.airpair.com/js/jquery-ajax-post-tutorial
	
	$.ajax({
		 url: '/getAllSenses', 
		 data: JSON.stringify({"target": word}), 
		 type: 'post', 
		 contentType: 'application/json',
		 dataType: 'json', 		// use this if the response type is Json	 
		 //dataType: 'html',   // use this if the response type is html	 
		 processData: false,
		 success:  function( data){
			 		console.log(data); // to print response data to the console
			 		
			 		/*var output;			 		
			 		for (var property in data) {
			 			  output = property + ' : ' + data[property]+'; ';
			 		}
			 		alert(output);*/
			 			
						//$('#result').html( data ); 
			 			//var sense = data["sense"];
			 			//alert(sense);
			 			//$('#result2').empty().append( "<p>The correct sense :"+ sense +"</p>" );
			 			//$('#result-show').val( ""+ sense +"" ); 
				   } 
		//error: function( jqXhr, textStatus, errorThrown ){ console.log( errorThrown ); } 
		/*error: function(data){ 
			if(data["responseText"] == "100001"){
				console.log( data["responseText"] ); 
				$('#result-show').val( " අරුත පැහැදිලි කිරීමට තරම් ප්‍රමණවත් දත්ත නොමැත " ); 
			} else if(data["responseText"] == "000005"){
				console.log( data["responseText"] ); 
			}
		}*/
		 
   }); 
}
