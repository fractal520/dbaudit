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
    <h2>彩票兑奖转账告警</h2>
    <table id="customers">
        <tr>
            <th>告警时间</th>
            <th>统计时间区间</th>
            <th>昨日转账金额</th>
            <th>今日转账金额</th>
            <th>偏差比例</th>
        </tr>
        <#list trans as params>
        <tr>
            <td>${params.check_time?string('yyyy-MM-dd HH:mm:ss')}</td>
            <td>${(params.time_interval)}</td>
            <td>${(params.transfer_ytd)/100}</td>
            <td>${(params.transfer_td)/100}</td>
            <#if (params.transfer_ytd != 0) >
                <td>${(params.percent)*100}%</td>
            </#if>
        </tr>
        </#list>
        <#list trans as params><a href=${(params.alarm_url)}>查看详情：${(params.alarm_url)}</a></#list>
    </table>

</div>
</body>
</html>