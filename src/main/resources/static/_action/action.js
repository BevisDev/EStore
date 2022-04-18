$(document).ready(function(){
	$(".bb-like").on("click", function() {
        var id = $(this).closest("[data-id]").attr("data-id");
        var url = "/user/product/like/" + id;
        fetch(url).then(resp => resp.json()).then(json => {
            alert("Thanks for your like "+ json);
        });
    });

	$(".bb-share").on("click", function() {
		id = $(this).closest("[data-id]").attr("data-id");
        $("#share-dialog").modal('show');
    });

	$(".bb-share-send").on("click", function() {
		var url = "/user/product/share-send/";
		var form = {
			sender: $("#sender").val(),
            receiver: $("#receiver").val(),
            subject: $("#subject").val(),
            text: $("#text").val(),
            product: { id: id }
		}
		
		fetch(url, {
		  method: 'POST',
		  headers: {
		    'Content-Type': 'application/json',
		  },
		  body: JSON.stringify(form),
		})
		.then(response => {
			$("#share-dialog").modal('hide')
		})	
		.catch((error) => {
		  console.error('Error:', error);
		});
    });
	
	
})