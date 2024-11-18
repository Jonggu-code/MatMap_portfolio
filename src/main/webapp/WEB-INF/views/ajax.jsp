<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
    <input type="text">
    <h1>{name: "abc", age: 10}</h1>
    <button id="btn_send">보내기</button>
    <hr>

    <div id="getData"> </div>

<script>

    $(document).ready(function(){
        let person={name:"abc",age:10};
        let person2 = {};

        $('#btn_send').click(function(){
            $.ajax({
                url:'/app/ajax',
                type:'POST',
                headers: {"content-type":"application/json"},
                dataType: 'text',
                data: JSON.stringify(person),
                success:function(result){
                    person2=JSON.parse(result);
                    alert("받은거:  " + result);

                    $("#getData").html("name="+person2.name+", age="+person2.age);
                }
                , error:function() {
                    alert("에러");
                }
            });

            alert("111")
        });
    })

</script>

</body>
</html>
