$(document).ready(function () {

    var quantity =0;
    $(".product-over-tool-gio-hang").click(function () {
        quantity++;
        $.cookie()
        $(".number-quantity").attr('style','display:inline-block').text(quantity);

    });
    $(".item-cart").on("click", function () {
        $("#modal-create-cart").modal();
    });
});