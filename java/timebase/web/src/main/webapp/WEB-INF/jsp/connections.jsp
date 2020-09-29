<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<t:layoutpage headerFile="/WEB-INF/jsp/common/header.jsp" menuFile="/WEB-INF/jsp/common/menu.jsp" footerFile="/WEB-INF/jsp/common/footer.jsp">

    <%@include file="/WEB-INF/jsp/common/common.jsp"%>

    <h3>
        Communication Framework (<fmt:formatDate pattern="HH:mm:ss.SSS" value="${model.currentDate}"/>)
    </h3>

    <table id="connectionsTable" class="table table-hover table-striped table-bordered">
        <thead>
            <tr>
                <th>Index</th>
                <th>Details</th>
                <th>#</th>
                <th>Client Id</th>
                <th>Application</th>
                <th>Connection Time</th>
                <th>#Transports</th>
                <th>Throughput (MB/sec)</th>
                <th>Avg Throughput (MB/sec)</th>
                <th>Remote Address</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="dispatcher" items="${model.serverFramework.dispatchers}" varStatus="loop">
                <c:url var="connectionUrl" value="/connection">
                    <c:param name="clientId" value="${dispatcher.clientId}" />
                </c:url>
                <tr>
                    <td>${dispatcher.clientId}</td>
                    <td></td>
                    <td>${loop.index + 1}</td>
                    <td><a href="${connectionUrl}">${dispatcher.clientId}</a></td>
                    <td>${dispatcher.applicationID}</td>
                    <td>${dispatcher.creationDate}</td>
                    <td>${dispatcher.numTransportChannels}</td>
                    <td><fmt:formatNumber pattern="###,###,###.#" value="${(dispatcher.throughput / 1024 / 1024)}"/></td>
                    <td><fmt:formatNumber pattern="###,###,###.#" value="${(dispatcher.averageThroughput / 1024 / 1024)}"/></td>
                    <td>${dispatcher.remoteAddress}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="pull-right" >
        <img src="${returnImgUrl}">
        <a class="action" href="/.."> QuantServer Home</a>
    </div>

    <script type="application/javascript">
        function format ( d ) {
            return '<div class="col-md-8">'+
                '<table class="table">'+
                '<tr>'+
                    '<th>Client Id:</th>'+
                    '<td>' + d[3] + '</td>'+
                '</tr>'+
                '<tr>'+
                    '<th>Number of Transports:</th>'+
                    '<td>' + d[6] + '</td>'+
                '</tr>'+
                '<tr>'+
                    '<th>Throughput (MB/sec):</th>'+
                    '<td>' + d[7] + '</td>'+
                '</tr>'+
                '<tr>'+
                    '<th>Avg Throughput (MB/sec):</th>'+
                    '<td>' + d[8] + '</td>'+
                '</tr>'+
                '</table>'+
                '</div>';
        }

        $(document).ready(function() {
            var table = $('#connectionsTable').DataTable( {
                "paginate": false,
                'columnDefs' : [
                    { 'className': 'details-control', 'targets': 1},
                    { "orderable": false, 'targets': [1]},
                    { 'visible': false, 'targets': [0, 6, 7, 8] }
                ],
                "order": [[2, 'asc']],
                "stateSave": true
            });

            var itemName = "connectionDetails";
            var openedDetailsSet = getExpandedDetailsSet(itemName);
            expandDetails(table, openedDetailsSet, 0);
            saveExpandedDetailsSet(itemName, openedDetailsSet);
            $('#connectionsTable tbody').on('click', 'td.details-control', function () {
                var tr = $(this).closest('tr');
                var row = table.row( tr );
                var index = row.data()[0];

                if ( row.child.isShown() ) {
                    row.child.hide();
                    tr.removeClass('shown');

                    openedDetailsSet.delete(index);
                    saveExpandedDetailsSet(itemName, openedDetailsSet);
                } else {
                    row.child( format(row.data()) ).show();
                    tr.addClass('shown');

                    openedDetailsSet.add(index);
                    saveExpandedDetailsSet(itemName, openedDetailsSet);
                }
            } );
        } );
    </script>


</t:layoutpage>