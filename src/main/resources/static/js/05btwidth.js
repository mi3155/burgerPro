
var bar = document.querySelector("#bar");
var play = document.querySelector("#play");


//화면 이미지 변경
var prev = document.querySelector("prev");
var next = document.querySelector("next");


 function PlayClick(e){
e.preventDefault();
if(play.className ==="play"){



    play.className = "play pause";
    bar.style = "animation-name : moving; animation-duration : 4s; animation-iteration-count : infinite;";
    swiper.autoplay.start();


}

else{

        play.className = "play";
        bar.style="display:none;"

        swiper.autoplay.stop();
}

}


play.addEventListener('click', PlayClick);
