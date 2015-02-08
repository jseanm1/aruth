function tryExecute(){
	if(validateInput()){
		$("#error-bar-home").hide();
		submitInput();
		getAllSenses();
	}
}

function validateInput(){
	
	var context = $("#context").val().trim();
	var word = $("#target").val().trim();
	var status=true;
	$("#error-bar-home").empty();
	if(context == "" && word == ""){
		$("#error-bar-home").show();
		$("#error-bar-home").empty().append("<span>කරුණාකර අරුත විමසීමට අවශ්‍ය වචනය සහ එය අඩංගු වගන්තිය හෝ පරිච්ඡේදය  ඇතුලත් කරන්න</span>");
		status = false;
	} else if(word == ""){
		$("#error-bar-home").show();
		$("#error-bar-home").empty().append("<span>කරුණාකර අරුත විමසීමට අවශ්‍ය වචනය ඇතුලත් කරන්න</span>");
		status = false;
	} else if(context == ""){
		$("#error-bar-home").show();
		$("#error-bar-home").empty().append("<span>කරුණාකර අරුත විමසීමට අවශ්‍ය වචනය අඩංගු වගන්තිය හෝ පරිච්ඡේදය  ඇතුලත් කරන්න</span>");
		status = false;
	} 
	return status;
}


function submitInput(){
	//get the user input data from the view using jQuery
	var context = $("#context").val().trim();
	var word = $("#target").val().trim(); 
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
			 			var res = data["sense"].split("|");
			 			var sense = res[0];
			 			var offSet = data["offset"];
			 			console.log("offSet :   "+offSet);
			 			//alert(sense);
			 			//$('#result2').empty().append( "<p>The correct sense :"+ sense +"</p>" );
			 			$('#result-show').val( ""+ sense +"" ); 
			 			$('#offSet').val(offSet);
			 			//alert($('#offSet').val());
				   }, 
		//error: function( jqXhr, textStatus, errorThrown ){ console.log( errorThrown ); } 
		error: function(data){ 
			if(data["responseText"] == "100001"){
				console.log( data["responseText"] ); 
				$('#result-show').val( " අරුත පැහැදිලි කිරීමට තරම් ප්‍රමාණවත් දත්ත නොමැත " ); 
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
	$( "#all-senses" ).show(); 
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
			 		$("#word-senses").empty();
			 		for (var i = 0; i < data.length; i++) { 
			 			console.log(data[i]);
			 			var res = data[i].split("|"); 
			 			$("#word-senses").append("<div class='meaning-of-a-sense'>"+ res[0] +"</div>");
			 			$("#word-senses").append("<div class='example-of-a-sense'>"+ res[1].replace(/"/g, "") +"</div>");
			 		}  
		 },	 		 
		 error: function(data){ 
			if(data["responseText"] == "100001"){
				console.log( data["responseText"] ); 
				$('#word-senses').empty().append("<div>අරුත පැහැදිලි කිරීමට තරම් ප්‍රමාණවත් දත්ත නොමැත</div>" );
			} else if(data["responseText"] == "000005"){
				console.log( data["responseText"] ); 
			}
		 }
		 
   }); 
}
