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
    $('.fill_star').css({width:'100%'})

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


    document.querySelectorAll('.menu_item').forEach((item) => {
        item.addEventListener('click', (e) => {
            const menuId = e.target.getAttribute('data-id');
            updateSelectedMenu();
        });
    });

    function updateSelectedMenu() {
        const selectedMenus = [...document.querySelectorAll('.menu_active')]
            .map((item) => item.getAttribute('data-id'));

        const container = document.getElementById('selected_menus_container');
        container.innerHTML = ""; // 기존 태그 제거

        selectedMenus.forEach(menuId => {
            const input = document.createElement('input');
            input.type = "hidden";
            input.name = "selected_menu[]";
            input.value = menuId;
            container.appendChild(input);
        });
    }

    function validateSelectedMenus(e) {
        const selectedMenus = document.querySelectorAll('#selected_menus_container input');

        if (selectedMenus.length === 0) {
            console.log("preventDefault 호출 전");
            e.preventDefault(); // 기본 폼 제출 방지
            console.log("preventDefault 호출됨");
            alert("메뉴 1개 이상 선택해주세요.");
            return false;
        }

        console.log("폼 제출 허용");
        return true;
    }
    $('#wrap').on('submit',function(e){
        return validateSelectedMenus(e);
    })



// 이미지 추가 (input 버튼 클릭)
    let files = [];

    function img_upload(event) {
        let uploadList = Array.from(event.target.files); // 업로드된 파일 리스트
        let count = files.length + uploadList.length; // 현재 파일 개수 + 새로 추가된 파일 개수

        // 파일 최대 10개 제한
        if (count > 10) {
            const excess = count - 10;
            for (var i = 0; i < excess; i++) {
                uploadList.pop();
            }
            alert("이미지 파일 업로드는 최대 10장까지만 가능합니다.");
        }

        event.preventDefault();
        $(this).css({ opacity: '1' });
        $('.img_box').children(':not(.add_img_box)').hide();

        // 파일 필터링: 형식과 크기 제한
        uploadList.forEach((file, index) => {
            const validExtensions = ['image/jpeg', 'image/png', 'image/jpg'];
            const maxFileSize = 10 * 1024 * 1024; // 10MB

            if (!validExtensions.includes(file.type)) {
                alert("이미지 파일은 JPG, JPEG, PNG 형식만 업로드 가능합니다.");
                return;
            }
            if (file.size > maxFileSize) {
                alert("파일 크기는 10MB를 초과할 수 없습니다.");
                return;
            }
            if (files.some(existingFile => existingFile.name === file.name)) {
                alert("이미 추가된 파일입니다.");
                return;
            }

            // 파일이 유효하면 처리
            const uniqueId = `${Date.now()}_${Math.random().toString(36).substring(2, 10)}`;
            const reader = new FileReader();

            reader.onload = function (e) {
                files.push({ file, id: uniqueId, name: file.name });
                const add_img = $(`
                <div class="add_img_box" data-id="${uniqueId}">
                    <div class="remove_img">
                        <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet">
                            <g>
                                <path d="M12.7,12l6.6,6.6l-0.7,0.7L12,12.7l-6.6,6.6l-0.7-0.7l6.6-6.6L4.6,5.4l0.7-0.7l6.6,6.6l6.6-6.6l0.7,0.7L12.7,12z"></path>
                            </g>
                        </svg>
                    </div>
                </div>
            `);

                const $img = $('<img>').attr('src', e.target.result);
                add_img.append($img);
                $('.img_box').append(add_img);
            };
            reader.readAsDataURL(file);
        });
    }

// 등록버튼 클릭 시, AJAX로 파일 서버 전송
    function btn_submit(){

        console.log("서버로 전송할 fileList 배열:", files);

        const formData = new FormData();
        files.forEach(({ file }) => {
            formData.append('files', file); // 서버로 보낼 파일 추가
        });

        $.ajax({
            url: 'http://localhost:8080/app/reviewWrite2', // 스프링 서버 업로드 엔드포인트
            method: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                alert('파일 업로드 성공!');
                console.log('서버 응답:', response);
            },
            error: function (xhr, status, error) {
                alert('파일 업로드 실패: ' + error);
                console.error('서버 에러:', xhr.responseText);
            }
        });
    }

// + 버튼 클릭해서 넣기
    $('.add_img_btn').on('click',function(){
        $('#add_img').val("")
        $('#add_img').click()
    })
    $('#add_img').on('change', function(event){
        img_upload(event)
    })

// 삭제버튼 클릭해서 이미지 지우기
    $(document).on('click','.remove_img', function(){
        let add_img_box = $(this).closest('.add_img_box');
        let img_id = add_img_box.data('id');

        let del_curr_img = files.findIndex((item) => item.id === img_id)
        if (del_curr_img > -1) {
            files.splice(del_curr_img, 1);
            console.log(`파일 ID: ${img_id} 삭제 완료.`, files); // 삭제 후 배열 상태 확인
        } else {
            console.warn(`파일 ID: ${img_id}를 찾을 수 없습니다.`);
        }

        // DOM에서 이미지 요소 제거
        add_img_box.remove();

        if($('.img_box').find('.add_img_box').length == 0) {
            $('.img_box').children(':not(input)').show()
            $('img_box').css({flexWrap: 'wrap'})
        }
    })

// 등록버튼 클릭시 서버에 등록
    $('#btn_submit').on('click', function(){
        btn_submit()
    })

    $('#review_txt').on('input', function(){
        let txt_leng = $('#review_txt').val().length
        $('.txt_limit > span:first-child').html(`${txt_leng}`)
    })

    $('.btn_etc').on('click', function(){
        console.log("서버로 전송할 fileList 배열:", files)
    })
    $('.btn_test').on('click', function(){
        $('#add_img').click()
    })
})