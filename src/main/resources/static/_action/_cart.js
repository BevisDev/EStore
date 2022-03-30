$(document).ready(function() {
	function showCartInfo(json){
		$("#cart-cnt").html(json.count);
		$("#cart-amt").html(json.amount.toFixed(2));
	}
	
	fetch(`/cart/info`).then(resp => resp.json()).then(json => {
		showCartInfo(json);
    });

    $(".bb-add-to-cart").on("click", function() {
        var id = $(this).closest("[data-id]").attr("data-id");
        fetch(`/cart/add/${id}`).then(resp => resp.json()).then(json => {
			showCartInfo(json);
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
        });
		tr.find(".item-amount").html((price * qty).toFixed(2));
    });
});