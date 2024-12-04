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

    // 지도의 현재 레벨을 얻어옵니다
    if(level < 6) {
        level = map.getLevel();
    }
    else if(level >= 6 && !isHandler){
        isHandler = true;
        alert('동작 실행');
        setBounds()

    }

    
    var message = '현재 지도 레벨은 ' + level + ' 입니다';

    var resultDiv = document.getElementById('result');  
    resultDiv.innerHTML = message;
});


// 마커 생성단
let rest_name = '카카오 아이디'

var positions = [
    {
        title: '카카오',
        content:'<div class="wrap">' + 
        '    <div class="info">' + 
        '        <div class="title">' + 
        rest_name + 
        '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
        '        </div>' + 
        '        <div class="body">' + 
        '            <div class="img">' +
        '                <img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/thumnail.png" width="73" height="70">' +
        '           </div>' + 
        '            <div class="desc">' + 
        '                <div class="ellipsis">제주특별자치도 제주시 첨단로 242</div>' + 
        '                <div class="jibun ellipsis">(우) 63309 (지번) 영평동 2181</div>' + 
        '                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' + 
        '            </div>' + 
        '        </div>' + 
        '    </div>' +    
        '</div>',
        latlng: new kakao.maps.LatLng(37.50164685853944, 127.03563532174606)
    },
    {
        title: '임호준띠',
        content: '<div>임호준띠</div>',
        latlng: new kakao.maps.LatLng(37.500892909011384, 127.04062227483718)
    },
    {
        title: '심재용띠',
        content: '<div>심재용띠</div>',
        latlng: new kakao.maps.LatLng(37.49855254774154, 127.03342864632779)
    },
    {
        title: '곽채연띠',
        content: '<div>곽채연띠</div>',
        latlng: new kakao.maps.LatLng(37.50331012355251, 127.03248077266952)
    },
    {
        title: '강주헌띠',
        content: '<div>강주헌띠</div>',
        latlng: new kakao.maps.LatLng(37.504623364154185, 127.03976476192746)
    },
]

var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 

var marker_list = [];
for (var i=0; i < positions.length; i++){
    var imageSize = new kakao.maps.Size(24, 35); 
    
    // 마커 이미지를 생성합니다    
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
    
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image : markerImage, // 마커 이미지 
    });

    marker_list.push(marker)

    var infowindow = new kakao.maps.InfoWindow({
        content: positions[i].content
    })

    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
}

function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}

const btn = document.getElementsByClassName('focus_map')[0]

btn.addEventListener('click', function(){
    // delete positions[0,1,2,3,4]
    // console.log(positions)

    for(let i=0; i < positions.length; i++){
        // marker.setMap(null)
        // console.log(marker.positions, marker.title)
        marker_list[i].setMap(null);
    }
})