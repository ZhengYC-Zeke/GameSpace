$(document).ready(function() {
        var mySwiper = new Swiper(".swiper-container", {
            slidesPerView: 4, //默认是显示4个
            initialSlide: 999, //默认从第几个显示，999是为了让右边没有
            spaceBetween: 0, //间距
            speed: 1000,//速度
            prevButton: ".swiper-button-prev", //左右按钮
            nextButton: ".swiper-button-next"
        })
    $("#news-slider").owlCarousel({
        items:3,
        itemsDesktop:[1199,2],
        itemsDesktopSmall:[980,2],
        itemsMobile:[600,1],
        pagination:false,
        navigationText:false,
        autoPlay:true
    });
});
