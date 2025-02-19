<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///K:/Thip/5.0.0/websrcsvil/dtd/xhtml1-transitional.dtd">
<html>
<!-- WIZGEN Therm 2.0.0 as Batch form - multiBrowserGen = true -->
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
  BODataCollector PachettoTraInvioBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm PachettoTraInvioForm =  
     new com.thera.thermfw.web.WebFormForBatchForm(request, response, "PachettoTraInvioForm", "PachettoTraInvio", "Arial,10", "com.thera.thermfw.batch.web.BatchFormActionAdapter", false, false, false, true, true, true, null, 0, true, "it/softre/thip/archismall/trasmissione/PacchettoTrasmissioneInvio.js"); 
  PachettoTraInvioForm.setServletEnvironment(se); 
  PachettoTraInvioForm.setJSTypeList(jsList); 
  PachettoTraInvioForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  PachettoTraInvioForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  ((WebFormForBatchForm)  PachettoTraInvioForm).setGenerateThReportId(true); 
  ((WebFormForBatchForm)  PachettoTraInvioForm).setGenerateSSDEnabled(true); 
  PachettoTraInvioForm.setWebFormModifierClass("it.softre.thip.archismall.trasmissione.web.PacchettoTrasmissioneInvioFormModifier"); 
  PachettoTraInvioForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = PachettoTraInvioForm.getMode(); 
  String key = PachettoTraInvioForm.getKey(); 
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
        PachettoTraInvioForm.outTraceInfo(getClass().getName()); 
        String collectorName = PachettoTraInvioForm.findBODataCollectorName(); 
				 PachettoTraInvioBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (PachettoTraInvioBODC instanceof WebDataCollector) 
            ((WebDataCollector)PachettoTraInvioBODC).setServletEnvironment(se); 
        PachettoTraInvioBODC.initialize("PachettoTraInvio", true, 0); 
        int rcBODC; 
        if (PachettoTraInvioBODC.getBo() instanceof BatchRunnable) 
          rcBODC = PachettoTraInvioBODC.initSecurityServices("RUN", mode, true, false, true); 
        else 
          rcBODC = PachettoTraInvioBODC.initSecurityServices(mode, true, true, true); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           PachettoTraInvioForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = PachettoTraInvioBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              PachettoTraInvioForm.setBODataCollector(PachettoTraInvioBODC); 
              PachettoTraInvioForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

	<title>Invio - Archismall</title>
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(PachettoTraInvioForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/com/thera/thermfw/batch/BatchRunnableMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
<% 
  WebLink link_0 =  
   new com.thera.thermfw.web.WebLink(); 
 link_0.setHttpServletRequest(request); 
 link_0.setHRefAttribute("thermweb/css/thermGrid.css"); 
 link_0.setRelAttribute("STYLESHEET"); 
 link_0.setTypeAttribute("text/css"); 
  link_0.write(out); 
%>
<!--<link href="thermweb/css/thermGrid.css" rel="STYLESHEET" type="text/css">-->
<body bottommargin="0" leftmargin="0" onbeforeunload="<%=PachettoTraInvioForm.getBodyOnBeforeUnload()%>" onload="<%=PachettoTraInvioForm.getBodyOnLoad()%>" onunload="<%=PachettoTraInvioForm.getBodyOnUnload()%>" rightmargin="0" topmargin="0"><%
   PachettoTraInvioForm.writeBodyStartElements(out); 
%> 

	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = PachettoTraInvioForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", PachettoTraInvioBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=PachettoTraInvioForm.getServlet()%>" method="post" name="PachettoTraInvioForm" style="height:100%"><%
  PachettoTraInvioForm.writeFormStartElements(out); 
%>

		<table cellpadding="2" cellspacing="2" height="100%" width="100%">
			<tr><td style="height:0"><% myToolBarTB.writeChildren(out); %> 
</td></tr>
			<tr style="display:none;">
				<td>
					<table width="100%">
					        <tr><td><label id="descr"></label></td></tr>	
            				<tr>
							<td><% 
  WebTextInput PachettoTraInvioChiaviSelezionati =  
     new com.thera.thermfw.web.WebTextInput("PachettoTraInvio", "ChiaviSelezionati"); 
  PachettoTraInvioChiaviSelezionati.setParent(PachettoTraInvioForm); 
%>
<input class="<%=PachettoTraInvioChiaviSelezionati.getClassType()%>" id="<%=PachettoTraInvioChiaviSelezionati.getId()%>" maxlength="<%=PachettoTraInvioChiaviSelezionati.getMaxLength()%>" name="<%=PachettoTraInvioChiaviSelezionati.getName()%>" readonly size="<%=PachettoTraInvioChiaviSelezionati.getSize()%>"><% 
  PachettoTraInvioChiaviSelezionati.write(out); 
%>
</td>
							</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height:0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(PachettoTraInvioForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  PachettoTraInvioForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = PachettoTraInvioForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", PachettoTraInvioBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              PachettoTraInvioForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, PachettoTraInvioBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, PachettoTraInvioBODC.getErrorList().getErrors()); 
           if(PachettoTraInvioBODC.getConflict() != null) 
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
     if(PachettoTraInvioBODC != null && !PachettoTraInvioBODC.close(false)) 
        errors.addAll(0, PachettoTraInvioBODC.getErrorList().getErrors()); 
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
     String errorPage = PachettoTraInvioForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", PachettoTraInvioBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = PachettoTraInvioForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
