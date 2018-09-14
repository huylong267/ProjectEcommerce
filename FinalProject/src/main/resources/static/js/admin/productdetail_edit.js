$(document).ready(function () {
    var dataProductDT = {};
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#preview-product-detail-img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    $("#input-select-img-product-detail").change(function() {
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("#input-select-img-product-detail")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function(res){
            NProgress.done();
            if(res.data.success) {
                $('#preview-product-detail-img').attr('src', res.data.link);
                dataProductDT = {
                    image: res.data.link
                };
            }
        }, function(err){
            NProgress.done();
        })
    });
    $(".btn-edit-product-detail").on("click",function () {
        var  pddInfo =  $(this).data("productdetail");
        NProgress.start();
        axios.get("/api/productdetail/getdetailproduct/"+pddInfo).then(function (res) {
            NProgress.done();
            if(res.data.success){
                dataProductDT.id = res.data.data.productdetail_id;
                dataProductDT.image = res.data.data.image;
                $("#input-select-img-product-detail").attr('src',dataProductDT.image);
                $('#productdetail-size').val(res.data.data.sizeDetail.size_id);
                $('#productdetail-color').val(res.data.data.colorDetail.color_id);
                $('#input-productdetail-quantity').val(res.data.data.quantity);
                $("#modal-create-product-detail").modal();
            }
        }, function(err){
            NProgress.done();
        })
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
           linkPost = "/api/product/update-product/" + dataProduct.id;


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
})