$(function(){
    //queryRInfo();
    $.ajax({
        url:"/getCByS.do",
        type:"POST",
        contentType:"application/json",
        success: function(jsonResult){
            var item;
            var type;
            var s;
            $.each(jsonResult,function (i,result) {
                if(result['Complaint_Status']==1){
                    s="已处理";
                }
                else {
                    s="未处理";
                }
                item="<tr><td>"+(i+1)+"</td><td>"+result['Complaint_Id']+"</td><td>"+result['Complaint_Name']+"</td><td>"+result['Complaint_Phone']+"</td>";
                item+="<td>"+result['Complaint_Content']+"</td><td>"+"</td><td><a class='update' style='cursor:pointer'>处理</a></td></tr>";
                $("#complaintManageTBody").append(item);
            });

            btnBind();
        },
        error:function (jsonResult) {
            console.log("error");
            console.log(jsonResult);
        }
    });
});
function queryRInfo() {
    if($("#repairApplicationManage").hashClass("dataTable")){
        $("#repairApplicationManage").dataTable().fnClearTable();
        $("#repairApplicationManage").dataTable().fnDestroy();
    }
    $("#repairApplicationManage").DataTable({
        // 是否允许检索
        "searching": false,
        // 是否允许排序
        "ordering": true,
        // 件数选择功能 默认true
        "lengthChange": false,
        // 件数选择下拉框内容
        "lengthMenu": [10, 25, 50, 75, 100],
        // 每页的初期件数 用户可以操作lengthMenu上的值覆盖
        "pageLength": 10,
        // 是否表示 "processing" 加载中的信息，这个信息可以修改
        "processing": true,

        "serverSide": true,
        // ajax选项 可以直接简单指定成请求的文件
        //"ajax": "data.json",
        // 也可以用对象来配置，更加灵活
        "ajax":{
            type:"POST",
            url:"queryRInfo.do",
            contentType:"application/json",
            async:false,
            data:function (d) {
                d.rDateS=$("input[name=rDateS]").val();
                d.rDateE=$("input[name=rDateE]").val();
                d.rType=$("select[name=rType] option:selected").attr("value");
                d.rStatus=$("select[name=rStatus] option:selected").attr("value");
                d.rPhone=$("input[name=rPhone]").val();
                d.rId=$("input[name=rId]").val();
                d.rName=$("input[name=rName]").val();
                return JSON.stringify(d);
            }
        },
        "columns": [
            {"data": "id", "bSortable": false},
            {"data": "roomId", "bSortable": false},
            {"data": "roomName", "bSortable": false},
            {"data": "date"},
            {"data": "type", "bSortable": false},
            {"data": "state", "bSortable": false}
        ],
    });
}
window.onload=function () {
    btnBind();
}
function doneClick(id) {
    var tr=document.getElementById(id).parentNode.parentNode;
    var rid=tr.cells[1];
    var Status=tr.cells[7];
    var updateBtn=tr.cells[8];
    var s="1";
    $.ajax({
        url:"/editC.do",
        type:"POST",
        contentType: "application/json", // 参数类型，必须有415
        data:JSON.stringify({
            "Complaint_Id":rid.innerHTML,
            "Complaint_Status":s
        }),
        success:function (jsonResult) {
            console.log(jsonResult);

            if(jsonResult == true) {
                alert("修改成功");
                window.location.reload();
            }
            else {
                alert("修改失败");
                window.location.reload();
            }
        },
        error:function (jsonResult) {
            alert("修改失败");
            window.location.reload();
        }
    })
}
function cancelClick(id) {
    var tr=document.getElementById(id).parentNode.parentNode;
    var rid=tr.cells[1];
    var Status=tr.cells[7];
    var updateBtn=tr.cells[8];
    var s="2";
    $.ajax({
        url:"/editC.do",
        type:"POST",
        contentType: "application/json", // 参数类型，必须有415
        data:JSON.stringify({
            "Complaint_Id":rid.innerHTML,
            "Complaint_Status":s
        }),
        success:function (jsonResult) {
            console.log(jsonResult);

            if(jsonResult == true) {
                alert("修改成功");
                window.location.reload();
            }
            else {
                alert("修改失败");
                window.location.reload();
            }
        },
        error:function (jsonResult) {
            alert("修改失败");
            window.location.reload();
        }
    })
}
function btnBind() {
    $(".update").click(function () {
        $(".update").unbind();
        var tr = $(this).closest("tr");
        var Status = tr.find("td:eq(7)");
        var updateBtn=tr.find("td:eq(8)");
        var done = document.createElement("a");
        done.setAttribute("class", "done");
        done.setAttribute("id", "doneBtn");
        done.innerHTML = "确认";
        done.setAttribute("onclick", "doneClick('doneBtn')");

        var cancel = document.createElement("a");
        cancel.setAttribute("class", "cancel");
        cancel.setAttribute("id", "cancelBtn")
        cancel.innerHTML = "暂缓";
        cancel.setAttribute("onclick", "cancelClick('cancelBtn');");

        updateBtn.empty();
        updateBtn.append(done);
        updateBtn.append(cancel);
    })

}
$(document).on('click', '#agreeA', function (e) {
    var target = e.target;
    var val;
    if (target.getAttribute('id') == 'agreeA') {
        val = target.parentNode.parentNode.getAttribute('data-target');
        console.log(val);
        val = JSON.parse(val);
    }
    var RepairApplication_Id = val.RepairApplication_Id;
    var objData = [
        { name: 'RepairApplication_Id', value: RepairApplication_Id }
    ];//post方式传送的数据
    $.ajax({

        type: "POST",
        url: "/updateRepairByStatus.do",
        data: objData,
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        success: function (data)  {
            alert(RepairApplication_Id);
            alert("success");
            window.location.href = "/jsp/RepairManage.jsp";
        }


    });
});