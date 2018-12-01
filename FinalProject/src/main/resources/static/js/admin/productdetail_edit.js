$(document).ready(function () {
    var dataProductDTEdit = {};
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#preview-product-detail-img-edit').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    $("#input-select-img-product-detail-edit").change(function() {
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("#input-select-img-product-detail-edit")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function(res){
            NProgress.done();
            if(res.data.success) {
                $('#preview-product-detail-img-edit').attr('src', res.data.link);
                dataProductDTEdit = {
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
                dataProductDTEdit.id = res.data.data.productdetail_id;
                dataProductDTEdit.image = res.data.data.image;
                $("#preview-product-detail-img-edit").attr('src',dataProductDTEdit.image);
                $('#productdetail-size-edit').val(res.data.data.sizeDetail.size_id);
                $('#productdetail-color-edit').val(res.data.data.colorDetail.color_id);
                $('#input-productdetail-quantity-edit').val(res.data.data.quantity);
                $("#modal-create-product-detail-edit").modal();
            }
        }, function(err){
            NProgress.done();
        })
    });
    $(".btn-save-productdetail-edit").on("click", function () {

        if($("#input-productdetail-quantity-edit").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }

        dataProductDTEdit.size_id = $('#productdetail-size-edit').val();
        dataProductDTEdit.color_id = $('#productdetail-color-edit').val();
        dataProductDTEdit.quantity = $("#input-productdetail-quantity-edit").val();
        NProgress.start();
        var  linkPost = "/api/productdetail/update-detail/" + dataProductDTEdit.id ;
        console.log(dataProductDTEdit);
        axios.post(linkPost, dataProductDTEdit).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
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

    $(".btn-delete-product-detail").on("click", function () {
        var pdInfo = $(this).data("productdetail");
        swal({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true
        }).then(function(result) {
            if (result.value) {
                NProgress.start();
                axios.post("/api/productdetail/delete-productdetail", {
                    productdetail_id: pdInfo
                }).then(function(res){
                    NProgress.done();
                    if(res.data.success) {
                        swal(
                            'Good job!',
                            res.data.message,
                            'success'
                        ).then(function() {
                            location.reload();
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
            }
        })
    });

});