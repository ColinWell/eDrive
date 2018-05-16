$(document).ready(function () {
    var p=$('#add-popup').prompt21();
    $.ajax({
        url: "/getAllEmergency.do",
        type: "POST",
        contentType:"application/json",
        success: function (jsonResult) {
            console.log(jsonResult);
            alert("yes");
            var item;
            $.each(jsonResult,function (i,result) {
                item="<tr><td>"+(i+1)+"</td><td>"+result['EmergencyContact_Name']+"</td><td>"+result['EmergencyContact_Phone']+"</td><td>"+result['EmergencyContact_Time']+"</td>";
                item+="<td><a class='update' style='cursor:pointer'>修改</a></td>"+"<td><a href=\"javascript:delUR("+result['EmergencyContact_Id']+")\">删除</a></td></tr>";
                $("#emergencyManageTBody").append(item);
            });
            btnBind();
        },
        error:function (jsonResult) {
            console.log("error");
            console.log(jsonResult);
        }
    });
    $(".add-popup").on("click",function () {
        p.getData(function (err,data) {
            console.log(err);
            console.log(data);
            $.ajax({
                url:"/addEmergency.do",
                type:"POST",
                contentType: "application/json", // 参数类型，必须有415
                data:JSON.stringify({
                    EmergencyContact_Name:data.name,
                    EmergencyContact_Phone:data.phone,
                    EmergencyContact_Time:data.time
                }),
                success:function (jsonResult) {
                    if(jsonResult == true){
                        alert("添加成功");
                        location.reload();
                    }else{
                        alert("添加失败");
                    }
                }
            })
        })
    });
});
window.onload=function () {
    btnBind();
}

save.onclick=function addEmergency() {
    var name=$("#name.Name").val();
    var phone=$("#name.phone").val();
    var time=$("#name.time").val();
    $.ajax({
        url:"addEmergency.do",
        type:"POST",
        contentType: "application/json", // 参数类型，必须有415
        data:JSON.stringify({
            EmergencyContact_Name:data.name,
            EmergencyContact_Phone:data.phone,
            EmergencyContact_Time:data.time
        }),
        success:function (jsonResult) {
            if(jsonResult == true){
                alert("添加成功");
                location.reload();
            }else{
                alert("添加失败");
            }
        }
    })
};
function doneClick(id) {
    var tr=document.getElementById(id).parentNode.parentNode;
    var eid=tr.cells[0];
    var name=tr.cells[1];
    var phone=tr.cells[2];
    var time=tr.cells[3];
    var updateBtn=tr.cells[4];
    //value change
    var ename=name.children[0].value;
    var ephone=phone.children[0].value;
    var etime=time.children[0].value;
    $.ajax({
        url:"/editEmergency.do",
        type:"POST",
        contentType: "application/json", // 参数类型，必须有415
        data:JSON.stringify({
            "EmergencyContact_Id":eid.innerHTML,
            "EmergencyContact_Name":ename,
            "EmergencyContact_Phone":ephone,
            "EmergencyContact_Time":etime
        }),
        success:function (jsonResult) {
            console.log(jsonResult);
            alert("修改成功");
            if(jsonResult == true) {
                name.innerHTML = ename;
                phone.innerHTML = ephone;
                time.innerHTML = etime;
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
            else {
                alert("修改失败");
                cancelClick('cancelBtn');
            }
        },
        error:function (jsonResult) {
            alert("修改失败");
            cancelClick(id);
        }
    })
}
function cancelClick(id) {
    var tr=document.getElementById(id).parentNode.parentNode;
    var name=tr.cells[1];
    var phone=tr.cells[2];
    var time=tr.cells[3];
    var updateBtn=tr.cells[4];
    name.innerHTML=name.children[0].attributes['defaultValue'].nodeValue;
    phone.innerHTML=phone.children[0].attributes['defaultValue'].nodeValue;
    time.innerHTML=time.children[0].attributes['defaultValue'].nodeValue;
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
function delUR(id) {
    if (confirm('确定要删除吗?') == true) {
        console.log(id);
        $.ajax({
            url:"/delE.do",
            type:"POST",
            contentType:"application/json",
            data:JSON.stringify({
                "EmergencyContact_Id":id
            }),
            success:function (jsonResult) {
                alert("删除成功");
                window.location.reload();
            },
            error:function (jsonResult) {
                alert("删除失败");
                window.location.reload();
            }
        });
    }
}
function btnBind() {
    $('.update').click(function () {
        $(".update").unbind();
        var tr=$(this).closest("tr");
        var name=tr.find("td:eq(1)");
        var phone=tr.find("td:eq(2)");
        var time=tr.find("td:eq(3)");
        var updateBtn=tr.find("td:eq(4)");
        var nameInp=document.createElement("input");
        nameInp.setAttribute("type","text");
        nameInp.setAttribute("id","nameInp");
        nameInp.setAttribute("value",name.html());
        nameInp.setAttribute("defaultValue",name.html());
        name.empty();
        name.append(nameInp);
        var phoneInp=document.createElement("input");
        phoneInp.setAttribute("type","text");
        phoneInp.setAttribute("id","phoneInp");
        phoneInp.setAttribute("value",phone.html());
        phoneInp.setAttribute("defaultValue",phone.html());
        phone.empty();
        phone.append(phoneInp);
        var timeInp=document.createElement("input");
        timeInp.setAttribute("type","text");
        timeInp.setAttribute("id","timeInp");
        timeInp.setAttribute("value",time.html());
        timeInp.setAttribute("defaultValue",time.html());
        time.empty();
        time.append(timeInp);
        //btn change
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
    })
    // $('.updateo').click(function () {
    //     $(".updateo").unbind();
    //     var tr=$(this).closest("tr");
    //     var updateBtn1=tr.find("td:eq(5)");
    //     var doneo=document.createElement("a");
    //     doneo.setAttribute("class","doneo");
    //     doneo.setAttribute("id","doneoBtn");
    //     doneo.innerHTML="确认";
    //     doneo.setAttribute("onclick","doneoClick('doneoBtn')");
    //
    //     var cancelo=document.createElement("a");
    //     cancelo.setAttribute("class","cancelo");
    //     cancelo.setAttribute("id","canceloBtn");
    //     cancelo.innerHTML("取消");
    //     cancelo.setAttribute("onclick","canceloClick('canceloBtn')");
    //
    //     updateBtn1.empty();
    //     updateBtn1.append(doneo);
    //     updateBtn1.append(cancleo);
    // })
}
