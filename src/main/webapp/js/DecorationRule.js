$(function () {
    $.ajax({
        url:"/getDRule.do",
        type:"POST",
        success:function (data) {
            alert("yes");
            var val=eval("("+data+")");
            var tbody=$('<tbody></tbody>');
            var tr=$('<tr></tr>');
            tr.append('<td>'+val.Rule_Main+'</td>>');
            tbody.append(tr);
            $('#oldRule tbody').replaceWith(tbody);
        }
    });

});

