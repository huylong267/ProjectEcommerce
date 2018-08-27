$(document).ready(function(){
    $('#dau').hover(function(){
        $('#dau').attr('placeholder','Search')
    });
    $('#dau').click(function(){
        $('#dau').css('border','1px solid blue')
    });
    $("#go-top").click(function(){
        $('body,html').animate({scrollTop:0},600);
        return false;
    });
    $(window).scroll(function(){
        if( $(window).scrollTop() == 0 ) {
            $('#go-top').fadeOut(600);
        }else{
            $('#go-top').fadeIn(600);
        }
    });
    $('.open').click(function(){
        $('.menu2').css('display','block');
        $('.close').css('display','block');
        $('.open').css('display','none');
    });
    $('.menu-Responsive .close').click(function(){
        $('.menu2').css('display','none');
        $('.open').css('display','block');
        $('.close').css('display','none');
    });
});

// slider

var chiSo =0;
var y = document.getElementsByClassName("anh");
var z=document.getElementsByClassName("tron");
function test(){
    if (chiSo >= y.length) {
        chiSo = 0;
    };
    if (chiSo < 0) {
        chiSo = y.length-1;
    };
};
function next(){
    new WOW().init();
    test();
    show(chiSo++);
};
function back(){
    new WOW().init();
    test();
    show(chiSo--);
};
function chonSlide(n) {
    show(chiSo=n);
};
function show(chiSo) {
    var i;
    for (i = 0; i <= y.length-1; i++) {
        y[i].style.display = "none";
    };
    for (i = 0; i <= z.length-1; i++) {
        z[i].style.background = "white";
    };
    y[chiSo].style.display = "block";
    z[chiSo].style.background="red";
};

$(document).ready(function(){
    $('.view').click(function(){
        autoplay: true,
            $('.container').css('opacity','0.7');
        var x= $(this).parent().prev().children().attr('src');
        $('.lightBox img').attr('src',x);
        $('.lightBox').css('display','block');


    });
    $('.delete').click(function(){
        $('.container').css('opacity','1.0');
        $('.lightBox').css('display','none');
    });

    $(".hop").mouseover(function(){
        $(this).css("opacity","0.6");
        $(this).css("transform","rotate(-5deg)");
        $(this).css("cursor","pointer");

    });
    $(".hopPhai").mouseover(function(){
        $(this).css("opacity","0.6");
        $(this).css("transform","rotate(10deg)");
        $(this).css("cursor","pointer");

    });
    $(".hop").mouseout(function(){
        $(this).css("opacity","1");
        $(this).css("transform","rotate(0deg)");
    });
    $('.thanhNgang').each(function(){
        $(this).find('.mau').animate({
            width:$(this).attr('data-percent')
        },8000);
    });

    $(".panel").hide();
    $(".panel").eq(0).show();
    $(":button").on("click",function(){
        if($(this).parent().next().is(":hidden")==true){
            $(".panel").slideUp();
            $(this).parent().next().slideDown();
            $(":button").val("+");
            $(this).val("-");
            $(this).parent().css('background','gray');
        }
        else{
            $(this).parent().next().slideUp();
            $(this).val("+");
        }
    });


    $('.reponCon').click(function(){
        var tab_id = $(this).attr('data-tab');

        $('.reponCon').removeClass('current');
        $('.reponPhaiCon').removeClass('current');

        $(this).addClass('current');
        $("#"+tab_id).addClass('current');
    });


})

