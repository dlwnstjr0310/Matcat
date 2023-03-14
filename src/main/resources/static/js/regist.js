
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

    if(document.getElementById("searchZipCode")){
        const $search = document.getElementById("searchZipCode");
        $search.onclick = function() {
        new daum.Postcode({
            oncomplete: function(data) {
                document.getElementById("zipCode").value = data.zonecode;
                document.getElementById("address1").value = data.address;
                document.getElementById("address2").focus();
            }
        }).open();
    }
    }

    if(document.getElementById("duplicationCheck")) {

        const $duplication = document.getElementById("duplicationCheck");

        $duplication.onclick = function() {
            let memberId = document.getElementById("memberId").value.trim();
            fetch("/member/idDupCheck", {
                method: "POST",
                headers: {'Content-Type': 'application/json;charset=UTF-8'},
                // body: JSON.stringify({memberId: memberId})
                body: JSON.stringify({memberId})
            })
                .then(result => result.text())
                .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));

        }
    }

    if(document.getElementById("memberName")){
        const $name = document.getElementById("memberName");
        $name.onfocus = function(){
            $name.placeholder = "이름을 입력하세요."
        }
        $name.blur = function(){
            $name.value = $name.value;
        }
    }

    if(document.getElementById("memberId")){
        const $id = document.getElementById("memberId");
        $id.onfocus = function(){
            $id.placeholder = "영문 + 숫자로 14자 이내"
        }
        $id.blur = function(){
            $name.value = $name.value;
        }
    }

    if(document.getElementById("memberPwd")){
        const $pwd = document.getElementById("memberPwd");
        $pwd.onfocus = function(){
            $pwd.placeholder = "특수문자를 포함해서 8자 이상"
        }
        $pwd.blur = function(){
            $pwd.value = $pwd.value;
        }
    }

    if(document.getElementById("memberPwd")){
        const $pwd = document.getElementById("memberPwd");
        $pwd.onfocus = function(){
            $pwd.placeholder = "특수문자를 포함해서 8자 이상"
        }
        $pwd.blur = function(){
            $pwd.value = $pwd.value;
        }
    }




}


