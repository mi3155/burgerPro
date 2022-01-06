const allbtn = document.querySelector("#allbtn");
const ingbtn = document.querySelector("#ingbtn");
const endbtn = document.querySelector("#endbtn");
const btnMore = document.querySelector("#btnMore");

let ingmenu = document.querySelectorAll("#ingmenu li");
let endmenu = document.querySelectorAll("#endmenu li");
let a = 6; //한페이지에 나타낼 메뉴개수

let ccnt = 0;

function moreClick(e){
    e.preventDefault();
    // console.log(ccnt)


//allbtn on
if(allbtn.className === "TrueRed") {

    if(ccnt == 0) {
        for(i=a; i<ingmenu.length; i++) {
            ingmenu[i].className ="ing"
        } ccnt++;
    } else if (ccnt == 1) {
        for(i=0; i<a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 2) {
        for(i=a; i<2*a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 3) {
        for(i=2*a; i<3*a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 4) {
        for(i=3*a; i<4*a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 5) {
        for(i=4*a; i<5*a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 6) {
        for(i=5*a; i<6*a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 7) {
        for(i=6*a; i<7*a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 8) {
        for(i=7*a; i<endmenu.length; i++) {
            endmenu[i].className ="end"
        } ccnt++;
            btnMore.style = "display : none"
    }

}

//ingbtn on
if(ingbtn.className === "TrueRed") {
    if(ccnt == 0){
        for(i=a; i<ingmenu.length; i++) {
            ingmenu[i].className ="ing"
        } ccnt++;
            btnMore.style = "display : none"
    }
}
//endbtn on
if (endbtn.className === "TrueRed") {
    if(ccnt == 0){
        for(i=a; i<2*a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 1) {
        for(i=2*a; i<3*a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 2) {
        for(i=3*a; i<4*a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 3) {
        for(i=4*a; i<5*a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 4) {
        for(i=5*a; i<6*a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 5) {
        for(i=6*a; i<7*a; i++) {
            endmenu[i].className ="end"
        } ccnt++;
    } else if (ccnt == 6) {
        for(i=7*a; i<endmenu.length; i++) {
            endmenu[i].className ="end"
        } ccnt++;
            btnMore.style = "display : none"
    }

}



}


function AllClick(e) {
    e.preventDefault();

    ccnt = 0;
    for(i=0; i<6; i++){
        ingmenu[i].classList = "ing";
    }
    for(i=6; i<ingmenu.length; i++){
        ingmenu[i].classList = "none";
    }
    for(i=0; i<endmenu.length; i++){
        endmenu[i].classList = "none";
    }
    allbtn.className = "TrueRed"
    ingbtn.className = "FalseRed"
    endbtn.className = "FalseRed"

    btnMore.style = "display : block;"
}

function IngClick(e) {
    e.preventDefault();

    ccnt = 0;
    for(i=0; i<6; i++){
        ingmenu[i].classList = "ing";
    }
    for(i=6; i<ingmenu.length; i++){
        ingmenu[i].classList = "none";
    }
    for(i=0; i<endmenu.length; i++){
        endmenu[i].classList = "none";
    }
    allbtn.className = "FalseRed";
    ingbtn.className = "TrueRed"
    endbtn.className = "FalseRed"

    btnMore.style = "display : block;"
}

function EndClick(e) {
    e.preventDefault();

    ccnt = 0;
    for(i=0; i<6; i++){
        endmenu[i].classList = "end";
    }
    for(i=0; i<ingmenu.length; i++){
        ingmenu[i].classList = "none";
    }
    allbtn.className = "FalseRed";
    ingbtn.className = "FalseRed"
    endbtn.className = "TrueRed"

    btnMore.style = "display : block;"
}


allbtn.addEventListener('click', AllClick);
ingbtn.addEventListener('click', IngClick);
endbtn.addEventListener('click', EndClick);
btnMore.addEventListener('click', moreClick);

