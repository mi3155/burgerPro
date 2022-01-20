
var bar = document.querySelector("#bar");
var play = document.querySelector("#play");
//var bar = document.getElementsByClassName("bar");
//var play = document.getElementsByClassName("play");
//var pause = document.getElementsByClassName("pause");

//화면 이미지 변경
var prev = document.getElementsByClassName("prev");
var next = document.getElementsByClassName("next");

function PlayClick(e){
e.preventDefault();
if(play.className ==="play"){
    play.className = "play pause";
    bar.style = "animation-name : moving; animation-duration : 4s; animation-iteration-count : infinite;";

}

else{

        play.className = "play";

}
}


play.addEventListener('click', PlayClick);
