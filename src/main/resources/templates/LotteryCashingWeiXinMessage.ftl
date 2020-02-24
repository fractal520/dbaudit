【兑奖交易未及时处理】
<#list params as params>
${params.getcompanyName(params.companyId)}兑奖设备异常，兑奖交易处理延时达${params.getTimeDelay()?c}分钟，累计待处理交易${params.num}笔，请检查设备状态。

</#list>