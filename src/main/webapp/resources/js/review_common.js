
$(document).ready(function(){

let star_bar = $('.review_star')

function click_pos(nural) {
    barWidth = nural.width(); //progress-bar의 너비
    barOffset = nural.offset().left; // progress-bar의 페이지 상의 왼쪽 경계
    clickX = event.pageX - barOffset;
    // 클릭한 위치의 X 좌표 (progress-bar 내에서)

    // 클릭 위치를 백분율로 계산
    click_position = (clickX / barWidth)
    // 원하는 값으로 환산
    click_position = Math.floor(click_position * 100)
}

// 별점 넣는 기능

$('.empty_star_main').click(function(){
    click_pos(star_bar)
    console.log(click_position)
    $(this).find('.fill_star').css({ width : `${click_position}%`})
    let score_txt = $(this).find('.fill_star').width() / 40
    if(click_position >= 98 ){
        $('.score_user_main').val('5.0')
    }
    else{
        $('.score_user_main').val(`${Math.floor(score_txt * 10) / 10}`)
    }
})
$('.empty_star_kind').click(function(){
    click_pos(star_bar)
    $(this).find('.fill_star').css({ width : `${click_position}%`})
    let score_txt = $(this).find('.fill_star').width() / 40
    if(click_position >= 98 ){
        $('.score_user_kind').val('5.0')
    }
    else{
        $('.score_user_kind').val(`${Math.floor(score_txt * 10) / 10}`)
    }
})
$('.empty_star_clean').click(function(){
    click_pos(star_bar)
    $(this).find('.fill_star').css({ width : `${click_position}%`})
    let score_txt = $(this).find('.fill_star').width() / 40
    if(click_position >= 98 ){
        $('.score_user_clean').val('5.0')
    }
    else{
        $('.score_user_clean').val(`${Math.floor(score_txt * 10) / 10}`)
    }
})
$('.empty_star_taste').click(function(){
    click_pos(star_bar)
    $(this).find('.fill_star').css({ width : `${click_position}%`})
    let score_txt = $(this).find('.fill_star').width() / 40
    if(click_position >= 98 ){
        $('.score_user_taste').val('5.0')
    }
    else{
        $('.score_user_taste').val(`${Math.floor(score_txt * 10) / 10}`)
    }
})

$('.menu_list > li').on('click',function(){
    $(this).toggleClass('menu_active')
})


// 이미지 드래그해서 넣을 수 있는 기능

let fileList = [];
let drag_zone = $('.img_box');

drag_zone.on('dragover', function(event){
    event.preventDefault();
    drag_zone.addClass('hover')
})
drag_zone.on('dragleave', function(){
    drag_zone.removeClass('hover')
})
drag_zone.on('drop', function(e){

    e.preventDefault();
    $(this).css({ opacity: '1', })
    const files = Array.from(e.originalEvent.dataTransfer.files)
    drag_zone.children().hide()

    files.forEach((file, index) => {
        if (file.type.startsWith('image/')) {
            const reader = new FileReader();
            reader.onload = function(e){
                
                fileList.push({file, id: Date.now() + index});
                const add_img = $(`
                    <div class="add_img_box" data-id="${Date.now() + index}">
                        <div class="remove_img"><svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M12.7,12l6.6,6.6l-0.7,0.7L12,12.7l-6.6,6.6l-0.7-0.7l6.6-6.6L4.6,5.4l0.7-0.7l6.6,6.6l6.6-6.6l0.7,0.7L12.7,12z"></path></g></svg></div>
                    </div>
                `);

                const $img = $('<img>').attr('src', e.target.result);
                add_img.append($img);
                drag_zone.append(add_img);
            }
            reader.readAsDataURL(file);
        }
        else {
            alert('이미지 파일만 업로드 해주세요.')
        }
    })
});

// + 버튼 클릭해서 넣기

$('.add_img_btn').on('click',function(){
    $('#add_img').click()
})
$('#add_img').on('change', function(event){
    event.preventDefault();
    const files = Array.from(event.target.files)
    drag_zone.children().hide()

    files.forEach((file, index) => {
        if (file.type.startsWith('image/')) {
            const reader = new FileReader();
            reader.onload = function(e){
                
                fileList.push({file, id: Date.now() + index});
                const add_img = $(`
                    <div class="add_img_box" data-id="${Date.now() + index}">
                        <div class="remove_img"><svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M12.7,12l6.6,6.6l-0.7,0.7L12,12.7l-6.6,6.6l-0.7-0.7l6.6-6.6L4.6,5.4l0.7-0.7l6.6,6.6l6.6-6.6l0.7,0.7L12.7,12z"></path></g></svg></div>
                    </div>
                `);

                const $img = $('<img>').attr('src', e.target.result);
                add_img.append($img);
                drag_zone.append(add_img);
            }
            reader.readAsDataURL(file);
        }
        else {
            alert('이미지 파일만 업로드 해주세요.')
        }
    })
    event.target.value = '';
})

$(document).on('click','.remove_img', function(){
    let add_img_box = $(this).closest('.add_img_box');
    let img_id = add_img_box.data('id');

    let del_curr_img = fileList.findIndex((item) => item.id === img_id)
    if(del_curr_img > -1){
        fileList.splice(del_curr_img, 1);
    }
    add_img_box.remove();

    if($('.img_box').find('.add_img_box').length == 0) {
        $('.img_box').children(':not(input)').show()
        $('img_box').css({flexWrap: 'wrap'})
    }
})

$('#review_txt').on('input', function(){
    let txt_leng = $('#review_txt').val().length
    $('.txt_limit > span:first-child').html(`${txt_leng}`)
})

});
