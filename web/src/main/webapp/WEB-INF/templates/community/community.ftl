<#include "../main_template.ftl"/>

<#macro content>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Community info</h1>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-info">
            <div class="panel-heading">
                Community info
                <input id="community" type="text" style="display: none" value="${community.id}">
                <#if membership>
                    <a href="#" class="unsubscribe" style="float: right">Unsubscribe</a>
                <#else>
                    <a href="#" class="subscribe" style="float: right">Subscribe</a>
                </#if>
            </div>
            <div class="panel-body">
                <p class="lead">
                    <strong>Name:</strong> <em>${community.name}</em>
                </p>
                <p class="lead">
                    <strong>Description:</strong> <em>${community.description}</em>
                </p>
                <p class="lead">
                    <strong>Founder:</strong> <em>${community.founder.name} ${community.founder.surname}</em>
                </p>
                <p class="lead">
                    <strong>Created at:</strong> <em>${community.createdAt}</em></p>
                <#if membership>
                    <hr>
                    <p class="lead">
                        <em>News:</em>
                    </p>
                    <ul class="news">
                        <#list news as n>
                            <li>
                                <blockquote>
                                    <p>${n.text}</p>
                                    <small>
                                        ${n.author.name} ${n.author.surname} at ${n.createdAt}
                                    </small>
                                </blockquote>
                            </li>
                        </#list>
                    </ul>
                    <p class="lead">
                        <em>Add news:</em>
                    </p>
                    <div class="form-group">
                        <textarea name="text" class="text form-control" rows="5" cols="30"></textarea>
                    </div>
                    <div class="form-group">
                        <button type="button" class="send btn btn-primary btn-outline">Send</button>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</div>
</#macro>

<@main title="Request info" scripts=["/resources/custom/community.js"]/>