

const swiper = new Swiper('.swiper', {
  // Optional parameters

  loop: true,


  // Navigation arrows
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },

  autoplay: {
    delay: 4000,
   },


    on: {
         slideChange : function (){

         var bar = document.querySelector("#bar");

            bar.style= "background-color : pink;";
        }

}

});