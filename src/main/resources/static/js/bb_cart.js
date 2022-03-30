$(document).ready(function() {
	function showCartInfo(json) {
		$(".cart-count").html(json.count);
		$(".cart-amount").html(json.amount.toFixed(2)+ '$');
	}

	 fetch(`/card/info`).then(resp => resp.json()).then(json => {
		showCartInfo(json);
	});

	

	//add cart
	$(".bb-add-to-cart").on("click", function() {
		var id = $(this).closest("[data-id]").attr("data-id");
		fetch(`/cart/add/${id}`).then(resp => resp.json()).then(json => {
			showCartInfo(json);
		});
	});
	
	//remove cart
	$(".bb-remove-from-cart").on("click", function() {
        var id = $(this).closest("[data-id]").attr("data-id");
        fetch(`/cart/remove/${id}`).then(resp => resp.json()).then(json => {
			showCartInfo(json);
        });
		$(this).closest("[data-id]").hide(300);
    });

	//update cart
	$(".bb-cart-update").on("input", function() {
		var tr = $(this).closest("[data-id]");
		var price = $(this).closest("[data-price]").attr("data-price");
        var id = tr.attr("data-id");
		var qty = $(this).val();
        fetch(`/cart/update/${id}/${qty}`).then(resp => resp.json()).then(json => {
			showCartInfo(json);
        });
		tr.find(".item-amount").html('$' + (price * qty).toFixed(2));
    });
});