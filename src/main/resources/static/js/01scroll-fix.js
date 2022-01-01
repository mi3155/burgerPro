
 

document.addEventListener('scroll',function(){
var header2 = document.getElementById("header2");
var hLogo2 = document.getElementById("hLogo2");
var himg2 = document.getElementById("himg2");
var hMenu2 = document.getElementById("header-nav");
var Menu2 = document.getElementById("Menu2");
var hla2 = document.getElementById("hla2");
var hla3 = document.getElementById("hla3");
var hla4 = document.getElementById("hla4");
var hla5 = document.getElementById("hla5");

var curpos = document.documentElement.scrollTop; //스크롤 상단값 curpos에 대입
var depth1 = document.getElementById("demth1");

if(curpos>140){
    header2.style = "position:fixed;height:81px;z-index:12"
    hLogo2.style = "top:18px"
    himg2.style = "height:45px"
    hMenu2.style = "padding:22px 0"
    hla2.style = "line-height:80px;bottom:25px"
    hla3.style = "line-height:80px;bottom:25px"
    hla4.style = "line-height:80px;bottom:25px"
    hla5.style = "line-height:80px;bottom:25px"
    Menu2.style = "height:80px"
    
}  else{
    header2.style = ";"
    hLogo2.style =  ";"
    himg2.style = " ;"  
    hMenu2.style = ";"
    Menu2.style = ";"
    hla2.style = ";"
    hla3.style = ";"
    hla4.style = ";"
    hla5.style = ";"

}


})

document.addEventListener('scroll',function(){
    var curpos2 = document.documentElement.scrollHeight;
    var curpos = document.documentElement.scrollTop; //스크롤 상단값 curpos에 대입
   
    var aside2 = document.getElementById("aside2");
    var btnTop = document.getElementById("btnTop");

    if( curpos2-curpos-937 < 278){
    aside2.style = "position:absolute;bottom:378px"
    btnTop.style = "position:absolute;bottom:310px"
    } else {
    aside2.style = ";"
    btnTop.style = ";"
    }
})
   

