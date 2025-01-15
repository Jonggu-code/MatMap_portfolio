$(document).ready(function(){
    let banner_img_length = $('.banner_img').length

    // 배너에 이미지 넣어주는 반복문
    for(i=0; i<banner_img_length; i++){
        $(`.count_${i}`).css({
            background: `url(./img/예시이미지/${i+1}.jpg) no-repeat center / cover`
        })
    }
})