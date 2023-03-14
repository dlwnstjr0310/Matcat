
window.onload = function() {

    if(document.getElementById("doubleCheck")){
        const button = document.getElementById("doubleCheck");
        button.onclick = function() {
            const $userEmail = document.getElementById("Email");
            const $sendEmail = document.forms["sendEmail"];
            fetch("/member/doubleCheck", {
                method: 'POST',
                headers: {'Content-Type': 'application/json;charset=UTF-8'},
                body: JSON.stringify({$userEmail})
            })
            .then(result => result.text())
            .then(result => alert(result))
            .catch((error) => error.text().then((res) => alert(res)));
        }
    }

}