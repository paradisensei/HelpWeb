<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<#include "../main_template.ftl"/>

<#macro content>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Edit info</h1>
    </div>
</div>

<div class="row">
    <div class="col-lg-6">
        <@sf.form role="form" action="/users/edit" method="post" modelAttribute="user">
            <fieldset>
                <div style="display: none">
                    <@sf.input path="id" type="text"/>
                </div>
                <div class="form-group">
                    <@sf.input path="name" cssClass="form-control" placeholder="Name" type="text"/>
                    <@sf.errors path="name" cssClass="help-block"/>
                </div>
                <div class="form-group">
                    <@sf.input path="surname" cssClass="form-control" placeholder="Surname" type="text"/>
                                <@sf.errors path="surname" cssClass="help-block"/>
                </div>
                <div class="form-group">
                    <@sf.input path="email" cssClass="form-control" placeholder="Email" type="email"/>
                                <@sf.errors path="email" cssClass="help-block"/>
                </div>
                <div class="form-group">
                    <input class="btn btn-info btn-outline" type="submit" value="Update">
                </div>
            </fieldset>
        </@sf.form>
    </div>
</div>
</#macro>

<@main title="Community"/>