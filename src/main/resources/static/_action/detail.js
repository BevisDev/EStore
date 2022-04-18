$(document).ready(function() {
	function showDetail(json){
		$(".detail-count").html(json.count);
		$(".detail-amount").html("$" + json.amount.toFixed(2));
	}
	
	fetch(`/detail/info`).then(resp => resp.json()).then(json => {
		showDetail(json);
    });

    $(".update-detail").on("input", function() {
		var tr = $(this).closest("[data-id]");
        var id = tr.attr("data-id");
	//	var price = tr.attr("data-price");
		var qty = $(this).val();
      //  fetch(`/cart/update/${id}/${qty}`).then(resp => resp.json()).then(json => {
		//	showCartInfo(json);
			//console.log(qty);
     //   });
	//	tr.find(".item-amount").html((price * qty).toFixed(2));
	console.log(qty);
    });
})