<#include "../main_template.ftl"/>

<#macro content>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">${user.name} ${user.surname}</h1>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-info">
            <div class="panel-heading">
                Profile
                <a href="/users/${user.id}/dialog" class="subscribe" style="float: right">Write message</a>
                <input id="user" type="text" style="display: none" value="${user.id}">
            </div>
            <div class="panel-body">
                <#if communities?has_content>
                    <p class="lead">
                        <em>Communities:</em>
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
                <#if needyReq?has_content>
                    <p class="lead">
                        <em>As needy:</em>
                    </p>
                    <ul>
                        <#list needyReq as r>
                            <li>
                                <a href="/requests/${r.id}">
                                    Was helped
                                    <#if r.volunteer??>
                                        by ${r.volunteer.name} ${r.volunteer.surname}
                                    </#if>
                                    at ${r.createdAt}
                                </a>
                            </li>
                        </#list>
                    </ul>
                    <hr>
                </#if>
                <#if volunteerReq?has_content>
                    <p class="lead">
                        <em>As volunteer:</em>
                    </p>
                    <ul>
                        <#list volunteerReq as r>
                            <li>
                                <a href="/requests/${r.id}">
                                    Helped ${r.needy.name} ${r.needy.surname} at ${r.createdAt}
                                </a>
                            </li>
                        </#list>
                    </ul>
                    <hr>
                </#if>
                <p class="lead">
                    <em>Rating:</em>
                </p>
                <#if positive>
                    <button type="button" class="rating btn btn-link disabled fa fa-thumbs-o-up fa-2x">${rating}</button>
                <#else>
                    <button type="button" class="rating btn btn-link disabled fa fa-thumbs-o-down fa-2x">${rating}</button>
                </#if>
                <hr>
                <p class="lead">
                    <em>Your opinion</em>
                </p>
                <#if haveAssessment>
                    <#if havePositiveAssessment>
                        <button type="button" class="assess btn btn-link fa fa-thumbs-o-up fa-2x"></button>
                    <#else>
                        <button type="button" class="assess btn btn-link fa fa-thumbs-o-down fa-2x"></button>
                    </#if>
                <#else>
                    <button type="button" class="assess btn btn-link fa fa-thumbs-o-up fa-2x"></button>
                </#if>
            </div>
        </div>
    </div>
</div>
</#macro>

<@main title="${user.name}" scripts=["/resources/custom/profile.js"]/>