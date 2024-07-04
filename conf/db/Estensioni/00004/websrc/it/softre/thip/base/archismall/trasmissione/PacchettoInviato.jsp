<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///W:\PthDev\Projects\Panthera\Archismall\WebContent\dtd/xhtml1-transitional.dtd">
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
  BODataCollector PacchettoInviatoBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm PacchettoInviatoForm =  
     new com.thera.thermfw.web.WebForm(request, response, "PacchettoInviatoForm", "PacchettoInviato", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 1, true, "it/softre/thip/base/archismall/trasmissione/PacchettoInviato.js"); 
  PacchettoInviatoForm.setServletEnvironment(se); 
  PacchettoInviatoForm.setJSTypeList(jsList); 
  PacchettoInviatoForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  PacchettoInviatoForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  PacchettoInviatoForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = PacchettoInviatoForm.getMode(); 
  String key = PacchettoInviatoForm.getKey(); 
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
        PacchettoInviatoForm.outTraceInfo(getClass().getName()); 
        String collectorName = PacchettoInviatoForm.findBODataCollectorName(); 
                PacchettoInviatoBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (PacchettoInviatoBODC instanceof WebDataCollector) 
            ((WebDataCollector)PacchettoInviatoBODC).setServletEnvironment(se); 
        PacchettoInviatoBODC.initialize("PacchettoInviato", true, 1); 
        PacchettoInviatoForm.setBODataCollector(PacchettoInviatoBODC); 
        int rcBODC = PacchettoInviatoForm.initSecurityServices(); 
        mode = PacchettoInviatoForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           PacchettoInviatoForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = PacchettoInviatoBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              PacchettoInviatoForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(PacchettoInviatoForm); 
   request.setAttribute("menuBar", menuBar); 
%> 
<jsp:include page="/com/thera/thermfw/common/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="menuBar"/> 
</jsp:include> 
<% 
  menuBar.write(out); 
  menuBar.writeChildren(out); 
%> 
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(PacchettoInviatoForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/com/thera/thermfw/common/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=PacchettoInviatoForm.getBodyOnBeforeUnload()%>" onload="<%=PacchettoInviatoForm.getBodyOnLoad()%>" onunload="<%=PacchettoInviatoForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   PacchettoInviatoForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = PacchettoInviatoForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", PacchettoInviatoBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=PacchettoInviatoForm.getServlet()%>" method="post" name="PacchettoInviatoForm" style="height:100%"><%
  PacchettoInviatoForm.writeFormStartElements(out); 
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
  WebTextInput PacchettoInviatoIdLancio =  
     new com.thera.thermfw.web.WebTextInput("PacchettoInviato", "IdLancio"); 
  PacchettoInviatoIdLancio.setParent(PacchettoInviatoForm); 
%>
<input class="<%=PacchettoInviatoIdLancio.getClassType()%>" id="<%=PacchettoInviatoIdLancio.getId()%>" maxlength="<%=PacchettoInviatoIdLancio.getMaxLength()%>" name="<%=PacchettoInviatoIdLancio.getName()%>" size="<%=PacchettoInviatoIdLancio.getSize()%>" type="hidden"><% 
  PacchettoInviatoIdLancio.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput PacchettoInviatoIdPacchetto =  
     new com.thera.thermfw.web.WebTextInput("PacchettoInviato", "IdPacchetto"); 
  PacchettoInviatoIdPacchetto.setParent(PacchettoInviatoForm); 
%>
<input class="<%=PacchettoInviatoIdPacchetto.getClassType()%>" id="<%=PacchettoInviatoIdPacchetto.getId()%>" maxlength="<%=PacchettoInviatoIdPacchetto.getMaxLength()%>" name="<%=PacchettoInviatoIdPacchetto.getName()%>" size="<%=PacchettoInviatoIdPacchetto.getSize()%>" type="hidden"><% 
  PacchettoInviatoIdPacchetto.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput PacchettoInviatoIdArchiPro =  
     new com.thera.thermfw.web.WebTextInput("PacchettoInviato", "IdArchiPro"); 
  PacchettoInviatoIdArchiPro.setParent(PacchettoInviatoForm); 
%>
<input class="<%=PacchettoInviatoIdArchiPro.getClassType()%>" id="<%=PacchettoInviatoIdArchiPro.getId()%>" maxlength="<%=PacchettoInviatoIdArchiPro.getMaxLength()%>" name="<%=PacchettoInviatoIdArchiPro.getName()%>" size="<%=PacchettoInviatoIdArchiPro.getSize()%>" type="hidden"><% 
  PacchettoInviatoIdArchiPro.write(out); 
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
  mytabbed.setParent(PacchettoInviatoForm); 
  mytabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
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
  errorList.setParent(PacchettoInviatoForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  PacchettoInviatoForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = PacchettoInviatoForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", PacchettoInviatoBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              PacchettoInviatoForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, PacchettoInviatoBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, PacchettoInviatoBODC.getErrorList().getErrors()); 
           if(PacchettoInviatoBODC.getConflict() != null) 
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
     if(PacchettoInviatoBODC != null && !PacchettoInviatoBODC.close(false)) 
        errors.addAll(0, PacchettoInviatoBODC.getErrorList().getErrors()); 
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
     String errorPage = PacchettoInviatoForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", PacchettoInviatoBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = PacchettoInviatoForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
