

function openApiPost(){
    var addr1 = document.getElementById("addr1");
    new daum.Postcode({
        oncomplete: function(data){
            if(data.userSelectedType === 'R'){
            addr1.value = data.roadAddress;
          }
          else {
            addr1.value = data.jibunAddress;
          }
        }
    }).open();

}