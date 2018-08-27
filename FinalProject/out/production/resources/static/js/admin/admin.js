$(document).ready(function () {
    var dataProduct = {};

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#preview-product-img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#input-select-img-product").change(function() {
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("#input-select-img-product")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function(res){
            NProgress.done();
            if(res.data.success) {
                $('#preview-product-img').attr('src', res.data.link);
                dataProduct = {
                    image: res.data.link
                };
            }
        }, function(err){
            NProgress.done();
        })
    });

    $('#datepicker-created-date-product').datetimepicker();
    $('#datepicker-updated-date-product').datetimepicker();

    $("#li-new-product").on("click", function () {
        dataProduct = {};
        $('#preview-product-img').attr('src', '/img/default.png');
        $('#input-product-name').val("");
        $('#input-product-desc').val("");
        $("#product-category").val("3");
        $("#input-product-price").val("")
        $("#modal-create-product").modal();
    });

    $(".btn-edit-product").on("click", function () {
        $("#form-update-date").css({ display: "block" });
        $("#datepicker-created-date-product").addClass("disabledbutton");

        var pdInfo = $(this).data("product");
         NProgress.start();
        axios.get("/api/product/getproduct/" + pdInfo).then(function(res){
            NProgress.done();
            if(res.data.success) {
                dataProduct.id = res.data.data.product_id;
                dataProduct.image = res.data.data.image;
                $("#input-product-name").val(res.data.data.name);
                $("#input-product-desc").val(res.data.data.desc);
                $('#preview-product-img').attr('src', dataProduct.image);
                $('#input-product-price').val(res.data.data.price);
                $("#product-category").val(res.data.data.category.category_id);
                var createdDate = moment(res.data.data.created_date, "YYYY-MM-DD HH:mm:ss");
                $('#datepicker-created-date-product').data("DateTimePicker").date(createdDate);
                var updateDate = moment(res.data.data.updated_date, "YYYY-MM-DD HH:mm:ss");
                $('#datepicker-updated-date-product').data("DateTimePicker").date(updateDate);
                $("#modal-create-product").modal();
            }
        }, function(err){
            NProgress.done();
        })
    });

    $(".btn-save-product").on("click", function () {
        if($("#input-product-name").val() === "" || $("#input-product-desc").val() === "" || dataProduct.image === undefined) {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        var createdDate = null;
        var updateDate = null;
        if($("#datepicker-created-date-product").data("DateTimePicker").date()) {
            createdDate = $("#datepicker-created-date-product").data("DateTimePicker").date().format("YYYY-MM-DD HH:mm:ss");
        }
        if($("#datepicker-updated-date-product").data("DateTimePicker").date()) {
            updateDate = $("#datepicker-updated-date-product").data("DateTimePicker").date().format("YYYY-MM-DD HH:mm:ss");
        }
        dataProduct.name = $('#input-product-name').val();
        dataProduct.desc = $('#input-product-desc').val();
        dataProduct.created_date = createdDate;
        dataProduct.updated_date = updateDate;
        dataProduct.category_id = $("#product-category").val();
        dataProduct.price = $("#input-product-price").val();

        NProgress.start();

        var linkPost = "/api/product/createproduct";
        if(dataProduct.id) {
            linkPost = "/api/product/update-product/" + dataProduct.id;
        }

        axios.post(linkPost, dataProduct).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    window.location.replace("/admin/product/getListproduct")
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

    $(".btn-delete-product").on("click", function () {
        var pdInfo = $(this).data("product");
        swal({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true
        }).then(function(result) {
            if (result.value) {
                NProgress.start();
                axios.post("/api/product/delete-product", {
                    product_id: pdInfo
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