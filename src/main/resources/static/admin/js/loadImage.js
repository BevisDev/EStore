$(document).ready(function(){
	$("a[href*=delete]").on("click",function(){
		if(!confirm("Are you sure to delete this?")){
			return false;
		}
	});
	
	// hiện ảnh tạm thời trên client 
	
	// account
	$("input[name = avatar_file]").on("change",function(){
		showImage(this, "#image_avatar");
	});
	
	//product
	$("input[name =image_file1]").on("change",function(){
		showImage(this, "#image_product");
	});
	
	//brand
	$("input[name =imageLogo]").on("change",function(){
		showImage(this, "#image_logo");
	});
	
	showImage = function(fileSelector, imageSeletor){
		let file = $(fileSelector).get(0).files[0];
		let fileReader = new FileReader();
		fileReader.onload = function(){
			$(imageSeletor).attr("src", fileReader.result);
		};
		fileReader.onerror = function(){
			alert(fileReader.error);
		};
		fileReader.readAsDataURL(file);
	};
});