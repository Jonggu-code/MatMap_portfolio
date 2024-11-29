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

document.getElementsByClassName('rev_create_btn')[0].addEventListener('click',function(){
    if(login == false){
        alert("로그인 후 작성해주세요.")
        window.location.href = "login.html";
    }
    else if(login == true){
        window.location.href = "review_page_1.html"
    }
})

// 제이쿼리
$(document).ready(function(){

    // 태그 추천 맛집 박스 따라나니는 스크롤 이벤트
    $(window).scroll(function(){
        let reco_box = $('#reco_container').offset().top
        let test = window.scrollY
        console.log(reco_box, test)
        if(test > 730){
            $('#reco_container').css({
                top: `${313 + (test - 730)}px`
            })
        }
        else if(test < 730){
            $('#reco_container').css({
                top: "313px"
            })
        }
    });
    // 배너 이미지 관련 변수
    let banner_img_length = $('.banner_img').length

    // 배너에 이미지 넣어주는 반복문
    for(i=0; i<banner_img_length; i++){
        $(`.count_${i}`).css({
            background: `url(./img/예시이미지/${i+1}.jpg) no-repeat center / cover`
        })
    }

    // 이미지 div 9개 넣어주는 함수
    function load_img_div(count1, count2){
        for(i=count1; i<count2; i++){
            let tmp = `
                <div class="rest_img" style="background: url(./img/예시이미지/${i}.jpg) no-repeat center / cover;"></div>
            `
            $('.img_con_box').append(tmp)
        }
    }
    load_img_div(1,10)

    // 더보기 버튼 누르면 사진 & div 더 넣어주는 클릭이벤트
    let curr_img = 10;
    let load_img = 19;
    $('.img_con_more_btn').click(function(){
        load_img_div(curr_img, load_img)
        curr_img += 9
        load_img += 9
    })



});

