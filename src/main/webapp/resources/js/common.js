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
}, 3000)

// top 버튼 누르면 페이지 상단으로 이동
let move_top_btn = document.getElementsByClassName('move_top')[0]

move_top_btn.addEventListener('click', function(e){
    e.preventDefault();
    window.scrollTo({top:0, behavior: 'smooth'})
});

$(document).ready(function(){

    $(document).on('click', '.search_submit', function() {
        const e = $.Event('keydown', { keyCode: 13 }); // Enter 키
        $('#search_keyword').trigger(e);
    });


    // let guest_box = `
    //     <p class="login_com">로그인</p>
    //     <p class="register">회원가입</p>
    // `
    // let login_box = `
    //     <p class="my_page">마이페이지</p>
    //     <p class="my_rest">내가 찜한 맛집</p>
    //     <p class="my_review">내가 작성한 후기</p>
    //     <p class="login_del">로그아웃</p>
    // `
    // $('.guest_menu_box').append(guest_box)
    //
    $(document).on('click','.login_com',function(){
        window.location.href = 'http://localhost:8080/login'
    })
    $(document).on('click','.register_com',function(){
        window.location.href = 'http://localhost:8080/join'
    })
    $(document).on('click','.myPage_com',function(){
        window.location.href = 'http://localhost:8080/mypage2'
    })
    $(document).on('click','.myFavorite_com',function(){
        window.location.href = 'http://localhost:8080/myPageRestaurant'
    })
    $(document).on('click','.myReview_com',function(){
        window.location.href = 'http://localhost:8080/myPageReview'
    })
    $(document).on('click','.logout_com',function(){
        window.location.href = 'http://localhost:8080/logout'
    })
    //
    // $(document).on('click','.login_com',function(){
    //     login = true;
    //     $('.guest_menu_box').empty()
    //     $('.guest_menu_box').append(login_box)
    //     $('.guest_icon').css({backgroundColor: "#ffa500"})
    // })
    // $(document).on('click','.login_del',function(){
    //     login = false;
    //     $('.guest_menu_box').empty()
    //     $('.guest_menu_box').append(guest_box)
    //     $('.guest_icon').css({backgroundColor: "#ff00ff"})
    // })


    let fact = true;

    // 게스트 메뉴 클릭시 이벤트
    $(document).on('click','.guest_menu', function(){
        if(fact == true){
            fact = false;
            $(this).addClass('guest_menu_active');
            $('.guest_menu_box').addClass('guest_menu_box_active')
        }
        else if(fact == false){
            fact = true;
            $(this).removeClass('guest_menu_active');
            $('.guest_menu_box').removeClass('guest_menu_box_active')
        }
    });


    // 페이지 내려가면 상단으로 이동 버튼
    $(window).scroll(function(){
        console.log(window.scrollY)
        if(window.scrollY > 800) {
            $('.move_top').css({opacity: '1'})
        }
        else if(window.scrollY < 800) {
            $('.move_top').css({opacity: '0'})
        }
    })


    // 점수에 따른 별점 width 값 조정
    let star_ave = $('.score_ave').html()
    let star_kind = $('.score_kind').html()
    let star_clean = $('.score_clean').html()
    let star_taste = $('.score_taste').html()
    let star_main = $('.score_main').html()

    function set_star_width(){
        setTimeout(() => {
            $('#star_ave').css({width: `${star_ave * 20}%`})
            $('#star_kind').css({width: `${star_kind * 20}%`})
            $('#star_clean').css({width: `${star_clean * 20}%`})
            $('#star_taste').css({width: `${star_taste * 20}%`})
            $('.fill_star').css({width: `${star_main * 20}%`})
            console.log(star_main * 20)
        }, 100);
    }
    set_star_width()

    let bar_biggood = $('#bar_biggood').html()
    let bar_good = $('#bar_good').html()
    let bar_common = $('#bar_common').html()
    let bar_bad = $('#bar_bad').html()
    let bar_bigbad = $('#bar_bigbad').html()
    let bar_main = 23;

    function set_bar_width(){
        console.log((bar_biggood / bar_main) * 100)
        setTimeout(() => {
            $('#score_biggood').css({width: `${(bar_biggood / bar_main) * 100}%`})
            $('#score_good').css({width: `${(bar_good / bar_main) * 100}%`})
            $('#score_common').css({width: `${(bar_common / bar_main) * 100}%`})
            $('#score_bad').css({width: `${(bar_bad / bar_main) * 100}%`})
            $('#score_bigbad').css({width: `${(bar_bigbad / bar_main) * 100}%`})
        }, 100);
    }
    set_bar_width()

    $(".review_edit").on("click", function (event) {
        event.preventDefault(); // 기본 동작 방지
        if (confirm("내가 작성한 리뷰를 수정하시겠습니까?")) {
            window.location.href = $(this).attr("href"); // '예'를 선택하면 리뷰 작성 페이지로 이동
        }
    });

    $(".review_delete").on("click", function (event) {
        event.preventDefault(); // 기본 동작 방지
        if (confirm("내가 작성한 리뷰를 삭제하시겠습니까?")) {
            window.location.href = $(this).attr("href");
            // '예'를 선택하면 리뷰 삭제
        }
    });

    $(".rest_delete").on("click", function (event) {
        event.preventDefault(); // 기본 동작 방지
        if (confirm("내가 찜한 맛집 리스트에서 삭제하시겠습니까?")) {
            window.location.href = $(this).attr("href");
            // '예'를 선택하면 찜한 식당에서 삭제
        }
    });
});

