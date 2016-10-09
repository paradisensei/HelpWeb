<#list messages as message>
    <#if my??>
        <li class="left clearfix">
            <span class="chat-img pull-left">
                <img src="http://placehold.it/50/55C1E7/fff" alt="User Avatar" class="img-circle" />
            </span>
            <div class="chat-body clearfix">
                <div class="header">
                    <strong class="primary-font">You</strong>
                    <small class="pull-right text-muted">
                        <i class="fa fa-clock-o fa-fw"></i>${message.createdAt?string('HH:mm:ss')}</small>
                </div>
                <p>${message.text}</p>
            </div>
        </li>
    <#else>
        <li class="right clearfix">
            <span class="chat-img pull-right">
                <img src="http://placehold.it/50/FA6F57/fff" alt="User Avatar" class="img-circle" />
            </span>
            <div class="chat-body clearfix">
                <div class="header">
                    <strong class="pull-right primary-font">${message.sender.name}</strong>
                    <small class=" text-muted">
                        <i class="fa fa-clock-o fa-fw"></i>${message.createdAt?string('HH:mm:ss')}</small>
                </div>
                <p class="pull-right">${message.text}</p>
            </div>
        </li>
    </#if>
</#list>