
window.onload = function(){
    if(document.getElementById("AllCheck")){
        const $check = document.getElementById("AllCheck");
        $check.addEventListener("change", function(e){
            e.preventDefault();
            const list = document.querySelectorAll("input[class=check]");
            for (var i = 0; i < list.length; i++) {
                list[i].checked = this.checked;
            }
        })}
    
    if(document.getElementById("next")){
        const $next = document.getElementById("next");
        const $check = document.getElementById("check1");
        const $check2 = document.getElementById("check2");
        $next.onclick = function(){
            if(!($check.checked && $check2.checked)){
                alert("필수 옵션을 체크해주세요.");
            } else {
                location.href='/member/regist2';
            }
        }
    }

        
    }


