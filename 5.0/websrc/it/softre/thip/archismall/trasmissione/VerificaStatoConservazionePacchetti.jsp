<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///K:/Thip/5.1.0/websrcsvil/dtd/xhtml1-transitional.dtd">
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
  BODataCollector YVerificaStatoConsPacArchBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YVerificaStatoConsPacArchForm =  
     new com.thera.thermfw.web.WebFormForBatchForm(request, response, "YVerificaStatoConsPacArchForm", "YVerificaStatoConsPacArch", "Arial,10", "com.thera.thermfw.batch.web.BatchFormActionAdapter", false, false, false, true, true, true, null, 0, true, null); 
  YVerificaStatoConsPacArchForm.setServletEnvironment(se); 
  YVerificaStatoConsPacArchForm.setJSTypeList(jsList); 
  YVerificaStatoConsPacArchForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YVerificaStatoConsPacArchForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  ((WebFormForBatchForm)  YVerificaStatoConsPacArchForm).setGenerateThReportId(true); 
  ((WebFormForBatchForm)  YVerificaStatoConsPacArchForm).setGenerateSSDEnabled(true); 
  YVerificaStatoConsPacArchForm.setWebFormModifierClass("it.softre.thip.archismall.trasmissione.web.VerificaStatoConservazionePacchettiFormModifier"); 
  YVerificaStatoConsPacArchForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YVerificaStatoConsPacArchForm.getMode(); 
  String key = YVerificaStatoConsPacArchForm.getKey(); 
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
        YVerificaStatoConsPacArchForm.outTraceInfo(getClass().getName()); 
        String collectorName = YVerificaStatoConsPacArchForm.findBODataCollectorName(); 
				 YVerificaStatoConsPacArchBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YVerificaStatoConsPacArchBODC instanceof WebDataCollector) 
            ((WebDataCollector)YVerificaStatoConsPacArchBODC).setServletEnvironment(se); 
        YVerificaStatoConsPacArchBODC.initialize("YVerificaStatoConsPacArch", true, 0); 
        int rcBODC; 
        if (YVerificaStatoConsPacArchBODC.getBo() instanceof BatchRunnable) 
          rcBODC = YVerificaStatoConsPacArchBODC.initSecurityServices("RUN", mode, true, false, true); 
        else 
          rcBODC = YVerificaStatoConsPacArchBODC.initSecurityServices(mode, true, true, true); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YVerificaStatoConsPacArchForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YVerificaStatoConsPacArchBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YVerificaStatoConsPacArchForm.setBODataCollector(YVerificaStatoConsPacArchBODC); 
              YVerificaStatoConsPacArchForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

	<title>Invio - Archismall</title>
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(YVerificaStatoConsPacArchForm); 
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
<body bottommargin="0" leftmargin="0" onbeforeunload="<%=YVerificaStatoConsPacArchForm.getBodyOnBeforeUnload()%>" onload="<%=YVerificaStatoConsPacArchForm.getBodyOnLoad()%>" onunload="<%=YVerificaStatoConsPacArchForm.getBodyOnUnload()%>" rightmargin="0" topmargin="0"><%
   YVerificaStatoConsPacArchForm.writeBodyStartElements(out); 
%> 

	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YVerificaStatoConsPacArchForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YVerificaStatoConsPacArchBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YVerificaStatoConsPacArchForm.getServlet()%>" method="post" name="myForm" style="height:100%"><%
  YVerificaStatoConsPacArchForm.writeFormStartElements(out); 
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
  WebTextInput YVerificaStatoConsPacArchChiaviSelezionati =  
     new com.thera.thermfw.web.WebTextInput("YVerificaStatoConsPacArch", "ChiaviSelezionati"); 
  YVerificaStatoConsPacArchChiaviSelezionati.setParent(YVerificaStatoConsPacArchForm); 
%>
<input class="<%=YVerificaStatoConsPacArchChiaviSelezionati.getClassType()%>" id="<%=YVerificaStatoConsPacArchChiaviSelezionati.getId()%>" maxlength="<%=YVerificaStatoConsPacArchChiaviSelezionati.getMaxLength()%>" name="<%=YVerificaStatoConsPacArchChiaviSelezionati.getName()%>" readonly size="<%=YVerificaStatoConsPacArchChiaviSelezionati.getSize()%>"><% 
  YVerificaStatoConsPacArchChiaviSelezionati.write(out); 
%>
</td>
							</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height:0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YVerificaStatoConsPacArchForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  YVerificaStatoConsPacArchForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YVerificaStatoConsPacArchForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YVerificaStatoConsPacArchBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YVerificaStatoConsPacArchForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YVerificaStatoConsPacArchBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YVerificaStatoConsPacArchBODC.getErrorList().getErrors()); 
           if(YVerificaStatoConsPacArchBODC.getConflict() != null) 
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
     if(YVerificaStatoConsPacArchBODC != null && !YVerificaStatoConsPacArchBODC.close(false)) 
        errors.addAll(0, YVerificaStatoConsPacArchBODC.getErrorList().getErrors()); 
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
     String errorPage = YVerificaStatoConsPacArchForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YVerificaStatoConsPacArchBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YVerificaStatoConsPacArchForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
