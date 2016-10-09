<#import "../macro/requests_table.ftl" as mac/>

<#if type??>
    <@mac.req_table requests=requests type=type/>
<#else>
    <@mac.req_table requests=requests/>
</#if>