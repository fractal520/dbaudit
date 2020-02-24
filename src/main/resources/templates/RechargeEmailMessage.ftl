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
    <h2>异常充值</h2>
    <table id="customers">
        <tr>
            <th>充值流水</th>
            <th>充值前卡片余额</th>
            <th>充值金额</th>
            <th>充值后卡片余额</th>
            <th>交易时间</th>
        </tr>
        <#list trans as params>
        <tr>
            <td>${(params.transeq)}</td>
            <td>${(params.balance)/100}</td>
            <td>${(params.amount)/100}</td>
            <td>${(params.afterbalance)/100}</td>
            <td>${(params.transtime)}</td>
        </tr>
        </#list>
    </table>
</div>
</body>
</html>