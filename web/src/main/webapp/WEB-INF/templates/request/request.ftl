<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>

<#include "../main_template.ftl"/>

<#macro content>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Request info</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-10">
            <div class="panel panel-info">
                <div class="panel-heading">
                    Request info
                    <#if request.status == "PENDING" && request.needy.id != principal.id>
                        <a id="help" href="#" style="float: right">Help!</a>
                    </#if>
                    <#if request.status != "CLOSED" && request.needy.id == principal.id>
                        <a id="close" href="#" style="float: right">Close</a>
                    </#if>
                    <input id="request" type="text" style="display: none" value="${request.id}">
                </div>
                <div class="panel-body">
                    <p class="lead">
                        <strong>Needy:</strong>
                        <em>
                            <#if request.needy.id == principal.id>
                                You
                            <#else>
                                ${request.needy.name} ${request.needy.surname}
                            </#if>
                        </em>
                    </p>
                    <p class="lead">
                        <strong>Address:</strong> <em>${request.address}</em>
                    </p>
                    <p class="lead">
                        <strong>Created at:</strong> <em>${request.createdAt}</em>
                    </p>
                    <p class="lead">
                        <strong>Type of service:</strong> <em>${request.serviceType.representation}</em>
                    </p>
                    <p class="lead">
                        <strong>Status:</strong>
                        <em class="status">${request.status.representation}</em>
                    </p>
                    <p class="lead">
                        <strong>Volunteer:</strong>
                        <em>
                            <#if request.volunteer??>
                                <#if request.volunteer.id == principal.id>
                                    You
                                <#else>
                                    ${request.volunteer.name} ${request.volunteer.surname}
                                </#if>
                            <#else>
                                No yet
                            </#if>
                        </em>
                    </p>
                    <hr>

                    <#-- Google maps -->
                    <#if request.volunteer?? && principal.id == request.volunteer.id>
                        <p class="lead">
                            <em>Show path:</em>
                        </p>
                        <div class="form-group">
                            <input type="text" class="address form-control" placeholder="Enter your address">
                            <p class="help-block" style="display: none;">Unreachable address. Make another try!</p>
                        </div>
                        <div class="form-group">
                            <button type="button" class="path btn btn-primary btn-outline">Show path</button>
                        </div>

                        <#-- Needy location to display on Google map view -->
                        <input id="latitude" type="text" style="display: none" value="${request.latitude}">
                        <input id="longitude" type="text" style="display: none" value="${request.longitude}">
                        <#-- Google map view -->
                        <div id="map" style="width: 100%; height: 400px;"></div>
                        <hr>
                    </#if>

                    <p class="lead">
                        <em>Comments:</em>
                    </p>
                    <ul class="comments">
                        <#list request.comments as comment>
                            <li>
                                <blockquote>
                                    <p>${comment.text}</p>
                                    <small>
                                    ${comment.author.name} ${comment.author.surname} at ${comment.createdAt}
                                    </small>
                                </blockquote>
                            </li>
                        </#list>
                    </ul>
                    <#if principal.id == request.needy.id || request.volunteer?? && principal.id == request.volunteer.id>
                        <p class="lead">
                            <em>Add new comment on this request:</em>
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
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA7rnCajr0BEK6i-H2G6twiYW_2hJPchOc&callback=initMap"
        async defer></script>
</#macro>

<@main title="Request info" scripts=["/resources/custom/request.js"]/>