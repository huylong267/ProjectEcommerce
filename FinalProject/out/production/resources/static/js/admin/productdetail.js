$(document).ready(function () {
    var productId;
    var dataProductDT = {};
    $("#search").autocomplete({
        source: function (request, response) {
            $.ajax({
                url: "http://localhost:1111/api/product/list-product",
                dataType: "json",
                data: {
                    term: $("#search").val()
                },
                success: function (data) {
                    response($.map(data.data, function (result) {
                        return {
                            label: result.product_id,
                            value: result.name,
                            imgsrc: result.image,
                        }
                    }));
                }
            });
        }
    }).data("ui-autocomplete")._renderItem = function (ul, item) {
        var inner_html = '<a onclick="replaceItemDetail(' + item.label + ');"><div class="list_item_container"><div class="image"><img src="' + item.imgsrc + '" ></div><div class="label"><h4><b>' + item.value + '</b></h4></div></div></a>';
        productId = item.label;
        return $("<li style='list-style-type: none' id='list-item-product'></li>")
            .data("item.autocomplete", item)
            .append(inner_html)
            .appendTo(ul);
    };
    $("#btn-create-productdetail").on("click", function () {
        dataProductDT = {};
        $('#productdetail-size').val("1");
        $('#productdetail-color').val("1");
        $("#input-productdetail-quantity").val("");
        $("#modal-create-product").modal();
    });
    $(".btn-save-productdetail").on("click", function () {
        if($("#input-productdetail-quantity").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        console.log(productId);
        dataProductDT.product_id = productId;
        dataProductDT.size_id = $('#productdetail-size').val();
        dataProductDT.color_id = $('#productdetail-color').val();
        dataProductDT.quantity = $("#input-productdetail-quantity").val();

        NProgress.start();

        var linkPost = "/api/productdetail/create-product-detail";
        // if(dataProduct.id) {
        //     linkPost = "/api/product/update-product/" + dataProduct.id;
        // }

        axios.post(linkPost, dataProductDT).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    window.location.replace("/admin/productdetail/getListproductDetail")
                });
            } else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when saving product',
                'error'
            );
        })
    });

});
var dataProduct = {};
function replaceItemDetail(id) {
    $("#abc").css("display","block");
    $("#btn-create-productdetail").css("display","block");
    NProgress.start();
    axios.get("/api/product/getproduct/" + id).then(function (res) {
        NProgress.done();
        if (res.data.success) {
            dataProduct.id = res.data.data.product_id;
            dataProduct.image = res.data.data.image;
            $("#input-product-name").val(res.data.data.name);
            $("#input-product-desc").val(res.data.data.desc);
            $('#preview-product-img').attr('src', dataProduct.image);
            $('#input-product-price').val(res.data.data.price);
            $("#product-category").val(res.data.data.category_id);
            $('#datepicker-created-date-product').val(res.data.data.created_date);
            $('#datepicker-updated-date-product').datetimepicker();
        }
    }, function (err) {
        NProgress.done();
    })
}