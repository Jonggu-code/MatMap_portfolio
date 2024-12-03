// 헤더에서 로그인 메뉴 클릭시 포커스 이벤트
let guest = document.getElementsByClassName('guest_menu')[0]
let guest_box = document.getElementsByClassName('guest_menu_box')[0]
guest.addEventListener('focus', function(){
    guest.classList.add('guest_menu_active');
    guest_box.classList.add('guest_menu_box_active')
});
guest.addEventListener('blur', function(){
    guest.classList.remove('guest_menu_active');
    guest_box.classList.remove('guest_menu_box_active')
});

$(document).ready(function(){

    let login = false;
    
    // 영업 정보 빈칸이면 "영업 정보 없음" / 아니면 정보 넣어주기
    let rest_time;
    if(rest_time == ""){
        $('.item_time').html("영업 정보 없음")
    }
    else {
        $('.item_time').html(rest_time)
    }

    // 매장 정보 빈칸이면 "매장 정보 없음" / 아니면 정보 넣어주기
    let rest_info;
    if(rest_info == ""){
        $('.item_info').html("매장 정보 없음")
    }
    else {
        $('.item_info').html(rest_info)
    }

    // 음식점 메뉴 빈칸이면 div 삭제 / 아니면 정보 넣어주기
    let rest_menu;
    if(rest_menu == ""){
        $('.main_item_bot').hide()
    }
    else{
        $('.main_item_bot').append(rest_menu)
    }

    // 비로그인, 로그인 상태 시 헤더 노출
    let guest_list = `
        <p class="login_com">로그인</p>
        <p>회원가입</p>
    `
    let login_list = `
        <p>마이페이지</p>
        <p>마이 맛집 </p>
        <p>마이 후기</p>
        <p class="login_del">로그아웃</p>
    `
    $('.guest_menu_box').append(guest_list)

    $(document).on('click','.login_com',function(){
        login = true;
        $('.guest_menu_box').empty()
        $('.guest_menu_box').append(login_list)
        $('.guest_icon').css({backgroundColor: "#814b11"})
    })
    $(document).on('click','.login_del',function(){
        login = false;
        $('.guest_menu_box').empty()
        $('.guest_menu_box').append(guest_list)
        $('.guest_icon').css({backgroundColor: "#ff00ff"})
    })

    $(document).on('click', '.choose_box > p',function(){
        $('.choose_box').removeClass('choose_box_act')
        $(this).closest('.choose_box').addClass('choose_box_act')
        $('.map_box_L svg').removeClass('choose_act')
        $(this).closest('.choose_box').children('svg').addClass('choose_act')
    })
    
    // 지도표시지역 선택시 하단창 뜨는 이벤트
    $(document).on('click', '.choose_location > p',function(){
        $('.location_box').toggleClass('loca_box_act')
    })
    $(document).on('click', '.c_addr_name',function(){
        $('.c_addr_name').removeClass('c_addr_act')
        $(this).addClass('c_addr_act')
    })
    $(document).on('click', '.d_addr_name',function(){
        $('.map_box_L svg').removeClass('choose_act')
        $('.location_box').removeClass('loca_box_act')
        $('.choose_box').removeClass('choose_box_act')
        $('.choose_location > p').html(`${$(this).html()}`)
    })

    // 정렬방식 선택시 하단창 뜨는 이벤트
    $(document).on('click', '.choose_align > p',function(){
        $('.align_box').toggleClass('align_box_act')
    })
    $(document).on('click', '.align_menu',function(){
        $('.map_box_L svg').removeClass('choose_act')
        $('.align_box').removeClass('align_box_act')
        $('.choose_box').removeClass('choose_box_act')
        $('.choose_align > p').html(`${$(this).html()}`)
    })

    // 음식점 태그 선택시 하단창 뜨는 이벤트
    $(document).on('click', '.choose_tag > p',function(){
        $('.tag_box').toggleClass('tag_box_act')
    })
    $(document).on('click', '.tag_menu',function(){
        $('.map_box_L svg').removeClass('choose_act')
        $('.tag_box').removeClass('tag_box_act')
        $('.choose_box').removeClass('choose_box_act')
        $('.choose_tag > p').html(`${$(this).html()}`)
    })
});