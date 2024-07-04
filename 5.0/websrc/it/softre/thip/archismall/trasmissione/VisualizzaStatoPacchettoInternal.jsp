<%@page import="it.softre.thip.archismall.trasmissione.PacchettoTrasmissione"%>
<%@page import="it.softre.thip.archismall.trasmissione.PacchettoInviato"%>
<%@page import="java.util.List"%>
<%@page import="com.thera.thermfw.base.IniFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="/<%=IniFile.getValue("thermfw.ini", "Web", "WebApplicationPath")%>/thermweb/image/nav/favicon.ico">
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("it/softre/thip/archismall/trasmissione/js/VisualizzaStatoPacchettoInternal.js", request)%>
<%=com.thera.thermfw.web.WebJSTypeList.getImportForCSS("it/softre/thip/archismall/trasmissione/css/VisualizzaStatoPacchettoInternal.css", request)%>
<%
PacchettoTrasmissione pacchetto = (PacchettoTrasmissione) request.getAttribute("Pacchetto");
%>
    <title>Panthera- Stato Pacchetti</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        var webAppPath = '<%=IniFile.getValue("thermfw.ini", "Web", "WebApplicationPath")%>';
        $(document).ready(function() {
            $(".row-archismall").each(function(index, element) {
                var rowId = $(element).attr("data-row-id");
                $.ajax({
                    url: getURLWS() + '/archismall/stato-conservazione',
                    type: 'GET',
                    data: { "IdPacchetto": rowId },
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader('Authorization', getBearerTokenFromLocalStorage());
                    },
                    success: function(response) {
                        $(element).find('.lancio').html(response.lancio);
                        $(element).find('.pacchetto').html(response.pacchetto);
                        $(element).find('.archiPro').html(response.archiPro);
                        
                        $(element).find('.numeroFattura').html(response.numeroFattura);
                        $(element).find('.dataFattura').html(response.dataFattura);
                        $(element).find('.ragioneSociale').html(response.ragioneSociale);
                        $(element).find('.tipoDocumento').html(response.tipoDocumento);
                        
                        $(element).find('.statoPanthera').html(response.statoPanthera);
                        var statoPanthera = response.statoPanthera;
                        if(statoPanthera == '1'){
                        	$(element).find('.statoPanthera').addClass('importato');
                        }else{
                        	$(element).find('.statoPanthera').addClass('non-importato');
                        }
                        $(element).find('.statoArchismall').html(response.statoArchismall);
                        $(element).find('.descrizioneErrore').html(response.descrizioneErrore);
                    },
                    error: function() {
                        $(element).find('.row-content').html("Error loading state");
                    }
                });
            });
        });
    </script>
</head>
<body onload="visualizzaStatoPacchettoInternalOnLoad()">
        <h2 class="m-4 text-center">Stato pacchetti interni <%=pacchetto.getIdLancio() %></h2>
        <div class="table-container">
        <table class="table table-striped table-bordered">
            <thead class="thead-dark sticky-header">
                <tr>
                    <th>Lancio</th>
                    <th>Pacchetto</th>
                    <th>Archi-Pro</th>
                    <th>Numero fattura</th>
                    <th>Data fattura</th>
                    <th>Ragione Soc.</th>
                    <th>Tipo Doc.</th>
                    <th>Stato Panthera</th>
                    <th>Stato Archismall</th>
                    <th>Descrizione Errore</th>
                </tr>
            </thead>
            <tbody>
            <% 
                List<PacchettoInviato> rows = (List<PacchettoInviato>) request.getAttribute("rows");
                if (rows != null) {
                    for (PacchettoInviato row : rows) {
            %>
                        <tr class="row-archismall" data-row-id="<%= row.getIdPacchetto() %>">
                            <td class="lancio"><%= row.getIdPacchetto() %> - Loading...</td>
                            <td class="pacchetto"></td>
                            <td class="archiPro"></td>
                            <td class="numeroFattura"></td>
                            <td class="dataFattura"></td>
                            <td class="ragioneSociale"></td>
                            <td class="tipoDocumento"></td>
                            <td class="statoPanthera"></td>
                            <td class="statoArchismall"></td>
                            <td class="descrizioneErrore"></td>
                        </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
        </div>
</body>
</html>
