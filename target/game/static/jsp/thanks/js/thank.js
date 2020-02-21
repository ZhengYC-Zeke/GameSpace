//人员名单
var _index5=0;
$("#four_flash .but_right img").click(function(){
    _index5++;
    var len=$(".flashBg ul.mobile li").length;
    if(_index5+5>len){
        $("#four_flash .flashBg ul.mobile").stop().append($("ul.mobile").html());
    }
    $("#four_flash .flashBg ul.mobile").stop().animate({left:-_index5*326},1000);
});


$("#four_flash .but_left img").click(function(){
    if(_index5==0){
        $("ul.mobile").prepend($("ul.mobile").html());
        $("ul.mobile").css("left","-1380px");
        _index5=6
    }
    _index5--;
    $("#four_flash .flashBg ul.mobile").stop().animate({left:-_index5*326},1000);
});
