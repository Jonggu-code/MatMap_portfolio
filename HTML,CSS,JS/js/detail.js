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

    // 이미지 사진 클릭하면 포토뷰어 등장시키는 이벤트

    $(document).on('click', '.rest_img', function(){
        $('body').addClass('photo_view')
        $('.photo_viewer').addClass('photo_active')

        let curr_rest_img = $(this).css("background")
        console.log(curr_rest_img)
        let curr_img_url = curr_rest_img.match(/url\("?(.*?)"?\)/)?.[1]
        console.log(curr_img_url)
        let photo = `<img src="${curr_img_url}" alt=""></img>`
        $('.photo_viewer').append(photo)
    })
    $(document).on('click', '.photo_viewer', function(){
        $(this).removeClass('photo_active')
        $('body').removeClass('photo_view')
        $('.photo_viewer').empty()
    })

    // 태그 추천 맛집 박스 따라나니는 스크롤 이벤트
    $(window).scroll(function(){
        let reco_box = $('#sub_box').offset().top
        let reco_pos = reco_box + $('#sub_box').outerHeight()
        let test = window.scrollY

        console.log(reco_box, reco_pos, test)
        
        if(test > reco_pos - 120 && window.innerWidth > 1250){
            $('#reco_container').css({top: `${test - 410}px`})
            $('.move_top').css({opacity: '1'})
        }
        else if(test > reco_pos - 120 && window.innerWidth < 1250){
            $('#reco_container').css({top: `${test - 320}px`})
            $('.move_top').css({opacity: '1'})
        }
        else if(test < reco_pos - 120){
            $('#reco_container').css({top: "106%"})
            $('.move_top').css({opacity: '0'})
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

    let img_length = 29; // 식당에 포함된 이미지 총 length
    let curr_img_btn = 1; // 현재 보고있는 이미지 페이지
    let img_more_btn_lock = Math.floor(img_length / 9); // 최종 이미지 불러올 시 더보기 버튼 lock
    let curr_img = 0; // 이미지 불러오기 경로(첫경로)
    let load_img = 9; // 이미지 불러오기 경로(끝경로)

    // 이미지 div 9개 넣어주는 함수
    function load_img_div(opt1, opt2){
        for(let i=opt1; i<opt2; i++){
            let tmp = `
                <div class="rest_img" style="background: url(./img/예시이미지/${i+1}.jpg) no-repeat center / cover;"></div>
            ` 
            $('.img_con_box').append(tmp)
        }
    }
    load_img_div(curr_img, load_img)

    // 더보기 버튼 누르면 사진 & div 더 넣어주는 클릭이벤트
    $('.img_con_more_btn').click(function(){
        if(curr_img_btn < img_more_btn_lock){
            console.log(curr_img_btn, img_more_btn_lock)
            curr_img += 9
            load_img += 9
            curr_img_btn += 1
            load_img_div(curr_img, load_img)
        }

        // 이미지 갯수 한계에 다르면 더보기 버튼 삭제
        else if(curr_img_btn = img_more_btn_lock){
            curr_img += 9
            load_img = img_length
            load_img_div(curr_img, load_img)
            $('.img_con_more_btn').remove()
        }
    })

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

    // 후기칸에서 이미지 더보기 버튼 클릭 이벤트

    let review_length = 14; // 식당에 포함된 리뷰 총 length
    let curr_rev_btn = 1; // 현재 보고있는 리뷰 페이지 (더보기 버튼 누르면 1씩 증가)
    let rev_more_btn_lock = Math.floor(review_length / 5); // 최종 후기 불러올 시 더보기 버튼 lock
    let curr_rev = 0; // 리뷰 불러오기 경로(시작단)
    let load_rev = 5; // 리뷰 불러오기 경로(끝단)

    // 후기칸에서 후기 더보기 버튼 클릭 이벤트

    // 이미지 리뷰 5개 넣어주는 함수
    function load_rev_div(opt1, opt2){
        for(let i=opt1; i<opt2; i++){
            // 리뷰칸에 5개씩 추가되는 함수 ()
            let tmp = `
                    <div class="rev_con_main_box">
                        <div class="rc_create_at">11월 13일</div>
                        <div class="rc_user_box">
                            <div class="rc_user_icon"></div>
                            <div class="rc_user_info">
                                <div id="user_name">무먹잘소</div>
                                <div id="user_info">
                                    <p>남</p>
                                    <p>/</p>
                                    <p>28세</p>
                                </div>
                            </div>
                        </div>
                        <!-- 유저가 준 평점 -->
                        <div class="rc_user_score_box">
                            <div class="con1">
                                <p>전체</p>
                                <p>4.8</p>
                            </div>
                            <div class="con1">
                                <p>고객응대</p>
                                <p>4.8</p>
                            </div>
                            <div class="con1">
                                <p>청결도</p>
                                <p>4.8</p>
                            </div>
                            <div class="con1">
                                <p>맛</p>
                                <p>4.8</p>
                            </div>
                        </div>
                        <!-- 유저가 적은 텍스트 -->
                        <div class="rc_user_txt_box">여긴 진짜… 시청역 직장인들의 숨은 맛집, 나만 알고싶은 맛집이 아닐까 생각한다.
진한 국물에 우동사리같은 면, 밥이 말아져서 나온다. 파도 듬뿍 올려져있다.

애성회관 한우곰탕의 킥은 ‘후추’다.
간혹 후추를 뿌리면 본연의 국물 맛이 가려지는 경우가 있는데. 애성회관 곰탕은 배가 된다.

24녘 7월 방문 기준
일반 곰탕은 고기가 3덩이, 특곰탕은 고기 8덩이 정도가 나온다. 가격은 2000원 차이난다.
밥이나 사리면의 양은 차이가 없다고 느껴진다.</div>
                        <!-- 유저가 먹은 메뉴 (후기 작성 페이지에서 선택) -->
                        <div class="re_menu_box">
                            <div class="review_menu">특곰탕</div>
                            <div class="review_menu">한우곰탕</div>
                        </div>
                        <!-- 유저가 올린 사진 (후기 작성 페이지에서 선택) -->
                        <div class="rc_img_box">
                            <div class="rc_img_box_scroll">
                            <!-- 리뷰어가 후기에 넣은 이미지 여기에 review_img 클래스 가진 상태로 append (아래는 참고 코드)-->

                                <div class="review_img"></div>
                                <div class="review_img"></div>
                                <div class="review_img"></div>
                                <div class="review_img"></div>
                                <div class="review_img"></div>
                                <div class="review_img"></div>
                                <div class="review_img"></div>
                                <div class="review_img"></div>
                                <div class="review_img"></div>
                                <div class="review_img"></div>
                            </div>
                        </div>
                    </div>
            ` 
            $('.rev_con_main').append(tmp)
        }
    }
    load_rev_div(0,5)

    $('.rev_con_more_btn').click(function(){
        if(curr_rev_btn < rev_more_btn_lock){
            console.log(curr_rev_btn, rev_more_btn_lock)
            curr_rev += 5
            load_rev += 5
            curr_rev_btn += 1
            load_rev_div(curr_rev, load_rev)
        }

        // 리뷰 갯수 한계에 다르면 더보기 버튼 삭제
        else if(curr_rev_btn = rev_more_btn_lock){
            curr_rev += 5
            load_rev = review_length
            load_rev_div(curr_rev, load_rev)
            $('.rev_con_more_btn').remove()
        }
    })
});

