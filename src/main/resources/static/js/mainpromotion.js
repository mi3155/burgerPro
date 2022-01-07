
const btnMore = document.querySelector("#btnMore");

let ingmenu = document.querySelectorAll("#ingmenu li");
let a = 6; //한페이지에 나타낼 메뉴개수

let ccnt = 0;

function moreClick(e){
    e.preventDefault();

     console.log(ccnt)


    if(ccnt == 0) {
        for(i=a; i<2*a; i++) {
            ingmenu[i].className ="ing"
        } ccnt++;

    } else if (ccnt == 1) {
        for(i=2*a; i<3*a; i++) {
            ingmenu[i].className ="ing"
        }
        btnMore.style = "display : none"
    }


}

btnMore.addEventListener('click', moreClick);

