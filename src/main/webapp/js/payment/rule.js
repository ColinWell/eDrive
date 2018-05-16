/**
 * Created by Colin on 2018/3/20.
 */
$(document).ready(function () {
    var p = $("#add-popup").prompt21();
    queryPaymentRule();
    $(".add-popup").on("click", function () {
        p.getData(function (err, data) {
            console.log(err);
            console.log(data);
            $.ajax({
                url:"/payment/addRule.do",
                type:"POST",
                contentType: "application/json", // 参数类型，必须有415
                data:JSON.stringify({
                    ruleType:data.name.type,
                    ruleVersion:data.name.version,
                    ruleFee:data.name.fee
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
        });
    });

});

function update(obj) {
    // var tr = $(this).closest("tr");
    // var feeTd = tr.find("td:eq(4)");
    // var fee = feeTd.html();
    // var ipt = document.createElement("input");
    // ipt.setAttribute("type","number");
    // ipt.setAttribute("id","temp_fee");
    // ipt.setAttribute("value",fee);
    // tr.append(ipt);
    // $(this).innerHTML = "Sure";

    var fee=prompt("请输入修改后的费用金额",obj.ruleFee);
    if (fee!=null && fee!="")
    {
        if(fee == obj.ruleFee){
            alert("金额不变，不做修改");
        }
        else{
            $.post("/payment/editRuleFee.do",{id:obj.id,fee:fee},function (jsonResult) {
                if(jsonResult == true){
                    alert("修改成功");
                    location.reload();
                }
                else{
                    alert("修改失败");
                }
            })
        }
    }
    else{
        return;
    }
}

function addPaymentRule() {

}
function queryPaymentRule() {
    if($("#paymentRuleQuery").hasClass("dataTable")){
        $("#paymentRuleQuery").dataTable().fnClearTable();
        $("#paymentRuleQuery").dataTable().fnDestroy();
    }
    $("#paymentRuleQuery").DataTable({
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
        "ajax": {
            type:"POST",
            // url可以直接指定远程的json文件，或是MVC的请求地址 /Controller/Action
            url:"/payment/queryPaymentRule.do",
            contentType:"application/json",
            async:false,
            // 传给服务器的数据，可以添加我们自己的查询参数
            data:function(d){
                d.id = "123";
                d.dateBegin = $("input[name=dateBegin]").val();
                d.dateEnd = $("input[name=dateEnd]").val();
                d.ruleType = $("select[name=ruleType] option:selected").attr("value");
                d.ruleVersion = $("input[name=ruleVersion]").val();
                return JSON.stringify(d);
            }
            //用于处理服务器端返回的数据。 dataSrc是DataTable特有的
            // dataSrc: function (json) {
            //     // if (myJson.timeout) {
            //     //     return "";
            //     // }
            //     console.log("dataSrc "+json);
            //     return json;
            // }
            // success:function (result) {
            //     console.log(result);
            //     return result;
            // }
        },
        "columns": [
            {"data": "id", "bSortable": false},
            {"data": "ruleName"},
            {"data": "ruleVersion"},
            {"data": "ruleDate"},
            {"data": "ruleFee"}
        ],
        "columnDefs": [
            {
                "targets": [3],
                "data": "ruleDate",
                "render": function(data, type, full) {
                    var date = $.formatDate("yyyy/MM/dd",new Date(data));
                    return date;
                }
            },
            {
                "targets": [5],
                "data": "id",
                "render": function(data, type, row, full) {
                    return "<a  href='javascript:update("+JSON.stringify(row)+")'>Update</a>";
                }
            }
        ]
    });
}
