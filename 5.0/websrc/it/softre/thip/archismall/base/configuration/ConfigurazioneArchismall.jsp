<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///K:/Thip/5.0.0/websrcsvil/dtd/xhtml1-transitional.dtd">
<html>
<!-- WIZGEN Therm 2.0.0 as Form - multiBrowserGen = true -->
<%=WebGenerator.writeRuntimeInfo()%>
  <head>
<%@ page contentType="text/html; charset=Cp1252"%>
<%@ page import= " 
  java.sql.*, 
  java.util.*, 
  java.lang.reflect.*, 
  javax.naming.*, 
  com.thera.thermfw.common.*, 
  com.thera.thermfw.type.*, 
  com.thera.thermfw.web.*, 
  com.thera.thermfw.security.*, 
  com.thera.thermfw.base.*, 
  com.thera.thermfw.ad.*, 
  com.thera.thermfw.persist.*, 
  com.thera.thermfw.gui.cnr.*, 
  com.thera.thermfw.setting.*, 
  com.thera.thermfw.collector.*, 
  com.thera.thermfw.batch.web.*, 
  com.thera.thermfw.batch.*, 
  com.thera.thermfw.pref.* 
"%> 
<%
  ServletEnvironment se = (ServletEnvironment)Factory.createObject("com.thera.thermfw.web.ServletEnvironment"); 
  BODataCollector ConfigurazioneArchismallBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm ConfigurazioneArchismallForm =  
     new com.thera.thermfw.web.WebForm(request, response, "ConfigurazioneArchismallForm", "ConfigurazioneArchismall", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/softre/thip/archismall/base/configuration/ConfigurazioneArchismall.js"); 
  ConfigurazioneArchismallForm.setServletEnvironment(se); 
  ConfigurazioneArchismallForm.setJSTypeList(jsList); 
  ConfigurazioneArchismallForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  ConfigurazioneArchismallForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  ConfigurazioneArchismallForm.setWebFormModifierClass("it.softre.thip.archismall.base.configuration.web.ConfigurazioneArchismallFormModifier"); 
  ConfigurazioneArchismallForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = ConfigurazioneArchismallForm.getMode(); 
  String key = ConfigurazioneArchismallForm.getKey(); 
  String errorMessage; 
  boolean requestIsValid = false; 
  boolean leftIsKey = false; 
  boolean conflitPresent = false; 
  String leftClass = ""; 
  try 
  {
     se.initialize(request, response); 
     if(se.begin()) 
     { 
        ConfigurazioneArchismallForm.outTraceInfo(getClass().getName()); 
        String collectorName = ConfigurazioneArchismallForm.findBODataCollectorName(); 
                ConfigurazioneArchismallBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (ConfigurazioneArchismallBODC instanceof WebDataCollector) 
            ((WebDataCollector)ConfigurazioneArchismallBODC).setServletEnvironment(se); 
        ConfigurazioneArchismallBODC.initialize("ConfigurazioneArchismall", true, 0); 
        ConfigurazioneArchismallForm.setBODataCollector(ConfigurazioneArchismallBODC); 
        int rcBODC = ConfigurazioneArchismallForm.initSecurityServices(); 
        mode = ConfigurazioneArchismallForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           ConfigurazioneArchismallForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = ConfigurazioneArchismallBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              ConfigurazioneArchismallForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(ConfigurazioneArchismallForm); 
   request.setAttribute("menuBar", menuBar); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="menuBar"/> 
</jsp:include> 
<% 
  menuBar.write(out); 
  menuBar.writeChildren(out); 
%> 
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(ConfigurazioneArchismallForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=ConfigurazioneArchismallForm.getBodyOnBeforeUnload()%>" onload="<%=ConfigurazioneArchismallForm.getBodyOnLoad()%>" onunload="<%=ConfigurazioneArchismallForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   ConfigurazioneArchismallForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = ConfigurazioneArchismallForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", ConfigurazioneArchismallBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=ConfigurazioneArchismallForm.getServlet()%>" method="post" name="ConfigurazioneArchismallForm" style="height:100%"><%
  ConfigurazioneArchismallForm.writeFormStartElements(out); 
%>

      <table cellpadding="0" cellspacing="0" height="100%" id="emptyborder" width="100%">
        <tr>
          <td style="height:0">
            <% menuBar.writeElements(out); %> 

          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% myToolBarTB.writeChildren(out); %> 

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput ConfigurazioneArchismallIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("ConfigurazioneArchismall", "IdAzienda"); 
  ConfigurazioneArchismallIdAzienda.setParent(ConfigurazioneArchismallForm); 
%>
<input class="<%=ConfigurazioneArchismallIdAzienda.getClassType()%>" id="<%=ConfigurazioneArchismallIdAzienda.getId()%>" maxlength="<%=ConfigurazioneArchismallIdAzienda.getMaxLength()%>" name="<%=ConfigurazioneArchismallIdAzienda.getName()%>" size="<%=ConfigurazioneArchismallIdAzienda.getSize()%>" type="hidden"><% 
  ConfigurazioneArchismallIdAzienda.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td height="100%">
            <!--<span class="tabbed" id="mytabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed mytabbed = new com.thera.thermfw.web.WebTabbed("mytabbed", "100%", "100%"); 
  mytabbed.setParent(ConfigurazioneArchismallForm); 
 mytabbed.addTab("tab1", "it.softre.thip.archismall.base.configuration.resources.ConfigurazioneArchismall", "tab1", "ConfigurazioneArchismall", null, null, null, null); 
  mytabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
              <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab1")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab1"); %>
                <table style="width: 100%;">
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ConfigurazioneArchismall", "Url", null); 
   label.setParent(ConfigurazioneArchismallForm); 
%><label class="<%=label.getClassType()%>" for="Url"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput ConfigurazioneArchismallUrl =  
     new com.thera.thermfw.web.WebTextArea("ConfigurazioneArchismall", "Url"); 
  ConfigurazioneArchismallUrl.setParent(ConfigurazioneArchismallForm); 
%>
<textarea class="<%=ConfigurazioneArchismallUrl.getClassType()%>" cols="60" id="<%=ConfigurazioneArchismallUrl.getId()%>" maxlength="<%=ConfigurazioneArchismallUrl.getMaxLength()%>" name="<%=ConfigurazioneArchismallUrl.getName()%>" rows="5" size="<%=ConfigurazioneArchismallUrl.getSize()%>"></textarea><% 
  ConfigurazioneArchismallUrl.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ConfigurazioneArchismall", "ClientSecret", null); 
   label.setParent(ConfigurazioneArchismallForm); 
%><label class="<%=label.getClassType()%>" for="ClientSecret"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput ConfigurazioneArchismallClientSecret =  
     new com.thera.thermfw.web.WebTextArea("ConfigurazioneArchismall", "ClientSecret"); 
  ConfigurazioneArchismallClientSecret.setParent(ConfigurazioneArchismallForm); 
%>
<textarea class="<%=ConfigurazioneArchismallClientSecret.getClassType()%>" cols="60" id="<%=ConfigurazioneArchismallClientSecret.getId()%>" maxlength="<%=ConfigurazioneArchismallClientSecret.getMaxLength()%>" name="<%=ConfigurazioneArchismallClientSecret.getName()%>" rows="5" size="<%=ConfigurazioneArchismallClientSecret.getSize()%>"></textarea><% 
  ConfigurazioneArchismallClientSecret.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ConfigurazioneArchismall", "ClientId", null); 
   label.setParent(ConfigurazioneArchismallForm); 
%><label class="<%=label.getClassType()%>" for="ClientId"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput ConfigurazioneArchismallClientId =  
     new com.thera.thermfw.web.WebTextArea("ConfigurazioneArchismall", "ClientId"); 
  ConfigurazioneArchismallClientId.setParent(ConfigurazioneArchismallForm); 
%>
<textarea class="<%=ConfigurazioneArchismallClientId.getClassType()%>" cols="60" id="<%=ConfigurazioneArchismallClientId.getId()%>" maxlength="<%=ConfigurazioneArchismallClientId.getMaxLength()%>" name="<%=ConfigurazioneArchismallClientId.getName()%>" rows="5" size="<%=ConfigurazioneArchismallClientId.getSize()%>"></textarea><% 
  ConfigurazioneArchismallClientId.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ConfigurazioneArchismall", "IdUtente", null); 
   label.setParent(ConfigurazioneArchismallForm); 
%><label class="<%=label.getClassType()%>" for="IdUtente"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput ConfigurazioneArchismallIdUtente =  
     new com.thera.thermfw.web.WebTextInput("ConfigurazioneArchismall", "IdUtente"); 
  ConfigurazioneArchismallIdUtente.setParent(ConfigurazioneArchismallForm); 
%>
<input class="<%=ConfigurazioneArchismallIdUtente.getClassType()%>" id="<%=ConfigurazioneArchismallIdUtente.getId()%>" maxlength="<%=ConfigurazioneArchismallIdUtente.getMaxLength()%>" name="<%=ConfigurazioneArchismallIdUtente.getName()%>" size="<%=ConfigurazioneArchismallIdUtente.getSize()%>"><% 
  ConfigurazioneArchismallIdUtente.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ConfigurazioneArchismall", "Password", null); 
   label.setParent(ConfigurazioneArchismallForm); 
%><label class="<%=label.getClassType()%>" for="Password"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput ConfigurazioneArchismallPassword =  
     new com.thera.thermfw.web.WebTextInput("ConfigurazioneArchismall", "Password"); 
  ConfigurazioneArchismallPassword.setParent(ConfigurazioneArchismallForm); 
%>
<input class="<%=ConfigurazioneArchismallPassword.getClassType()%>" id="<%=ConfigurazioneArchismallPassword.getId()%>" maxlength="<%=ConfigurazioneArchismallPassword.getMaxLength()%>" name="<%=ConfigurazioneArchismallPassword.getName()%>" size="<%=ConfigurazioneArchismallPassword.getSize()%>"><% 
  ConfigurazioneArchismallPassword.write(out); 
%>

                    </td>
                  </tr>
                </table>
              <% mytabbed.endTab(); %> 
</div>
            </div><% mytabbed.endTabbed();%> 

     </td>
   </tr>
</table><!--</span>-->
          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(ConfigurazioneArchismallForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  ConfigurazioneArchismallForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = ConfigurazioneArchismallForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", ConfigurazioneArchismallBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              ConfigurazioneArchismallForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, ConfigurazioneArchismallBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, ConfigurazioneArchismallBODC.getErrorList().getErrors()); 
           if(ConfigurazioneArchismallBODC.getConflict() != null) 
                conflitPresent = true; 
     } 
     else 
        errors.add(new ErrorMessage("BAS0000010")); 
  } 
  catch(NamingException e) { 
     errorMessage = e.getMessage(); 
     errors.add(new ErrorMessage("CBS000025", errorMessage));  } 
  catch(SQLException e) {
     errorMessage = e.getMessage(); 
     errors.add(new ErrorMessage("BAS0000071", errorMessage));  } 
  catch(Throwable e) {
     e.printStackTrace(Trace.excStream);
  }
  finally 
  {
     if(ConfigurazioneArchismallBODC != null && !ConfigurazioneArchismallBODC.close(false)) 
        errors.addAll(0, ConfigurazioneArchismallBODC.getErrorList().getErrors()); 
     try 
     { 
        se.end(); 
     }
     catch(IllegalArgumentException e) { 
        e.printStackTrace(Trace.excStream); 
     } 
     catch(SQLException e) { 
        e.printStackTrace(Trace.excStream); 
     } 
  } 
  if(!errors.isEmpty())
  { 
      if(!conflitPresent)
  { 
     request.setAttribute("ErrorMessages", errors); 
     String errorPage = ConfigurazioneArchismallForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", ConfigurazioneArchismallBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = ConfigurazioneArchismallForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
