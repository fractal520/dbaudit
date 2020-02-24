<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>消息通知</title>
</head>

<style type="text/css">
    table {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        width: 100%;
        border-collapse: collapse;
    }

    td, th {
        font-size: 1em;
        border: 1px solid #5B4A42;
        padding: 3px 7px 2px 7px;
    }

    th {
        font-size: 1.1em;
        text-align: center;
        padding-top: 5px;
        padding-bottom: 4px;
        background-color: #24A9E1;
        color: #ffffff;
    }
</style>
<body>
<div>
    <h2>兑奖交易未及时处理，请确认扫码设备状态</h2>
    <table id="customers">
        <tr>
            <th>发行方</th>
            <th>待处理交易</th>
            <th>最早申请时间</th>
        </tr>
        <#list trans as params>
        <tr>
            <td>${(params.getcompanyName(params.companyId))}</td>
            <td>${(params.num)}</td>
            <td>${(params.createTime)}</td>
        </tr>
        </#list>
    </table>
</div>
</body>
</html>