<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>

<#include "../main_template.ftl"/>

<#macro content>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">All users</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-info">
                <div class="panel-heading">
                    All users
                    <@sec.authorize access="hasRole('ROLE_ADMIN')">
                        <a href="/users/pdf" target="_blank" style="float: right">generate pdf</a>
                    </@sec.authorize>
                </div>
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Surname</th>
                                    <th>Email</th>
                                    <th>Status</th>
                                    <th></th>
                                    <@sec.authorize access="hasRole('ROLE_USER')">
                                        <th></th>
                                    </@sec.authorize>
                                </tr>
                            </thead>
                            <tbody>
                                <#list users as user>
                                    <tr>
                                        <td>${user.name}</td>
                                        <td>${user.surname}</td>
                                        <td class="email">${user.email}</td>
                                        <td class="status">${user.status.representation}</td>
                                        <@sec.authorize access="hasRole('ROLE_ADMIN')">
                                            <td>
                                                <#if user.status == "ACTIVE">
                                                    <button type="button" class="ban btn btn-outline btn-danger btn-xs">
                                                        ban
                                                    </button>
                                                <#elseif user.status == "BANNED">
                                                    <button type="button" class="pardon btn btn-outline btn-primary btn-xs">
                                                        pardon
                                                    </button>
                                                </#if>
                                            </td>
                                        </@sec.authorize>
                                        <@sec.authorize access="hasRole('ROLE_USER')">
                                            <td>
                                                <a href="/users/${user.id}/profile" class="btn btn-outline btn-primary btn-xs">
                                                    info
                                                </a>
                                            </td>
                                            <td>
                                                <a href="/users/${user.id}/dialog" class="btn btn-outline btn-success btn-xs">
                                                    dialog
                                                </a>
                                            </td>
                                        </@sec.authorize>
                                    </tr>
                                </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>

<@main title="Users"
       styles=["/resources/landing/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"]
       scripts=["//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js",
                "/resources/landing/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js",
                "/resources/custom/users.js"]
/>