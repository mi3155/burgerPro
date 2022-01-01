//단품, 세트메뉴 li
const solo = document.querySelector("#solo");
const set = document.querySelector("#set");

//단품일때, 세트일때 ul를 none or 보이게
const soloMenu = document.querySelector("#mcMenu1");
const setMenu = document.querySelector("#mcMenu22");
const btnMore = document.querySelector(".btnMore");

//menuList li를 보이게끔, 그리고 버튼클릭시 none된 클래스들이 보이게
let menuList = document.querySelectorAll(".menuList li");
let menuList2 = document.querySelectorAll(".menuList2 li");
let ccnt = 0;
var contArea = document.getElementById("contArea1");

count1.innerText = menuList.length+"products";
count2.innerText = menuList2.length+"products";

function more(){

    if(contArea.clientHeight<1111){

    contArea.style= "height: 1873px"

    
} else if(contArea.clientHeight<1900 && contArea.clientHeight>1200){

    contArea.style="height: 2647px"

    
}   else if (contArea.clientHeight<2700 && contArea.clientHeight>1700){

    contArea.style = "height : 2864px"

    
}
    
}


function moreClick(){
    
if(solo.className === "TrueRed"){
    ccnt++;
    if(ccnt == 1){
        for(i=6; i<12; i++){
            menuList[i].className="sololi"
    } 
    }else if(ccnt == 2){
        for(i=12; i<18; i++){
            menuList[i].className="sololi"
    } 
    }else if(ccnt == 3){
        for(i=18; i<menuList.length; i++){
            menuList[i].className="sololi"
    } 
        btnMore.style ="display : none"
    }
}

if(set.className === "TrueRed"){

    ccnt++;

    if(ccnt == 1){
        for(i=6; i<12; i++){
            menuList2[i].className="sololi"
    }
    }else if(ccnt == 2){
        for(i=12; i<18; i++){
            menuList2[i].className="sololi"
    } 
    }else if(ccnt == 3){
        for(i=18; i<menuList2.length; i++){
            menuList2[i].className="sololi"
    } 
        btnMore.style ="display : none"
     }
    }
}

function soloClick(e){
   e.preventDefault();

   ccnt = 0;

   for(i=0; i<6; i++){
       menuList[i].className ="sololi";
   }
   for(i=6; i<menuList.length; i++){
    menuList[i].className = "none";
}

contArea.style = "height : 1099px"
   solo.className= "TrueRed";
   set.className= "FalseRed";
   soloMenu.className = "mcMenu";
   setMenu.className = "none";
    
   btnMore.style = "display : block;"
}


function setClick(e){
    e.preventDefault();

    ccnt = 0;

    for(i=0; i<6; i++){
        menuList2[i].className = "sololi"
    }
    for(i=6; i<menuList2.length; i++){
     menuList2[i].className = "none";
    }
 contArea.style = "height : 1099px"
    set.className= "TrueRed";
    solo.className= "FalseRed";
    setMenu.className = "mcMenu";
    soloMenu.className = "none";

   btnMore.style = "display : block;"
}

solo.addEventListener('click', soloClick);
set.addEventListener('click', setClick);
btnMore.addEventListener('click', moreClick);
