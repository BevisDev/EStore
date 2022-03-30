$(document).ready(function(){
	$(".bb-like").on("click", function() {
        var id = $(this).closest("[data-id]").attr("data-id");
        var url = "/product/like/" + id;
        fetch(url).then(resp => resp.json()).then(json => {
            alert("Cảm ơn bạn đã tim ");
        });
    });
})