<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>

<#import "../macro/requests_table.ftl" as mac/>
<#include "../main_template.ftl"/>

<#macro content>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Requests</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-info">
                <div class="panel-heading">
                    Requests
                    <@sec.authorize access="hasRole('ROLE_ADMIN')">
                        <a href="/requests/pdf" target="_blank" style="float: right">generate pdf</a>
                    </@sec.authorize>
                    <@sec.authorize access="hasRole('ROLE_USER')">
                        <a href="/requests/new" style="float: right">New!</a>
                    </@sec.authorize>
                </div>
                <div class="panel-body">
                    <@sec.authorize access="hasRole('ROLE_ADMIN')">
                        <@mac.req_table requests=requests/>
                    </@sec.authorize>
                    <@sec.authorize access="hasRole('ROLE_USER')">
                        <ul class="nav nav-tabs">
                            <li>
                                <a id="all" href="#">All</a>
                            </li>
                            <li>
                                <a id="my" href="#">My</a>
                            </li>
                            <li>
                                <a id="pending" href="#">Pending</a>
                            </li>
                        </ul><br>

                        <div class="tab-content">
                            Choose filtering
                        </div>
                    </@sec.authorize>
                </div>
            </div>
        </div>
    </div>
</#macro>

<@main title="Requests"
       styles=["/resources/landing/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"]
       scripts=["//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js",
                "/resources/landing/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js",
                "/resources/custom/requests.js"]
/>