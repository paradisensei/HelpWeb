<#include "../main_template.ftl"/>

<#macro content>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Dialog with ${friend.name} ${friend.surname}</h1>
    </div>
</div>

<div class="row">
    <div class="col-lg-8">
                <div class="chat-panel panel panel-info">
                    <div class="panel-heading">
                        <i class="fa fa-comments fa-fw"></i>
                        Chat
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <ul class="chat">
                            <#list messages as message>
                                <#if message.sender.id == friend.id>
                                    <li class="right clearfix">
                                        <span class="chat-img pull-right">
                                            <img src="http://placehold.it/50/FA6F57/fff" alt="User Avatar" class="img-circle" />
                                        </span>
                                        <div class="chat-body clearfix">
                                            <div class="header">
                                                <small class=" text-muted">
                                                    <i class="fa fa-clock-o fa-fw"></i>${message.createdAt}</small>
                                                <strong class="pull-right primary-font">${friend.name}</strong>
                                            </div>
                                            <p class="pull-right">${message.text}</p>
                                        </div>
                                    </li>
                                <#else>
                                    <li class="left clearfix">
                                        <span class="chat-img pull-left">
                                            <img src="http://placehold.it/50/55C1E7/fff" alt="User Avatar" class="img-circle" />
                                        </span>
                                        <div class="chat-body clearfix">
                                            <div class="header">
                                                <strong class="primary-font">You</strong>
                                                <small class="pull-right text-muted">
                                                    <i class="fa fa-clock-o fa-fw"></i>${message.createdAt}</small>
                                            </div>
                                            <p>${message.text}</p>
                                        </div>
                                    </li>
                                </#if>
                            </#list>
                        </ul>
                    </div>
                    <!-- /.panel-body -->
                    <div class="panel-footer">
                        <div class="input-group">
                            <input id="btn-input" type="text" class="text form-control input-sm" placeholder="Type your message here..." />
                            <span class="input-group-btn">
                                <input id="friend" type="text" style="display: none" value="${friend.id}">
                                <button class="send btn btn-warning btn-sm" id="btn-chat">
                                    Send
                                </button>
                            </span>
                        </div>
                    </div>
                    <!-- /.panel-footer -->
                </div>
    </div>
</div>
</#macro>

<@main title="Dialog" scripts=["/resources/custom/dialog.js"]/>