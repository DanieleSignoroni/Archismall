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
  BODataCollector SendedPackArchismallBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm SendedPackArchismallForm =  
     new com.thera.thermfw.web.WebForm(request, response, "SendedPackArchismallForm", "SendedPackArchismall", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/softre/thip/archismall/trasmissione/SendedPackArchismall.js"); 
  SendedPackArchismallForm.setServletEnvironment(se); 
  SendedPackArchismallForm.setJSTypeList(jsList); 
  SendedPackArchismallForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  SendedPackArchismallForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  SendedPackArchismallForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = SendedPackArchismallForm.getMode(); 
  String key = SendedPackArchismallForm.getKey(); 
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
        SendedPackArchismallForm.outTraceInfo(getClass().getName()); 
        String collectorName = SendedPackArchismallForm.findBODataCollectorName(); 
                SendedPackArchismallBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (SendedPackArchismallBODC instanceof WebDataCollector) 
            ((WebDataCollector)SendedPackArchismallBODC).setServletEnvironment(se); 
        SendedPackArchismallBODC.initialize("SendedPackArchismall", true, 0); 
        SendedPackArchismallForm.setBODataCollector(SendedPackArchismallBODC); 
        int rcBODC = SendedPackArchismallForm.initSecurityServices(); 
        mode = SendedPackArchismallForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           SendedPackArchismallForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = SendedPackArchismallBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              SendedPackArchismallForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(SendedPackArchismallForm); 
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
  myToolBarTB.setParent(SendedPackArchismallForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=SendedPackArchismallForm.getBodyOnBeforeUnload()%>" onload="<%=SendedPackArchismallForm.getBodyOnLoad()%>" onunload="<%=SendedPackArchismallForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   SendedPackArchismallForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = SendedPackArchismallForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", SendedPackArchismallBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=SendedPackArchismallForm.getServlet()%>" method="post" name="SendedPackArchismallForm" style="height:100%"><%
  SendedPackArchismallForm.writeFormStartElements(out); 
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
  WebTextInput SendedPackArchismallIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("SendedPackArchismall", "IdAzienda"); 
  SendedPackArchismallIdAzienda.setParent(SendedPackArchismallForm); 
%>
<input class="<%=SendedPackArchismallIdAzienda.getClassType()%>" id="<%=SendedPackArchismallIdAzienda.getId()%>" maxlength="<%=SendedPackArchismallIdAzienda.getMaxLength()%>" name="<%=SendedPackArchismallIdAzienda.getName()%>" size="<%=SendedPackArchismallIdAzienda.getSize()%>" type="hidden"><% 
  SendedPackArchismallIdAzienda.write(out); 
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
  mytabbed.setParent(SendedPackArchismallForm); 
 mytabbed.addTab("tab1", "it.softre.thip.archismall.trasmissione.resources.SendedPackArchismall", "tab1", "SendedPackArchismall", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "SendedPackArchismall", "IdAnnoFattura", null); 
   label.setParent(SendedPackArchismallForm); 
%><label class="<%=label.getClassType()%>" for="IdAnnoFattura"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput SendedPackArchismallIdAnnoFattura =  
     new com.thera.thermfw.web.WebTextInput("SendedPackArchismall", "IdAnnoFattura"); 
  SendedPackArchismallIdAnnoFattura.setParent(SendedPackArchismallForm); 
%>
<input class="<%=SendedPackArchismallIdAnnoFattura.getClassType()%>" id="<%=SendedPackArchismallIdAnnoFattura.getId()%>" maxlength="<%=SendedPackArchismallIdAnnoFattura.getMaxLength()%>" name="<%=SendedPackArchismallIdAnnoFattura.getName()%>" size="<%=SendedPackArchismallIdAnnoFattura.getSize()%>"><% 
  SendedPackArchismallIdAnnoFattura.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "SendedPackArchismall", "DataFattura", null); 
   label.setParent(SendedPackArchismallForm); 
%><label class="<%=label.getClassType()%>" for="DataFattura"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput SendedPackArchismallDataFattura =  
     new com.thera.thermfw.web.WebTextInput("SendedPackArchismall", "DataFattura"); 
  SendedPackArchismallDataFattura.setShowCalendarBtn(true); 
  SendedPackArchismallDataFattura.setParent(SendedPackArchismallForm); 
%>
<input class="<%=SendedPackArchismallDataFattura.getClassType()%>" id="<%=SendedPackArchismallDataFattura.getId()%>" maxlength="<%=SendedPackArchismallDataFattura.getMaxLength()%>" name="<%=SendedPackArchismallDataFattura.getName()%>" size="<%=SendedPackArchismallDataFattura.getSize()%>"><% 
  SendedPackArchismallDataFattura.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "SendedPackArchismall", "IdNumeroFattura", null); 
   label.setParent(SendedPackArchismallForm); 
