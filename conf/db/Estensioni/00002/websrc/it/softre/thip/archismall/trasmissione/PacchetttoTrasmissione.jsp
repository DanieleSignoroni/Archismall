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
  BODataCollector PacchetttoTrasmissioneBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm PacchetttoTrasmissioneForm =  
     new com.thera.thermfw.web.WebForm(request, response, "PacchetttoTrasmissioneForm", "PacchetttoTrasmissione", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 1, true, "it/softre/thip/archismall/trasmissione/PacchetttoTrasmissione.js"); 
  PacchetttoTrasmissioneForm.setServletEnvironment(se); 
  PacchetttoTrasmissioneForm.setJSTypeList(jsList); 
  PacchetttoTrasmissioneForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  PacchetttoTrasmissioneForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  PacchetttoTrasmissioneForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = PacchetttoTrasmissioneForm.getMode(); 
  String key = PacchetttoTrasmissioneForm.getKey(); 
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
        PacchetttoTrasmissioneForm.outTraceInfo(getClass().getName()); 
        String collectorName = PacchetttoTrasmissioneForm.findBODataCollectorName(); 
                PacchetttoTrasmissioneBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (PacchetttoTrasmissioneBODC instanceof WebDataCollector) 
            ((WebDataCollector)PacchetttoTrasmissioneBODC).setServletEnvironment(se); 
        PacchetttoTrasmissioneBODC.initialize("PacchetttoTrasmissione", true, 1); 
        PacchetttoTrasmissioneForm.setBODataCollector(PacchetttoTrasmissioneBODC); 
        int rcBODC = PacchetttoTrasmissioneForm.initSecurityServices(); 
        mode = PacchetttoTrasmissioneForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           PacchetttoTrasmissioneForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = PacchetttoTrasmissioneBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              PacchetttoTrasmissioneForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(PacchetttoTrasmissioneForm); 
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
  myToolBarTB.setParent(PacchetttoTrasmissioneForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/com/thera/thermfw/common/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=PacchetttoTrasmissioneForm.getBodyOnBeforeUnload()%>" onload="<%=PacchetttoTrasmissioneForm.getBodyOnLoad()%>" onunload="<%=PacchetttoTrasmissioneForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   PacchetttoTrasmissioneForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = PacchetttoTrasmissioneForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", PacchetttoTrasmissioneBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=PacchetttoTrasmissioneForm.getServlet()%>" method="post" name="PacchetttoTrasmissioneForm" style="height:100%"><%
  PacchetttoTrasmissioneForm.writeFormStartElements(out); 
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
  WebTextInput PacchetttoTrasmissioneT01cd =  
     new com.thera.thermfw.web.WebTextInput("PacchetttoTrasmissione", "T01cd"); 
  PacchetttoTrasmissioneT01cd.setParent(PacchetttoTrasmissioneForm); 
%>
<input class="<%=PacchetttoTrasmissioneT01cd.getClassType()%>" id="<%=PacchetttoTrasmissioneT01cd.getId()%>" maxlength="<%=PacchetttoTrasmissioneT01cd.getMaxLength()%>" name="<%=PacchetttoTrasmissioneT01cd.getName()%>" size="<%=PacchetttoTrasmissioneT01cd.getSize()%>" type="hidden"><% 
  PacchetttoTrasmissioneT01cd.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput PacchetttoTrasmissioneF9pidpac =  
     new com.thera.thermfw.web.WebTextInput("PacchetttoTrasmissione", "F9pidpac"); 
  PacchetttoTrasmissioneF9pidpac.setParent(PacchetttoTrasmissioneForm); 
%>
<input class="<%=PacchetttoTrasmissioneF9pidpac.getClassType()%>" id="<%=PacchetttoTrasmissioneF9pidpac.getId()%>" maxlength="<%=PacchetttoTrasmissioneF9pidpac.getMaxLength()%>" name="<%=PacchetttoTrasmissioneF9pidpac.getName()%>" size="<%=PacchetttoTrasmissioneF9pidpac.getSize()%>" type="hidden"><% 
  PacchetttoTrasmissioneF9pidpac.write(out); 
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
  mytabbed.setParent(PacchetttoTrasmissioneForm); 
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
  errorList.setParent(PacchetttoTrasmissioneForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  PacchetttoTrasmissioneForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = PacchetttoTrasmissioneForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", PacchetttoTrasmissioneBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              PacchetttoTrasmissioneForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, PacchetttoTrasmissioneBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, PacchetttoTrasmissioneBODC.getErrorList().getErrors()); 
           if(PacchetttoTrasmissioneBODC.getConflict() != null) 
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
     if(PacchetttoTrasmissioneBODC != null && !PacchetttoTrasmissioneBODC.close(false)) 
        errors.addAll(0, PacchetttoTrasmissioneBODC.getErrorList().getErrors()); 
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
     String errorPage = PacchetttoTrasmissioneForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", PacchetttoTrasmissioneBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = PacchetttoTrasmissioneForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
