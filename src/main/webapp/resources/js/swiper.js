var swiper;
var swiper_rank;

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
}

swiper_event()