/**
 * Created by Colin on 2018/3/20.
 */
$(document).ready(function () {
    var p = $("#add-popup").prompt21();
    $.ajax({
        url:"/payment/queryRuleDis.do",
        type:"POST",
        contentType: "application/json", // 参数类型，必须有415
        success:function (jsonResult) {
            console.log(jsonResult);
            var item;
            $.each(jsonResult,function(i,result){
                item = "<tr><td>"+(i+1)+"</td><td>"+result['roomId']+"</td><td>"+result['roomName']+"</td><td>"+result['roomSize']+"</td>";
                item += "<td>"+result['propertyVer']+"</td><td>"+result['utilitiesVer']+"</td><td>"+result['parkingVer']+"</td>";
                item += "<td><a class='update' style='cursor:pointer'>修改</a></td></tr>";
                $("#ruleTBody").append(item);
            });
            btnBind();
        },
        error:function (jsonResult) {
            console.log("error");
            console.log(jsonResult);
        }
    });
    $.ajax({
        url:"/payment/queryUserAndRoom.do",
        type:"POST",
        contentType: "application/json", // 参数类型，必须有415
        success:function (jsonResult) {
            console.log(jsonResult);
            var item;
            $.each(jsonResult,function(i,result){
                item = "<tr><td>"+result['id']+"</td><td>"+result['roomId']+"</td><td>"+result['roomName']+"</td><td>"+result['userId']+"</td>";
                item += "<td><a href=\"javascript:delUR("+result['id']+")\">删除</a></td></tr>";
                $("#urTBody").append(item);
            });
        },
        error:function (jsonResult) {
            console.log("error");
            console.log(jsonResult);
        }
    });
    loadSelectList();
    $(".add-popup").on("click", function () {
        p.getData(function (err, data) {
            console.log(err);
            console.log(data);
            $.ajax({
                url:"/payment/addRelation.do",
                type:"POST",
                contentType: "application/json", // 参数类型，必须有415
                data:JSON.stringify({
                    userId:data.userList,
                    roomId:data.roomList
                }),
                success:function (jsonResult) {
                    if(jsonResult == true){
                        alert("添加成功");
                        location.reload();
                    }else{
                        alert("添加失败,注意已存在的用户-房屋关系");
                    }
                }
            })
        });
    });
});

function loadSelectList() {
    $.get("/user/getRoomList.do",function(data,status){
        console.log(data+status);
        var selector = $("select[name = roomList]");
        $.each(data,function (i,room) {
            var child = "<option value='"+room.roomId+"'>"+room.roomName+"</option>";
            selector.append(child);
        });
    });
    $.get("/user/getUserList.do",function(data,status){
        console.log(data+status);
        var selector = $("select[name = userList]");
        $.each(data,function (i,user) {
            var child = "<option value='"+user.id+"'>"+user.userName+"</option>";
            selector.append(child);
        });
    });
}
window.onload=function(){
    btnBind();
}
function delUR(id) {
    if (confirm('确定要删除吗?') == true) {
        $.get("/payment/delUR.do?id="+id, function (data, status) {
            console.log("Data: " + data + "\nStatus: " + status);
            if(data == true){
                location.reload(true);
            }
            else{
                alert("删除失败");
            }
        });
    }
}
function doneClick(id) {
    var tr = document.getElementById(id).parentNode.parentNode;
    var roomId = tr.cells[1];
    var proVer = tr.cells[4];
    var utiVer = tr.cells[5];
    var parVer = tr.cells[6];
    var updateBtn = tr.cells[7];

    // value change
    var propertyVer = proVer.children[0].value;
    var utilitiesVer = utiVer.children[0].value;
    var parkingVer = parVer.children[0].value;

    $.ajax({
        url:"/payment/editRuleDis.do",
        type:"POST",
        contentType: "application/json", // 参数类型，必须有415
        data:JSON.stringify({
            "id":roomId.innerHTML,
            "propertyVer":propertyVer,
            "utilitiesVer":utilitiesVer,
            "parkingVer":parkingVer
        }),
        success:function (jsonResult) {
            console.log(jsonResult);
            if(jsonResult == true){
                alert("修改成功");
                proVer.innerHTML = propertyVer;
                utiVer.innerHTML = utilitiesVer;
                parVer.innerHTML = parkingVer;
                // btn change
                var a = document.createElement("a");
                a.setAttribute("class","update");
                a.setAttribute("style","cursor:pointer");
                a.innerHTML = "修改";
                while(updateBtn.hasChildNodes()) //当还存在子节点时 循环继续
                {
                    updateBtn.removeChild(updateBtn.firstChild);
                }
                updateBtn.append(a);
                btnBind();
            }
            else{
                alert("修改失败");
                cancelClick('cancelBtn');
            }
        },
        error:function (jsonResult) {
            alert("修改失败");
            cancelClick(id);
        }
    });
}
function cancelClick(id) {
    var tr = document.getElementById(id).parentNode.parentNode;
    var proVer = tr.cells[4];
    var utiVer = tr.cells[5];
    var parVer = tr.cells[6];
    var updateBtn = tr.cells[7];

    // value change
    proVer.innerHTML = proVer.children[0].attributes['defaultValue'].nodeValue;
    utiVer.innerHTML = utiVer.children[0].attributes['defaultValue'].nodeValue;
    parVer.innerHTML = parVer.children[0].attributes['defaultValue'].nodeValue;

    // btn change
    var a = document.createElement("a");
    a.setAttribute("class","update");
    a.setAttribute("style","cursor:pointer");
    a.innerHTML = "修改";
    while(updateBtn.hasChildNodes()) //当还存在子节点时 循环继续
    {
        updateBtn.removeChild(updateBtn.firstChild);
    }
    updateBtn.append(a);
    btnBind();
}

function btnBind() {
    $('.update').click(function () {
        $(".update").unbind();
        var tr = $(this).closest("tr");
        var proVer = tr.find("td:eq(4)");
        var utiVer = tr.find("td:eq(5)");
        var parkingVer = tr.find("td:eq(6)");
        var updateBtn = tr.find("td:eq(7)");
        var proInp = document.createElement("input");
        proInp.setAttribute("type","number");
        proInp.setAttribute("id","proInp");
        proInp.setAttribute("value",proVer.html());
        proInp.setAttribute("defaultValue",proVer.html());
        proVer.empty();
        proVer.append(proInp);
        var utiInp = document.createElement("input");
        utiInp.setAttribute("type","number");
        utiInp.setAttribute("id","utiInp");
        utiInp.setAttribute("value",utiVer.html());
        utiInp.setAttribute("defaultValue",utiVer.html());
        utiVer.empty();
        utiVer.append(utiInp);
        var parkingInp = document.createElement("input");
        parkingInp.setAttribute("type","number");
        parkingInp.setAttribute("id","parkingInp");
        parkingInp.setAttribute("value",parkingVer.html());
        parkingInp.setAttribute("defaultValue",parkingVer.html());
        parkingVer.empty();
        parkingVer.append(parkingInp);
        // btn change
        var done = document.createElement("a");
        done.setAttribute("class","done");
        done.setAttribute("id","doneBtn");
        done.innerHTML="确认";
        done.setAttribute("onclick","doneClick('doneBtn')");

        var cancel = document.createElement("a");
        cancel.setAttribute("class","cancel");
        cancel.setAttribute("id","cancelBtn")
        cancel.innerHTML = "取消";
        cancel.setAttribute("onclick","cancelClick('cancelBtn');");

        updateBtn.empty();
        updateBtn.append(done);
        updateBtn.append(cancel);
    });
}