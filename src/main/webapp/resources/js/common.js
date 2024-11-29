// 전역함수 ZONE
let close = document.getElementById('btn_close');
let top_event = document.getElementsByClassName('event')[0]
let header = document.getElementsByClassName('header')[0]
let container = document.getElementsByClassName('container')[0]
let guest = document.getElementsByClassName('guest_menu')[0]
let guest_box = document.getElementsByClassName('guest_menu_box')[0]
// 이벤트창 닫혀있는지 열려있는지 체크용 bool
let bool_event = true;

// 최상단 이벤트 박스 close 클릭 이벤트
close.addEventListener('click', function(){
    top_event.style.display = "none";
    header.style.height = '80px';
    container.style.paddingTop = '80px';
    bool_event = false;

});

// 헤더에서 로그인 메뉴 클릭시 포커스 이벤트

guest.addEventListener('focus', function(){
    guest.classList.add('guest_menu_active');
    guest_box.classList.add('guest_menu_box_active')
});
guest.addEventListener('blur', function(){
    guest.classList.remove('guest_menu_active');
    guest_box.classList.remove('guest_menu_box_active')
});


$(document).ready(function(){
    // 비로그인, 로그인 상태 시 헤더 노출
    let guest_box = `
        <p>로그인</p>
        <p>회원가입</p>
    `
    let login_box = `
        <p>마이페이지</p>
        <p>마이 맛집 </p>
        <p>마이 후기</p>
    `
    let login = false;
    if(login == false){
        $('.guest_menu_box').empty()
        $('.guest_menu_box').append(guest_box)
    }
    else if(login == true){
        $('.guest_menu_box').empty()
        $('.guest_menu_box').append(login_box)
    }
});