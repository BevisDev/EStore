$(document).ready(function() {
	function showCartInfo(json){
		$(".cart-count").html(json.count);
		$(".cart-amount").html("$" + json.amount.toFixed(2));
	}
	
	fetch(`/cart/info`).then(resp => resp.json()).then(json => {
		showCartInfo(json);
    });

    $(".bb-add-to-cart").on("click", function() {
        var id = $(this).closest("[data-id]").attr("data-id");
        fetch(`/cart/add/${id}`).then(resp => resp.json()).then(json => {
			showCartInfo(json);
		//	console.log(json);
        });
    });

	$(".bb-remove-from-cart").on("click", function() {
        var id = $(this).closest("[data-id]").attr("data-id");
        fetch(`/cart/remove/${id}`).then(resp => resp.json()).then(json => {
			showCartInfo(json);
        });
		$(this).closest("[data-id]").hide(300);
    });

	$(".bb-cart-update").on("input", function() {
		var tr = $(this).closest("[data-id]");
        var id = tr.attr("data-id");
		var price = tr.attr("data-price");
		var qty = $(this).val();
        fetch(`/cart/update/${id}/${qty}`).then(resp => resp.json()).then(json => {
			showCartInfo(json);
			//console.log(qty);
        });
		tr.find(".item-amount").html((price * qty).toFixed(2));
    });

	$(".detail-update").on("change", function() {
		var tr = $(this).closest("[data-id]");
        var id = tr.attr("data-id");
		var qty = $(this).val();
        fetch(`/detail/update/${id}?qty=${qty}`).then(resp => resp.json()).then(json => {
			showCartInfo(json);
			//console.log(qty);
        });
    });
});