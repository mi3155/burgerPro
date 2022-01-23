function moving1(){
 var bar = document.getElementById("bar");

}
function moving21(){
 var bar = document.getElementById("bar");

}


const swiper = new Swiper('.swiper', {
  // Optional parameters

  loop: true,


  // Navigation arrows
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },

  autoplay: {
    delay: 4000,// 딜레이 4초
    disableOnInteraction : false, //false로 설정하면 스와이프 후 자동 재생이 비활성화 되지 않음
   },




    on: {
         slideChange : function (){

        },

        slideChangeTransitionStart:function(){
                     var  bar = document.querySelector(".bar");
                               bar.style= "display : none";

        },
        slideChangeTransitionEnd:function(){
            var  bar = document.querySelector(".bar");
            bar.style= "animation-name : moving; animation-duration : 4s;";
        },


}

});