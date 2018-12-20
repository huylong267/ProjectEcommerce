$(document).ready(function () {
   $(".div-input-choose-color").change(function () {
       var pId = $(this).data("productid");
        var optradio = $("input[name=optradio]:checked").val();
       NProgress.start();
        axios.get("/api/productdetail/findproductDetailbyColor/"+pId+"?optradio="+optradio).then(function (res) {
            NProgress.done();
            if(res.data.success){
                var link = res.data.data.image
                $("#zoom_09").attr('src',link);
                $("#zoom_09").data('zoom-image',link).elevateZoom();

            }
        }, function(err){
            NProgress.done();
        })
   })
})