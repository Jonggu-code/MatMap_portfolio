//  자바스크립트
let offset = 135;
document.getElementById('review_leng').addEventListener('click',function(e){
    e.preventDefault();
    // 리뷰 박스로 이동 - id 값 바꿔줘야함
    const target = document.querySelector('#review_container')
    const t_posit = target.getBoundingClientRect().top + window.scrollY;
    // 이벤트창 유무에 따른 화면 offset 값
    if(bool_event == true){
        offset = 134;
    }
    else if(bool_event == false) {
        offset = 99;
    }
    const t_move = t_posit - offset
    window.scrollTo({top:t_move, behavior: 'smooth'})
})

// 제이쿼리
$(document).ready(function(){
    let reco_box_top = $('#menu_container').offset().top
    $('#reco_container').css({top: `${reco_box_top - 550}px`})

    // 태그 추천 맛집 박스 따라나니는 스크롤 이벤트
    $(window).scroll(function(){
        let reco_box = $('#reco_container').offset().top
        let test = window.scrollY
        console.log(reco_box, test, reco_box_top)
        if(test > reco_box_top - 120){
            $('#reco_container').css({top: `${test - 420}px`})
            $('.move_top').css({opacity: '1'})
        }
        else if(test < reco_box_top- 120){
            $('#reco_container').css({top: `${reco_box_top - 550}px`})
            $('.move_top').css({opacity: '0'})
        }
    });
    // 배너 이미지 관련 변수
    let banner_img_length = $('.banner_img').length

    // 배너에 이미지 넣어주는 반복문
    // for(i=0; i<banner_img_length; i++){
    //     $(`.count_${i}`).css({
    //         background: `url(./img/예시이미지/${i+1}.jpg) no-repeat center / cover`
    //     })
    // }

    let img_length = 29; // 식당에 포함된 이미지 총 length
    let curr_img_btn = 1; // 현재 보고있는 이미지 페이지
    // let img_more_btn_lock = Math.floor(img_length / 9); // 최종 이미지 불러올 시 더보기 버튼 lock
    let curr_img = 0; // 이미지 불러오기 경로(첫경로)
    let load_img = 9; // 이미지 불러오기 경로(끝경로)

    // // 이미지 div 9개 넣어주는 함수
    // function load_img_div(opt1, opt2){
    //     for(let i=opt1; i<opt2; i++){
    //         let tmp = `
    //             <div class="rest_img" style="background: url(./img/예시이미지/${i+1}.jpg) no-repeat center / cover;"></div>
    //         `
    //         $('.img_con_box').append(tmp)
    //     }
    // }
    // load_img_div(curr_img, load_img)

    // 더보기 버튼 누르면 사진 & div 더 넣어주는 클릭이벤트
    // $('.img_con_more_btn').click(function(){
    //     if(curr_img_btn < img_more_btn_lock){
    //         console.log(curr_img_btn, img_more_btn_lock)
    //         curr_img += 9
    //         load_img += 9
    //         curr_img_btn += 1
    //         load_img_div(curr_img, load_img)
    //     }

        // 이미지 갯수 한계에 다르면 더보기 버튼 삭제
        // else if(curr_img_btn = img_more_btn_lock){
        //     curr_img += 9
        //     load_img = img_length
        //     load_img_div(curr_img, load_img)
        //     $('.img_con_more_btn').remove()
        // }
    // })

    // 찜하기 버튼 클릭
    $(document).on('click', '.rest_save', function(){
        $('.rest_save > svg path').toggleClass('rest_save_act')

        const hasClass = $(this).hasClass('rest_favo');

        $(this).toggleClass('rest_favo');

        $.ajax({
            url: '/favorite',
            method: 'POST',
            data: {
                favorite: !hasClass
            },
            success: function(response) {
                console.log(response.message)
            },
            error: function(xhr, status, error) {
                console.error ('에러 발생: ', error);
                $(this).toggleClass('rest_favo', hasClass)
            }
        })
    })
})
