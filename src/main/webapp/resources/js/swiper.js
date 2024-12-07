var swiper;
var swiper_rank;
var swiper_mypage_1;
var swiper_mypage_2;


function swiper_event(){
    swiper = new Swiper(".banner_swiper0", {
        slidesPerView: 3,
        slidesPerGroup: 1,
        // breakpoints: {},
        spaceBetween: 30,
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        },
        navigation: {
            nextEl: ".swiper-button-next0",
            prevEl: ".swiper-button-prev0",
        },
        effect: 'slide',
        speed: 700
    });

    swiper_rank = new Swiper(".rank_swiper0", {
        slidesPerView: 4,
        slidesPerGroup: 1,
        // breakpoints: {},
        spaceBetween: 10,
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        },
        navigation: {
            nextEl: ".swiper-button-next0",
            prevEl: ".swiper-button-prev0",
        },
        effect: 'slide',
        speed: 700
    });

    swiper_mypage_1 = new Swiper('.mypage_swiper0', {
        slidesPerView: 2,
        slidesPerGroup: 1,
        spaceBetween: 20, // 슬라이드 간 간격
        pagination: {
            el: '.swiper-pagination',
            clickable: true, // 페이지 번호 클릭 가능
        },
        effect: 'slide',
        speed: 700
    });

    swiper_mypage_2 = new Swiper('.mypage_swiper1', {
        slidesPerView: 2,
        slidesPerGroup: 1,
        spaceBetween: 20, // 슬라이드 간 간격
        pagination: {
            el: '.swiper-pagination',
            clickable: true, // 페이지 번호 클릭 가능
        },
        effect: 'slide',
        speed: 700
    });
}

swiper_event()