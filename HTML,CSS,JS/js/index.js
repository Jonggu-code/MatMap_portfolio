$(document).ready(function () {
  // 돋보기 버튼 클릭시 폼 제출
  $(document).on("click", ".search_submit", function () {
    const e = $.Event("keydown", { keyCode: 13 }); // Enter 키
    $("#search_keyword").trigger(e);
  });

  let login = false;

  // 영업 정보 빈칸이면 "영업 정보 없음" / 아니면 정보 넣어주기
  let rest_time;
  if (rest_time == "") {
    $(".item_time").html("영업 정보 없음");
  } else {
    $(".item_time").html(rest_time);
  }

  // 매장 정보 빈칸이면 "매장 정보 없음" / 아니면 정보 넣어주기
  let rest_info;
  if (rest_info == "") {
    $(".item_info").html("매장 정보 없음");
  } else {
    $(".item_info").html(rest_info);
  }

  // 음식점 메뉴 빈칸이면 div 삭제 / 아니면 정보 넣어주기
  let rest_menu;
  if (rest_menu == "") {
    $(".main_item_bot").hide();
  } else {
    $(".main_item_bot").append(rest_menu);
  }

  // 비로그인, 로그인 상태 시 헤더 노출
  // 비로그인, 로그인 상태 시 헤더 노출
  let guest_box = `
        <p class="login_com">로그인</p>
        <p class="register">회원가입</p>
    `;
  let login_box = `
        <p class="my_page">마이페이지</p>
        <p class="my_rest">마이 맛집 </p>
        <p class="my_review">마이 후기</p>
        <p class="login_del">로그아웃</p>
    `;
  $(".guest_menu_box").append(guest_box);

  $(document).on("click", ".register", function () {
    window.location.href = "./register.html";
  });
  $(document).on("click", ".my_page", function () {
    window.location.href = "./mypage.html";
  });
  $(document).on("click", ".my_rest", function () {
    window.location.href = "./list_page_restaurant.html";
  });
  $(document).on("click", ".my_review", function () {
    window.location.href = "./list_page_review.html";
  });

  $(document).on("click", ".login_com", function () {
    login = true;
    $(".guest_menu_box").empty();
    $(".guest_menu_box").append(login_box);
    $(".guest_icon").css({ backgroundColor: "#ffa500" });
  });
  $(document).on("click", ".login_del", function () {
    login = false;
    $(".guest_menu_box").empty();
    $(".guest_menu_box").append(guest_box);
    $(".guest_icon").css({ backgroundColor: "#ff00ff" });
  });

  // focus 풀기
  $(document).on("click", "#map", function () {
    $(".choose_box > p").blur();
  });

  // 클릭 이벤트에 공통적으로 들어가야되는 옵션 모음
  function common_control() {
    $(".choose_box").removeClass("choose_box_act");
    $(".map_box_L svg").removeClass("choose_act");
    $(".align_box").removeClass("align_box_act");
    $(".tag_box").removeClass("tag_box_act");
    $(".location_box").removeClass("loca_box_act");
  }

  // 각 옵션 선택시에 대한 함수 (닫히고 열리고)
  let fact = true;
  function choose_control(element, option1, option2) {
    $(".choose_box")
      .not(element.closest(".choose_box"))
      .removeClass("choose_box_act");
    $(".map_box_L svg")
      .not(element.closest(".choose_box").find("svg"))
      .removeClass("choose_act");
    if (fact == true) {
      fact = false;
      element.closest(".choose_box").addClass("choose_box_act");
      element.closest(".choose_box").children("svg").addClass("choose_act");
      option1.addClass(option2);
    } else if (fact == false) {
      fact = true;
      element.closest(".choose_box").removeClass("choose_box_act");
      element.closest(".choose_box").children("svg").removeClass("choose_act");
      option1.removeClass(option2);
    }
  }
  // 게스트메뉴 클릭시 하단창 뜨는 이벤트
  $(document).on("click", ".guest_menu", function () {
    if (fact == true) {
      fact = false;
      $(this).addClass("guest_menu_active");
      $(".guest_menu_box").addClass("guest_menu_box_active");
    } else if (fact == false) {
      fact = true;
      $(this).removeClass("guest_menu_active");
      $(".guest_menu_box").removeClass("guest_menu_box_active");
    }
  });

  // 지도표시지역 선택시 하단창 뜨는 이벤트
  $(document).on("click", ".choose_location > p", function () {
    common_control();
    choose_control($(this), $(".location_box"), "loca_box_act");
    fact = true;
  });
  $(document).on("click", ".c_addr_name", function () {
    $(".c_addr_name").removeClass("c_addr_act");
    $(this).addClass("c_addr_act");
  });
  $(document).on("click", ".d_addr_name", function () {
    $(".map_box_L svg").removeClass("choose_act");
    $(".location_box").removeClass("loca_box_act");
    $(".choose_box").removeClass("choose_box_act");
    $(".choose_location > p").html(`${$(this).html()}`);
  });

  // 정렬방식 선택시 하단창 뜨는 이벤트
  $(document).on("click", ".choose_align > p", function () {
    common_control();
    choose_control($(this), $(".align_box"), "align_box_act");
    fact = true;
  });
  $(document).on("click", ".align_menu", function () {
    $(".map_box_L svg").removeClass("choose_act");
    $(".align_box").removeClass("align_box_act");
    $(".choose_box").removeClass("choose_box_act");
    $(".choose_align > p").html(`${$(this).html()}`);
  });

  // 음식점 태그 선택시 하단창 뜨는 이벤트
  $(document).on("click", ".choose_tag > p", function () {
    common_control();
    choose_control($(this), $(".tag_box"), "tag_box_act");
    fact = true;
  });
  $(document).on("click", ".tag_menu", function () {
    $(".map_box_L svg").removeClass("choose_act");
    $(".tag_box").removeClass("tag_box_act");
    $(".choose_box").removeClass("choose_box_act");
    $(".choose_tag > p").html(`${$(this).html()}`);
  });

  // pagination 관련 함수
  $(document).on("click", ".page", function () {
    $(".page").removeClass("page_act");
    $(this).addClass("page_act");
  });

  let all_restaurant = 170; // 총 음식점 length 가 담기면 된다.
  let all_rest_length = Math.ceil(all_restaurant / 20); // 페이지 번호 생성
  let limit_page = Math.ceil(all_restaurant / 100); // 페이지 5개 기준 생기는 페이지네이션
  let curr_page = 1; // 현재 페이지
  let pos = 0; // 페이지 옮기는 용도의 변수
  let prev_btn_lock = true; // 이전페이지 버튼 lock
  let next_btn_lock = true; // 다음페이지 버튼 lock

  function create_page() {
    for (let i = 0; i < all_rest_length; i++) {
      let tmp = `<a href="#" id="page_no${i + 1}" class="page">${i + 1}</a>`;
      $(".page_box").append(tmp);
    }
    $("#page_no1").addClass("page_act");
  }

  create_page();

  // 페이지 수가 5개 이하일때 양쪽 버튼 둘다 lock
  if (all_rest_length < 5) {
    prev_btn_lock = false;
    next_btn_lock = false;
  }

  // 페이지 수가 5개 초과일때 (6개 이상일때) next 버튼 on
  if (all_rest_length > 5) {
    $("#next_page").addClass("page_btn_act");
  }

  function page_control(option1, option2) {
    curr_page += option1;
    pos += option2;
    $(".page").css({ left: `${pos}px` });
  }

  $(document).on("click", "#prev_page", function () {
    if (!prev_btn_lock) return;

    if (curr_page == 2) {
      page_control(-1, 200);
      prev_btn_lock = false;
      next_btn_lock = true;
      $("#next_page").addClass("page_btn_act");
      $(this).removeClass("page_btn_act");
    } else {
      next_btn_lock = true;
      $("#next_page").addClass("page_btn_act");
      $(this).addClass("page_btn_act");
      page_control(-1, 200);
    }
  });

  $(document).on("click", "#next_page", function () {
    if (!next_btn_lock) return;

    if (curr_page == limit_page - 1) {
      page_control(1, -200);
      next_btn_lock = false;
      prev_btn_lock = true;
      $("#prev_page").addClass("page_btn_act");
      $(this).removeClass("page_btn_act");
      return;
    } else {
      prev_btn_lock = true;
      $("#prev_page").addClass("page_btn_act");
      $(this).addClass("page_btn_act");
      page_control(1, -200);
    }
  });
});
