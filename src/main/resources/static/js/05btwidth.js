
var bar = document.querySelector("#bar");
var play = document.querySelector("#play");


 function PlayClick(e){
e.preventDefault();
if(play.className ==="play"){



    play.className = "play pause";
    bar.style = "animation-name : moving; animation-duration : 4s; animation-iteration-count : 1;";
    swiper.autoplay.start();


}

else{

        play.className = "play";
        bar.style="display:none;"

        swiper.autoplay.stop();
    }

}


play.addEventListener('click', PlayClick);
