彩票兑奖转账告警
<#list params as params>
    告警时间：${params.check_time?string('yyyy-MM-dd HH:mm:ss')}
    统计时间区间：${params.time_interval}
    昨日转账金额：${(params.transfer_ytd)/100}
    今日转账金额：${(params.transfer_td)/100}
    <#if (params.transfer_ytd != 0)>偏差比例：${(params.percent)*100}%</#if>
    查看详情：${(params.alarm_url)}

</#list>