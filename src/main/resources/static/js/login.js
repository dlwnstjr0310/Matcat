var count = 2;
window.onload = function(){

    if(document.getElementById("matcat")){
        const $cat = document.getElementById("matcat");
        const yellowCat = "/image/고양이.png";
        const blackCat = "/image/까만고양이.png";
        $cat.onclick = function(){
            if(count%2 == 0){
                $cat.src = blackCat;
                const $login = document.getElementById("loginForm");
                $login.action = "/admin/login";
                console.log($login)
                count++;
            } else {
                $cat.src = yellowCat;
                const $login = document.getElementById("loginForm");
                $login.action = "/member/login";
                console.log($login)
                count++;
            }
        }
    }

}