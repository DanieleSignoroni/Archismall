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
  BODataCollector PacchettoTrasmissioneBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm PacchettoTrasmissioneForm =  
     new com.thera.thermfw.web.WebForm(request, response, "PacchettoTrasmissioneForm", "PacchettoTrasmissione", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 1, true, "it/softre/thip/archismall/trasmissione/PacchettoTrasmissione.js"); 
  PacchettoTrasmissioneForm.setServletEnvironment(se); 
  PacchettoTrasmissioneForm.setJSTypeList(jsList); 
  PacchettoTrasmissioneForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  PacchettoTrasmissioneForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  PacchettoTrasmissioneForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = PacchettoTrasmissioneForm.getMode(); 
  String key = PacchettoTrasmissioneForm.getKey(); 
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
        PacchettoTrasmissioneForm.outTraceInfo(getClass().getName()); 
        String collectorName = PacchettoTrasmissioneForm.findBODataCollectorName(); 
                PacchettoTrasmissioneBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (PacchettoTrasmissioneBODC instanceof WebDataCollector) 
            ((WebDataCollector)PacchettoTrasmissioneBODC).setServletEnvironment(se); 
        PacchettoTrasmissioneBODC.initialize("PacchettoTrasmissione", true, 1); 
        PacchettoTrasmissioneForm.setBODataCollector(PacchettoTrasmissioneBODC); 
        int rcBODC = PacchettoTrasmissioneForm.initSecurityServices(); 
        mode = PacchettoTrasmissioneForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           PacchettoTrasmissioneForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = PacchettoTrasmissioneBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              PacchettoTrasmissioneForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(PacchettoTrasmissioneForm); 
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
  myToolBarTB.setParent(PacchettoTrasmissioneForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/com/thera/thermfw/common/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=PacchettoTrasmissioneForm.getBodyOnBeforeUnload()%>" onload="<%=PacchettoTrasmissioneForm.getBodyOnLoad()%>" onunload="<%=PacchettoTrasmissioneForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   PacchettoTrasmissioneForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = PacchettoTrasmissioneForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", PacchettoTrasmissioneBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=PacchettoTrasmissioneForm.getServlet()%>" method="post" name="PacchettoTrasmissioneForm" style="height:100%"><%
  PacchettoTrasmissioneForm.writeFormStartElements(out); 
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
  WebTextInput PacchettoTrasmissioneId =  
     new com.thera.thermfw.web.WebTextInput("PacchettoTrasmissione", "Id"); 
  PacchettoTrasmissioneId.setParent(PacchettoTrasmissioneForm); 
%>
<input class="<%=PacchettoTrasmissioneId.getClassType()%>" id="<%=PacchettoTrasmissioneId.getId()%>" maxlength="<%=PacchettoTrasmissioneId.getMaxLength()%>" name="<%=PacchettoTrasmissioneId.getName()%>" size="<%=PacchettoTrasmissioneId.getSize()%>" type="hidden"><% 
  PacchettoTrasmissioneId.write(out); 
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
  mytabbed.setParent(PacchettoTrasmissioneForm); 
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
  errorList.setParent(PacchettoTrasmissioneForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  PacchettoTrasmissioneForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = PacchettoTrasmissioneForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", PacchettoTrasmissioneBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              PacchettoTrasmissioneForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, PacchettoTrasmissioneBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, PacchettoTrasmissioneBODC.getErrorList().getErrors()); 
           if(PacchettoTrasmissioneBODC.getConflict() != null) 
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
     if(PacchettoTrasmissioneBODC != null && !PacchettoTrasmissioneBODC.close(false)) 
        errors.addAll(0, PacchettoTrasmissioneBODC.getErrorList().getErrors()); 
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
     String errorPage = PacchettoTrasmissioneForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", PacchettoTrasmissioneBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = PacchettoTrasmissioneForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
