<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<#include "../main_template.ftl"/>

<#macro content>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">New community</h1>
    </div>
</div>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-info">
            <div class="panel-heading">
                New community
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-8">
                        <@sf.form role="form" action="/communities/create" method="post" modelAttribute="community">
                            <fieldset>
                                <div class="form-group">
                                    <@sf.label path="name">Name</@sf.label>
                                    <@sf.input path="name" cssClass="form-control" type="text"/>
                                    <@sf.errors path="name" cssClass="help-block"/>
                                </div>
                                <div class="form-group">
                                    <@sf.label path="description">Brief description</@sf.label>
                                    <@sf.textarea path="description" cssClass="form-control" rows="5"/>
                                    <@sf.errors path="description" cssClass="help-block"/>
                                </div>
                                <div class="form-group">
                                    <input class="btn btn-info btn-outline" type="submit" value="Create">
                                </div>
                            </fieldset>
                        </@sf.form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>

<@main title="Community"/>