<#macro req_table requests type="default">
    <div class="dataTable_wrapper">
        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
            <thead>
                <tr>
                    <th>Needy</th>
                    <th>Address</th>
                    <th>Created at</th>
                    <th>Type of service</th>
                    <th>Status</th>
                    <th>Volunteer</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <#list requests as request>
                    <tr>
                        <td>${request.needy.name} ${request.needy.surname}</td>
                        <td>${request.address}</td>
                        <td>${request.createdAt}</td>
                        <td>${request.serviceType.representation}</td>
                        <td>${request.status.representation}</td>
                        <td>
                            <#if request.volunteer??>
                            ${request.volunteer.name} ${request.volunteer.surname}
                            <#else>
                                No yet
                            </#if>
                        </td>
                        <td>
                            <#if type=="pending">
                                <button type="button" name="${request.id}" class="help btn btn-outline btn-success btn-xs">
                                    help!
                                </button>
                            <#else>
                                <a href="/requests/${request.id}" class="btn btn-outline btn-primary btn-xs">
                                    info
                                </a>
                            </#if>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
</#macro>