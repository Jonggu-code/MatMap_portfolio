var mapContainer = document.getElementById('map');
var mapOptions = {
    center: new kakao.maps.LatLng(37.500227, 127.037003),
    level: 4
};

var map = new kakao.maps.Map(mapContainer, mapOptions);

var points = [
    new kakao.maps.LatLng(37.509548471097105, 127.0159250467026),
    new kakao.maps.LatLng(37.483543665468815, 127.02584661602873),
    new kakao.maps.LatLng(37.49464806546498, 127.06312211842112),
    new kakao.maps.LatLng(37.51887323827288, 127.05029230479465)
]

var path = [
    new kakao.maps.LatLng(44.450417966392685, 115.02830047854872),
    new kakao.maps.LatLng(44.28630877074187, 139.7926056198738),
    new kakao.maps.LatLng(31.15587112811611 , 137.8097657229059),
    new kakao.maps.LatLng(31.13113650304299 , 117.00978991779066)
];

var hole = [
    new kakao.maps.LatLng(37.50956646665349, 127.0161060170077),
    new kakao.maps.LatLng(37.48345352987959, 127.02600487552272),
    new kakao.maps.LatLng(37.48588101573885, 127.0437461378583),
    new kakao.maps.LatLng(37.486186878467315, 127.04502399350439),
    new kakao.maps.LatLng(37.48651096333826, 127.0457478313627),
    new kakao.maps.LatLng(37.49457592447882, 127.06323513913003),
    new kakao.maps.LatLng(37.51885524228435 , 127.05023573368683),
    new kakao.maps.LatLng(37.51721889910142, 127.041241947207),
];

var polygon = new kakao.maps.Polygon({
    map: map,
    path: [path, hole],
    strokeWeight: 2,
    strokeColor: '#ff9625',
    strokeOpacity: 0.8,
    fillColor: '#5d350a',
    fillOpacity: 0.7 
})

kakao.maps.event.addListener(map, 'click', function(mouseEvent){
    var latlng = mouseEvent.latLng;

    var message = `클릭한 위치의 위도는 ${latlng.getLat()} 이고 경도는 ${latlng.getLng()} 입니다.`

    var resultDiv = document.getElementById('result');
    resultDiv.innerHTML = message;
});

// 중심좌표 
kakao.maps.event.addListener(map, 'center_changed', function(mouseEvent){
    var latlng = map.getCenter(); 

    var center_loc_x = latlng.getLat() // 맵 움직일때마다 중심좌표 표시 (위도)
    var center_loc_y = latlng.getLng() // 맵 움직일때마다 중심좌표 표시 (경도)

    var message = `현재 중심 좌표는 위도: ${center_loc_x}, 경도: ${center_loc_y} 입니다.`

    var resultDiv = document.getElementById('result');
    resultDiv.innerHTML = message;
});

var bounds = new kakao.maps.LatLngBounds();

for (i=0; i < points.length; i++){
    bounds.extend(points[i]);
}

function setBounds() {
    map.setBounds(bounds);
}
// 지도가 확대 또는 축소되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다

let isHandler = false;
kakao.maps.event.addListener(map, 'zoom_changed', function(event) {       
    var level = map.getLevel();

    var message = '현재 지도 레벨은 ' + level + ' 입니다';

    var resultDiv = document.getElementById('result');  
    resultDiv.innerHTML = message;
});

// 마커 생성단
var positions = [
    {
        title: '종찬식당',
        latlng: new kakao.maps.LatLng(37.50164685853944, 127.03563532174606)
    },
    {
        title: '종찬식당',
        latlng: new kakao.maps.LatLng(37.500892909011384, 127.04062227483718)
    },
    {
        title: '종찬식당',
        latlng: new kakao.maps.LatLng(37.49855254774154, 127.03342864632779)
    },
    {
        title: '종찬식당',
        latlng: new kakao.maps.LatLng(37.50331012355251, 127.03248077266952)
    },
    {
        title: '종찬식당',
        latlng: new kakao.maps.LatLng(37.504623364154185, 127.03976476192746)
    },
]

var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
var marker_list = [];
var overlay_list = [];

for (var i = 0; i < positions.length; i++) {
    var imageSize = new kakao.maps.Size(24, 35); 
    
    // 마커 이미지를 생성합니다    
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
    
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        title: positions[i].title,
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        image: markerImage, // 마커 이미지 
    });
    
    // CustomOverlay 콘텐츠
    var overlayContent = `
        <div class="custom_overlay">
            <div class="marker_item">
                <button class="custom_close_btn" onclick="closeAllOverlays(overlay_list)">x</button>
                <div class="main_item_box">
                    <div class="main_item_Lbox">
                        <a class="marker_img" href="./detail.html">1</a>
                    </div>
                    <div class="main_item_Rbox">
                        <div class="item_title">
        
                            <!-- 식당 이름 -->
                            <a class="item_name" href="./detail.html">종찬식당종찬식당종찬식당종찬식당종찬식당</a>
                            <div class="item_star">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m12 2 3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2Z"></path></svg>
        
                                <!-- 식당 전체 평점의 평균 -->
                                <p>4.9</p>
        
                                <!-- 식당에 관련된 후기 갯수 -->
                                <p>(21)</p>
                            </div>
                        </div>
        
                        <!-- 식당 주소정보 들어오는 단 -->
                        <p class="item_addr">
                            <i class="rest_addr_icon"></i>
                            서울시 강남구 역삼동 테헤란로
                        </p>
        
                        <!-- 식당 영업시간정보 들어오는 단 / 빈칸이면 "영업 정보 없음"-->
                        <p class="item_time">
                            <i class="rest_num_icon"></i>
                            화요일 09:00 ~ 22:00
                        </p>
        
                        <!-- 식당 정보 들어오는 단 / 빈칸이면 "매장 정보 없음"-->
                        <p class="item_info">
                            <i class="rest_info_icon"></i>
                            예약 가능, 포장 가능, 배달 불가
                        </p>
                    </div>
                </div>
            </div>
        </div>
    `;

    var customOverlay = new kakao.maps.CustomOverlay({
        content: overlayContent,
        position: positions[i].latlng,
        yAnchor: 1.25,
        removable: true
    });
    
    overlay_list.push(customOverlay);
    marker_list.push(marker);

    // 마커 클릭 시 실행할 함수
    kakao.maps.event.addListener(marker, 'click', function (index) {
        return function () {
            // 모든 오버레이 닫기
            closeAllOverlays(overlay_list);
            // 현재 마커의 커스텀 오버레이 열기
            overlay_list[index].setMap(map);
    
            // getContent()로 반환된 HTML 문자열을 DOM 객체로 변환
            var content = overlay_list[index].getContent();
            var tempDiv = document.createElement('div');
            tempDiv.innerHTML = content; // HTML 문자열을 DOM 객체로 변환
    
            // 닫기 버튼 이벤트 추가
            var closeButton = tempDiv.querySelector('.custom_close_btn');
            if (closeButton) {
                closeButton.addEventListener('click', function () {
                    overlay_list[index].setMap(null); // 오버레이 닫기
                });
            }
    
            // 새로운 DOM을 오버레이에 반영
            overlay_list[index].setContent(tempDiv.innerHTML);
        };
    }(i));
}

// 모든 오버레이 닫기
function closeAllOverlays(overlay_list) {
    for (var i = 0; i < overlay_list.length; i++) {
        overlay_list[i].setMap(null); // 오버레이를 지도에서 제거
    }
}

const btn = document.getElementsByClassName('focus_map')[0]
