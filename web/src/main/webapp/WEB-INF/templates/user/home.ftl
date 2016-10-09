<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>

<#include "../main_template.ftl"/>

<#macro content>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Home</h1>
        </div>
    </div>

    <div class="row">
    <div class="col-lg-12">
        <@sec.authorize access="hasRole('ROLE_USER')">
            <#if penFriends?has_content>
                <p class="lead">
                    <em>Active dialogs with:</em>
                </p>
                <ul>
                    <#list penFriends as f>
                        <li>
                            <a href="/users/${f.id}/dialog">${f.name} ${f.surname}</a>
                        </li>
                    </#list>
                </ul>
                <hr>
            </#if>
            <#if communities?has_content>
                <p class="lead">
                    <em>My communities:</em>
                </p>
                <ul>
                    <#list communities as c>
                        <li>
                            <a href="/communities/${c.id}">${c.name}</a>
                        </li>
                    </#list>
                </ul>
                <hr>
            </#if>
            <p class="lead">
                <em>My rating:</em>
            </p>
            <#if positive>
            <button type="button" class="btn btn-link disabled fa fa-thumbs-o-up fa-2x">${rating}</button>
        <#else>
            <button type="button" class="btn btn-link disabled fa fa-thumbs-o-down fa-2x">${rating}</button>
        </#if>
        </@sec.authorize>
        <@sec.authorize access="hasRole('ROLE_ADMIN')">
            <#if recentCommunities?has_content>
                <p class="lead">
                    <em>Recently founded communities:</em>
                </p>
                <ul>
                    <#list recentCommunities as c>
                        <li>
                            <a href="/communities/${c.id}">${c.name}</a>
                        </li>
                    </#list>
                </ul>
                <hr>
            </#if>
            <#if recentRequests?has_content>
                <p class="lead">
                    <em>Recently created requests:</em>
                </p>
                <ul>
                    <#list recentRequests as r>
                        <li>
                            <a href="/requests/${r.id}">
                                Issued by ${r.needy.name} ${r.needy.surname} at ${r.createdAt}
                            </a>
                        </li>
                    </#list>
                </ul>
            </#if>
        </@sec.authorize>
    </div>
</div>
</#macro>

<@main title="Home"/>


<#--<div class="flot-chart">-->
    <#--<div class="flot-chart-content" id="flot-pie-chart" style="padding: 0px; position: relative;">-->
        <#--<canvas class="flot-base" width="473" height="400" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 473px; height: 400px;"></canvas>-->
        <#--<canvas class="flot-overlay" width="473" height="400" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 473px; height: 400px;"></canvas>-->
        <#--<div class="legend">-->
            <#--<div style="position: absolute; width: 57px; height: 64px; top: 5px; right: 5px; opacity: 0.85; background-color: rgb(255, 255, 255);"> </div>-->
            <#--<table style="position:absolute;top:5px;right:5px;;font-size:smaller;color:#545454">-->
                <#--<tbody>-->
                    <#--<tr>-->
                        <#--<td class="legendColorBox">-->
                            <#--<div style="border:1px solid #ccc;padding:1px">-->
                                <#--<div style="width:4px;height:0;border:5px solid rgb(237,194,64);overflow:hidden"></div>-->
                            <#--</div>-->
                        <#--</td>-->
                        <#--<td class="legendLabel">Series 0</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td class="legendColorBox">-->
                            <#--<div style="border:1px solid #ccc;padding:1px">-->
                                <#--<div style="width:4px;height:0;border:5px solid rgb(175,216,248);overflow:hidden"></div>-->
                            <#--</div>-->
                        <#--</td>-->
                        <#--<td class="legendLabel">Series 1</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td class="legendColorBox">-->
                            <#--<div style="border:1px solid #ccc;padding:1px">-->
                                <#--<div style="width:4px;height:0;border:5px solid rgb(203,75,75);overflow:hidden"></div>-->
                            <#--</div>-->
                        <#--</td>-->
                        <#--<td class="legendLabel">Series 2</td>-->
                    <#--</tr>-->
                    <#--<tr>-->
                        <#--<td class="legendColorBox">-->
                            <#--<div style="border:1px solid #ccc;padding:1px">-->
                                <#--<div style="width:4px;height:0;border:5px solid rgb(77,167,77);overflow:hidden"></div>-->
                            <#--</div>-->
                        <#--</td>-->
                        <#--<td class="legendLabel">Series 3</td>-->
                    <#--</tr>-->
                <#--</tbody>-->
            <#--</table>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->