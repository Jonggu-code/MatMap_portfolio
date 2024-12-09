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


    // $(document).on('click', '.rest_img', function(){
    //     $('body').addClass('photo_view')
    //     $('.photo_viewer').addClass('photo_active')
    //
    //     let curr_rest_img = $(this).css("background")
    //     console.log(curr_rest_img)
    //     let curr_img_url = curr_rest_img.match(/url\("?(.*?)"?\)/)?.[1]
    //     console.log(curr_img_url)
    //     let photo = `<img src="${curr_img_url}" alt=""></img>`
    //     $('.photo_viewer').append(photo)
    // })
    // $(document).on('click', '.photo_viewer', function(){
    //     $(this).removeClass('photo_active')
    //     $('body').removeClass('photo_view')
    //     $('.photo_viewer').empty()
    // })

    // 태그 추천 맛집 박스 따라나니는 스크롤 이벤트
    $(window).scroll(function() {
        let reco_box = $('#sub_box').offset().top
        let reco_pos = reco_box + $('#sub_box').outerHeight()
        let test = window.scrollY

        console.log(reco_box, reco_pos, test)

        if (test > reco_pos - 120 && window.innerWidth > 1250) {
            $('#reco_container').css({top: `${test - 410}px`})
            $('.move_top').css({opacity: '1'})
        } else if (test > reco_pos - 120 && window.innerWidth < 1250) {
            $('#reco_container').css({top: `${test - 320}px`})
            $('.move_top').css({opacity: '1'})
        } else if (test < reco_pos - 120) {
            $('#reco_container').css({top: "106%"})
            $('.move_top').css({opacity: '0'})
        }
    })

    // // 찜하기 버튼 클릭
    // $(document).on('click', '.rest_save', function(){
    //     $('.rest_save > svg path').toggleClass('rest_save_act')
    //
    //     const hasClass = $(this).hasClass('rest_favo');
    //
    //     $(this).toggleClass('rest_favo');
    //
    //     $.ajax({
    //         url: '/favorite',
    //         method: 'POST',
    //         data: {
    //             favorite: !hasClass,
    //         },
    //         success: function(response) {
    //             console.log(response.message)
    //         },
    //         error: function(xhr, status, error) {
    //             console.error ('에러 발생: ', error);
    //             $(this).toggleClass('rest_favo', hasClass)
    //         }
    //     })
    // })
})