%><label class="<%=label.getClassType()%>" for="IdNumeroFattura"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput SendedPackArchismallIdNumeroFattura =  
     new com.thera.thermfw.web.WebTextInput("SendedPackArchismall", "IdNumeroFattura"); 
  SendedPackArchismallIdNumeroFattura.setParent(SendedPackArchismallForm); 
%>
<input class="<%=SendedPackArchismallIdNumeroFattura.getClassType()%>" id="<%=SendedPackArchismallIdNumeroFattura.getId()%>" maxlength="<%=SendedPackArchismallIdNumeroFattura.getMaxLength()%>" name="<%=SendedPackArchismallIdNumeroFattura.getName()%>" size="<%=SendedPackArchismallIdNumeroFattura.getSize()%>"><% 
  SendedPackArchismallIdNumeroFattura.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "SendedPackArchismall", "StatoPacchetto", null); 
   label.setParent(SendedPackArchismallForm); 
%><label class="<%=label.getClassType()%>" for="StatoPacchetto"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebComboBox SendedPackArchismallStatoPacchetto =  
     new com.thera.thermfw.web.WebComboBox("SendedPackArchismall", "StatoPacchetto", null); 
  SendedPackArchismallStatoPacchetto.setParent(SendedPackArchismallForm); 
%>
<select id="<%=SendedPackArchismallStatoPacchetto.getId()%>" name="<%=SendedPackArchismallStatoPacchetto.getName()%>"><% 
  SendedPackArchismallStatoPacchetto.write(out); 
%> 
</select>
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "SendedPackArchismall", "StatoArchismall", null); 
   label.setParent(SendedPackArchismallForm); 
%><label class="<%=label.getClassType()%>" for="StatoArchismall"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebComboBox SendedPackArchismallStatoArchismall =  
     new com.thera.thermfw.web.WebComboBox("SendedPackArchismall", "StatoArchismall", null); 
  SendedPackArchismallStatoArchismall.setParent(SendedPackArchismallForm); 
%>
<select id="<%=SendedPackArchismallStatoArchismall.getId()%>" name="<%=SendedPackArchismallStatoArchismall.getName()%>"><% 
  SendedPackArchismallStatoArchismall.write(out); 
%> 
</select>
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "SendedPackArchismall", "IdLancio", null); 
   label.setParent(SendedPackArchismallForm); 
%><label class="<%=label.getClassType()%>" for="IdLancio"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput SendedPackArchismallIdLancio =  
     new com.thera.thermfw.web.WebTextArea("SendedPackArchismall", "IdLancio"); 
  SendedPackArchismallIdLancio.setParent(SendedPackArchismallForm); 
%>
<textarea class="<%=SendedPackArchismallIdLancio.getClassType()%>" cols="60" id="<%=SendedPackArchismallIdLancio.getId()%>" maxlength="<%=SendedPackArchismallIdLancio.getMaxLength()%>" name="<%=SendedPackArchismallIdLancio.getName()%>" rows="5" size="<%=SendedPackArchismallIdLancio.getSize()%>"></textarea><% 
  SendedPackArchismallIdLancio.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "SendedPackArchismall", "IdArchiPro", null); 
   label.setParent(SendedPackArchismallForm); 
%><label class="<%=label.getClassType()%>" for="IdArchiPro"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput SendedPackArchismallIdArchiPro =  
     new com.thera.thermfw.web.WebTextArea("SendedPackArchismall", "IdArchiPro"); 
  SendedPackArchismallIdArchiPro.setParent(SendedPackArchismallForm); 
%>
<textarea class="<%=SendedPackArchismallIdArchiPro.getClassType()%>" cols="60" id="<%=SendedPackArchismallIdArchiPro.getId()%>" maxlength="<%=SendedPackArchismallIdArchiPro.getMaxLength()%>" name="<%=SendedPackArchismallIdArchiPro.getName()%>" rows="5" size="<%=SendedPackArchismallIdArchiPro.getSize()%>"></textarea><% 
  SendedPackArchismallIdArchiPro.write(out); 
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
  errorList.setParent(SendedPackArchismallForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  SendedPackArchismallForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = SendedPackArchismallForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", SendedPackArchismallBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              SendedPackArchismallForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, SendedPackArchismallBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, SendedPackArchismallBODC.getErrorList().getErrors()); 
           if(SendedPackArchismallBODC.getConflict() != null) 
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
     if(SendedPackArchismallBODC != null && !SendedPackArchismallBODC.close(false)) 
        errors.addAll(0, SendedPackArchismallBODC.getErrorList().getErrors()); 
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
     String errorPage = SendedPackArchismallForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", SendedPackArchismallBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = SendedPackArchismallForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
