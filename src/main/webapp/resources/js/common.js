// 전역함수 ZONE
let close = document.getElementById('btn_close');
let top_event = document.getElementsByClassName('event')[0]
let header = document.getElementsByClassName('header')[0]
let container = document.getElementsByClassName('container')[0]
let guest = document.getElementsByClassName('guest_menu')[0]
let guest_box = document.getElementsByClassName('guest_menu_box')[0]
// 이벤트창 닫혀있는지 열려있는지 체크용 bool
let bool_event = true;
// 로그인 되어있는지 안되어있는지 체크용 bool (기본 false)
let login = false;

// 최상단 이벤트 박스 close 클릭 이벤트
close.addEventListener('click', function(){
    top_event.style.display = "none";
    header.style.height = '80px';
    container.style.paddingTop = '80px';
    bool_event = false;

});

// 최상단 이벤트 박스 auto_play 이벤트
let event_txt = document.querySelectorAll('.contents_area > p')
let event_count = event_txt.length;
let event_inc = 0;
let pos_x = 0

function event_play(){
    event_txt.forEach((element) => {
        element.classList.add('event_trans')
    });
    event_txt[(event_inc + event_count) % event_count].style.top = `-100%`
    event_txt[(event_inc + (event_count+1)) % event_count].style.top = `0%`

    setTimeout(() => {
        event_txt[(event_inc + event_count) % event_count].classList.remove('event_trans')
        event_txt[(event_inc + event_count) % event_count].style.top = `100%`
        event_inc += 1
    }, 500);
}

let interval = setInterval(() => {
    event_play()
},3000 )

// 헤더에서 로그인 메뉴 클릭시 포커스 이벤트

guest.addEventListener('focus', function(){
    guest.classList.add('guest_menu_active');
    guest_box.classList.add('guest_menu_box_active')
});
guest.addEventListener('blur', function(){
    guest.classList.remove('guest_menu_active');
    guest_box.classList.remove('guest_menu_box_active')
});

// top 버튼 누르면 페이지 상단으로 이동

document.getElementsByClassName('move_top')[0].addEventListener('click', function(e){
    e.preventDefault();

    window.scrollTo({top:0, behavior: 'smooth'})
});



$(document).ready(function(){
    // 비로그인, 로그인 상태 시 헤더 노출
    // let guest_box = `
    //     <p class="login_com">로그인</p>
    //
    //     <p>회원가입</p>
    // `
    // let login_box = `
    //     <p>마이페이지</p>
    //     <p>마이 맛집 </p>
    //     <p>마이 후기</p>
    //
    //     <p class="login_del">로그아웃</p>
    // `
    // $('.guest_menu_box').append(guest_box)
    //
    //
    // $(document).on('click','.register',function(){
    //     window.location.href = './register.html'
    // })
    // $(document).on('click','.my_page',function(){
    //     window.location.href = './mypage.html'
    // })
    // $(document).on('click','.my_rest',function(){
    //     window.location.href = './list_page_restaurant.html'
    // })
    // $(document).on('click','.my_review',function(){
    //     window.location.href = './list_page_review.html'

    $(document).on('click','.login_com',function(){
        window.location.href = 'login'
    })
    $(document).on('click','.register_com',function(){
        window.location.href = 'join'
    })
    $(document).on('click','.myPage_com',function(){
        window.location.href = 'mypage'
    })
    $(document).on('click','.myFavorite_com',function(){
        window.location.href = 'myPageRestaurant'
    })
    $(document).on('click','.myReview_com',function(){
        window.location.href = 'myPageReview'
    })
    $(document).on('click','.logout_com',function(){
        window.location.href = 'logout'
    })
    $(document).on('click', '.search_submit', function() {
        const e = $.Event('keydown', { keyCode: 13 }); // Enter 키
        $('#search_keyword').trigger(e);
    });

    $(document).on('click','.login_com',function(){
        login = true;
        $('.guest_menu_box').empty()
        $('.guest_menu_box').append(login_box)
        $('.guest_icon').css({backgroundColor: "#ffa500"})
    })
    $(document).on('click','.login_del',function(){
        login = false;
        $('.guest_menu_box').empty()
        $('.guest_menu_box').append(guest_box)
        $('.guest_icon').css({backgroundColor: "#ff00ff"})
    })

    // 페이지 내려가면 상단으로 이동 버튼
    $(window).scroll(function(){
        if(window.scrollY > 800) {
            $('.move_top').css({opacity: '1'})
        }
        else if(window.scrollY < 800) {
            $('.move_top').css({opacity: '0'})
        }
    })



});


