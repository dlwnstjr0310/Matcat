
window.onload = function() {

    if(document.getElementById("doubleCheck")){

        const $button = document.getElementById("doubleCheck");

        $button.onclick = function() {
            let memberEmail = document.getElementById("memberEmail").value.trim();
            fetch("/member/pwDupCheck", {
                method: "POST",
                headers: {'Content-Type': 'application/json;charset=UTF-8'},
                body: JSON.stringify({memberEmail})
        })
            .then(result => result.text())
            .then(result => alert(result))
            .catch((error) => error.text().then((res) => alert(res)));
        }
    }

    if(document.getElementById("memberId")){
        const $id = document.getElementById("memberId");
        $id.onfocus = function(){
            $id.placeholder = ""
        }
        $id.blur = function(){
            $id.value = $id.value;
        }
    }

    if(document.getElementById("memberName")){
        const $name = document.getElementById("memberName");
        $name.onfocus = function(){
            $name.placeholder = ""
        }
        $name.blur = function(){
            $name.value = $name.value;
        }
    }

    if(document.getElementById("memberPhone")){
        const $phone = document.getElementById("memberPhone");
        $phone.onfocus = function(){
            $phone.placeholder = ""
        }
        $phone.blur = function(){
            $phone.value = $phone.value;
        }
    }

    if(document.getElementById("memberEmail")){
        const $Email = document.getElementById("memberEmail");
        $Email.onfocus = function(){
            $Email.placeholder = ""
        }
        $Email.blur = function(){
            $Email.value = $Email.value;
        }
    }



}
