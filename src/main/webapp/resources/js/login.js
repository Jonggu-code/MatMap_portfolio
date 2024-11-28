function valid_chk(frm){
    let el_msg = document.getElementById('msg');
    if(frm.user_id.value.length == 0){
        setMsg(frm.user_id, "아이디를 입력하시오.");
        return false;
    }
    else if(frm.user_pw.value.length == 0){
        setMsg(frm.user_pw, "비밀번호를 입력하시오.");
        return false;
    }
    return true;
}

function setMsg(el,msg){
    document.getElementById('msg').innerHTML = `<b>${msg}</b>`
    if(el){
        el.select();
    }
}