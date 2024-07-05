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
String webAppPath = IniFile.getValue("thermfw.ini", "Web", "WebApplicationPath");
%>
    <title>Panthera- Stato Pacchetti</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="/<%=webAppPath%>/it/softre/thip/archismall/assets/DataTables/datatables.css?v=1.00" rel="stylesheet">
	<script src="/<%=webAppPath%>/it/softre/thip/archismall/assets/DataTables/datatables.js"></script>
	<link href="/<%=webAppPath%>/it/softre/thip/archismall/trasmissione/css/preloader.css" rel="stylesheet">
      <script type="text/javascript">
      var webAppPath = '<%=webAppPath%>';
      
        $(document).ready(function() {
            fetchRows();
        });

        function fetchRows() {
            var requests = [];
            $(".row-archismall").each(function(index, element) {
                var rowId = $(element).attr("data-row-id");
                var request = $.ajax({
                    url: getURLWS() + '/archismall/stato-conservazione',
                    type: 'GET',
                    data: { "IdPacchetto": rowId },
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader('Authorization', getBearerTokenFromLocalStorage());
                    },
                    success: function(response) {
                        var row = $(element);
                        row.find('.lancio').html(response.lancio);
                        row.find('.archiPro').html(response.archiPro);
                        row.find('.numeroFattura').html(response.numeroFattura);
                        row.find('.dataFattura').html(response.dataFattura);
                        row.find('.ragioneSociale').html(response.ragioneSociale);
                        row.find('.tipoDocumento').html(response.tipoDocumento);
                        row.find('.statoArchismall').html(response.statoArchismall);
                        row.find('.descrizioneStatoArchismall').html(response.descrizioneStatoArchismall);
                        row.find('.descrizioneErrore').html(response.descrizioneErrore);
                        row.find('.statoPanthera').html(response.statoPanthera);

                        var statoArchismall = response.statoArchismall;
                        if (statoArchismall == '2') {
                            row.find('.statoArchismall').addClass('importato');
                        } else {
                            row.find('.statoArchismall').addClass('non-importato');
                        }
                    },
                    error: function() {
                        $(element).find('.row-content').html("Error loading state");
                    }
                });

                requests.push(request);
            });

            $.when.apply($, requests).then(function() {
                initializeDataTable();
            });
        }

        function initializeDataTable() {
            $('#dataTable').DataTable({
                "initComplete": function(settings, json) {
                    updateFooter(this.api());
                }
            });
            rimuoviSpinner();
        }

        function updateFooter(api) {
            var totalRows = api.rows().count();
            var equalTo2 = 0;
            var notEqualTo2 = 0;

            api.rows().every(function() {
                var data = this.data();
                if (data[6] === '2') {
                    equalTo2++;
                } else {
                    notEqualTo2++;
                }
            });

            $(api.column(1).footer()).html('Pacchetti totali  ' + totalRows);
            $(api.column(3).footer()).html('Conservati ' + equalTo2);
            $(api.column(5).footer()).html('Non Conservati ' + notEqualTo2);
        }
    </script>
</head>
<body onload="visualizzaStatoPacchettoInternalOnLoad()">

	
	<div id="preloader" class="preloader" style="display: none">
		<div class="spinner"></div>
	</div>
	
	
        <div class="table-container">
        <table id="dataTable" class="table table-striped table-bordered">
            <thead class="thead-dark sticky-header">
                <tr>
                    <th>Lancio</th>
                    <th>Archi-Pro</th>
                    <th>Numero fattura</th>
                    <th>Data fattura</th>
                    <th>Ragione Soc.</th>
                    <th>Tipo Doc.</th>
                    <th>Stato Archismall</th>
                    <th>Descrizione Stato</th>
                    <th>Descrizione Errore</th>
                    <th>Stato Panthera</th>
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
                            <td class="archiPro"></td>
                            <td class="numeroFattura"></td>
                            <td class="dataFattura"></td>
                            <td class="ragioneSociale"></td>
                            <td class="tipoDocumento"></td>
                            <td class="statoArchismall"></td>
                            <td class="descrizioneStatoArchismall"></td>
                            <td class="descrizioneErrore"></td>
                            <td class="statoPanthera"></td>
                        </tr>
            <%
                    }
                }
            %>
            </tbody>
             <tfoot>
                <tr>
                    <th colspan="2" id="totalRows"></th>
                    <th colspan="2" id="equalTo2"></th>
                    <th colspan="2" id="notEqualTo2"></th>
                    <th colspan="4"></th>
                </tr>
            </tfoot>
        </table>
        </div>
</body>
</html>
