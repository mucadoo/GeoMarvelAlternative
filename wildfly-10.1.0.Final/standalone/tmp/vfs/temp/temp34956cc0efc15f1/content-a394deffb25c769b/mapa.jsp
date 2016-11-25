<%@page import="ejb.entities.Posicao"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Posicao> posicoes = (List<Posicao>) request.getAttribute("posicoes");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTf-8">
        <title>Mapa</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/ol.css" type="text/css">
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/ol.js"></script>
        <script type="text/javascript">
            $(function () {
                meuMapa = new ol.Map({
                    target: 'MeuMapa',
                    renderer: 'canvas',
                    view: new ol.View({
                        projection: 'EPSG:900913',
                        center: [0, 0],
                        zoom: 2
                    })
                });
                var openStreetMapLayer = new ol.layer.Tile({
                    source: new ol.source.OSM()
                });
                meuMapa.addLayer(openStreetMapLayer);

            <% for (Posicao posicao : posicoes) {%>
                var iconFeature = new ol.Feature({
                    geometry: new ol.geom.Point([<%=posicao.getLon()%>, <%=posicao.getLat()%>]),
                    name: '<%=posicao.getLogin()%>'
                });
                var iconStyle = new ol.style.Style({
                    image: new ol.style.Icon(({
                        anchor: [0.5, 46],
                        anchorXUnits: 'fraction',
                        anchorYUnits: 'pixels',
                        opacity: 0.75,
                        src: 'dados/r2d2.png'
                    }))
                });
                iconFeature.setStyle(iconStyle);
                var vectorSource = new ol.source.Vector({
                    features: [iconFeature]
                });
                var vectorLayer = new ol.layer.Vector({
                    source: vectorSource
                });
                meuMapa.addLayer(vectorLayer);
            <% }%>

                var element = document.getElementById('popup');
                var popup = new ol.Overlay({
                    element: element,
                    positioning: 'bottom-center',
                    stopEvent: false
                });
                meuMapa.addOverlay(popup);

                meuMapa.on('click', function (evt) {
                    var feature = meuMapa.forEachFeatureAtPixel(evt.pixel,
                            function (feature, layer) {
                                return feature;
                            });
                    if (feature) {
                        popup.setPosition(evt.coordinate);
                        var xmlString;
                        var login = feature.get('name')
                        var urlString = 'http://localhost:8080/WebRest/LP3Rest/posicoes/';
                        console.log(urlString);
                        urlString = urlString.concat(login);
                        console.log(urlString);
                        $.ajax({
                            url: urlString,
                            data: {
                                format: 'html'
                            },
                            success: function (data) {
                                $(element).popover({
                                    'placement': 'top',
                                    'html': true,
                                    'content': data
                                });
                                $(element).popover('show');
                            },
                            error: function (e) {
                                console.log(e.message);
                            },
                            type: 'GET'
                        });
                    } else {
                        $(element).popover('destroy');
                    }
                });
            });
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <div id="MeuMapa" class="map"><div id="popup"></div></div>
                </div>
            </div>
        </div>
    </body>
</html>
