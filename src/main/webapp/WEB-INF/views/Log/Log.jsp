<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Page-Title -->
<div class="row">
    <div class="col-sm-12">
        <h4 class="pull-left page-title">日志</h4>
        <ol class="breadcrumb pull-right">
            <li><a href="#">回到首页</a></li>
            <li class="active">日志</li>
        </ol>
    </div>
</div>


<div class="col-lg-4">
    <div class="panel panel-border panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Pie chart</h3>
        </div>
        <!-- 展示canvas -->
        <div class="panel-body">
            <canvas id="myChart" width="300" height="200"></canvas>
        </div>
    </div>
</div>

<div class="col-lg-8">
    <div class="panel panel-border panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Bar chart</h3>
        </div>
        <div class="panel-body">
            <%--<canvas id="barChart" width="600" height="319"></canvas>--%>
            <canvas id="barChart" width="600" height="189"></canvas>
        </div>
    </div>
</div>




<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-border panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Responsive Table</h3>
            </div>
            <div class="panel-body table-rep-plugin">
                <div class="btn-toolbar">
                    <div class="btn-group focus-btn-group">
                        <button class="btn btn-default">
                            <span class="glyphicon glyphicon-screenshot">

                            </span> Focus
                        </button>
                    </div>
                    <div class="btn-group dropdown-btn-group pull-right">
                        <button class="btn btn-default">Display all</button>
                        <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">Display <span
                                class="caret">

                        </span>
                        </button>
                        <ul class="dropdown-menu">
                            <li class="checkbox-row"><input type="checkbox" name="toggle-tech-companies-1-col-1"
                                                            id="toggle-tech-companies-1-col-1"
                                                            value="tech-companies-1-col-1"> <label
                                    for="toggle-tech-companies-1-col-1">Last Trade</label></li>
                            <li class="checkbox-row"><input type="checkbox" name="toggle-tech-companies-1-col-2"
                                                            id="toggle-tech-companies-1-col-2"
                                                            value="tech-companies-1-col-2"> <label
                                    for="toggle-tech-companies-1-col-2">Trade Time</label></li>
                            <li class="checkbox-row"><input type="checkbox" name="toggle-tech-companies-1-col-3"
                                                            id="toggle-tech-companies-1-col-3"
                                                            value="tech-companies-1-col-3"> <label
                                    for="toggle-tech-companies-1-col-3">Change</label></li>
                            <li class="checkbox-row"><input type="checkbox" name="toggle-tech-companies-1-col-4"
                                                            id="toggle-tech-companies-1-col-4"
                                                            value="tech-companies-1-col-4"> <label
                                    for="toggle-tech-companies-1-col-4">Prev Close</label></li>
                            <li class="checkbox-row"><input type="checkbox" name="toggle-tech-companies-1-col-5"
                                                            id="toggle-tech-companies-1-col-5"
                                                            value="tech-companies-1-col-5"> <label
                                    for="toggle-tech-companies-1-col-5">Open</label></li>
                            <li class="checkbox-row"><input type="checkbox" name="toggle-tech-companies-1-col-6"
                                                            id="toggle-tech-companies-1-col-6"
                                                            value="tech-companies-1-col-6"> <label
                                    for="toggle-tech-companies-1-col-6">Bid</label></li>
                            <li class="checkbox-row"><input type="checkbox" name="toggle-tech-companies-1-col-7"
                                                            id="toggle-tech-companies-1-col-7"
                                                            value="tech-companies-1-col-7"> <label
                                    for="toggle-tech-companies-1-col-7">Ask</label></li>
                            <li class="checkbox-row"><input type="checkbox" name="toggle-tech-companies-1-col-8"
                                                            id="toggle-tech-companies-1-col-8"
                                                            value="tech-companies-1-col-8"> <label
                                    for="toggle-tech-companies-1-col-8">1y Target Est</label></li>
                            <li class="checkbox-row"><input type="checkbox" name="toggle-tech-companies-1-col-9"
                                                            id="toggle-tech-companies-1-col-9"
                                                            value="tech-companies-1-col-9"> <label
                                    for="toggle-tech-companies-1-col-9">Lorem</label></li>
                            <li class="checkbox-row"><input type="checkbox" name="toggle-tech-companies-1-col-10"
                                                            id="toggle-tech-companies-1-col-10"
                                                            value="tech-companies-1-col-10"> <label
                                    for="toggle-tech-companies-1-col-10">Ipsum</label></li>
                        </ul>
                    </div>
                </div>
                <div class="table-responsive" data-pattern="priority-columns">
                    <table id="tech-companies-1" class="table table-small-font table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>Company</th>
                            <th data-priority="1">Last Trade</th>
                            <th data-priority="3">Trade Time</th>
                            <th data-priority="1">Change</th>
                            <th data-priority="3">Prev Close</th>
                            <th data-priority="3">Open</th>
                            <th data-priority="6">Bid</th>
                            <th data-priority="6">Ask</th>
                            <th data-priority="6">1y Target Est</th>
                            <th data-priority="6">Lorem</th>
                            <th data-priority="6">Ipsum</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th>GOOG <span class="co-name">Google Inc.</span></th>
                            <td>597.74</td>
                            <td>12:12PM</td>
                            <td>14.81 (2.54%)</td>
                            <td>582.93</td>
                            <td>597.95</td>
                            <td>597.73 x 100</td>
                            <td>597.91 x 300</td>
                            <td>731.10</td>
                            <td colspan="2">Spanning cell</td>
                        </tr>

                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- end row -->

<!-- flot Chart -->
<script src="/assets/plugins/flot-chart/jquery.flot.js"></script>
<script src="/assets/plugins/flot-chart/jquery.flot.time.js"></script>
<script src="/assets/plugins/flot-chart/jquery.flot.tooltip.min.js"></script>
<script src="/assets/plugins/flot-chart/jquery.flot.resize.js"></script>
<script src="/assets/plugins/flot-chart/jquery.flot.pie.js"></script>
<script src="/assets/plugins/flot-chart/jquery.flot.selection.js"></script>
<script src="/assets/plugins/flot-chart/jquery.flot.stack.js"></script>
<script src="/assets/plugins/flot-chart/jquery.flot.crosshair.js"></script>

<%--<script src="/assets/js/rwd-table.min.js"></script>--%>
<script src="/assets/js/Chart.min.js"></script>

<script type="text/javascript">
    // 设置参数
    var data = {
        labels: [
            "Red",
            "Blue",
            "Yellow"
        ],
        datasets: [
            {
                data: [300, 50, 100],
                backgroundColor: [
                    "#FF6384",
                    "#36A2EB",
                    "#FFCE56"
                ],
                hoverBackgroundColor: [
                    "#FF6384",
                    "#36A2EB",
                    "#FFCE56"
                ]
            }]
    };


    // Get the context of the canvas element we want to select
    var ctx = document.getElementById("myChart").getContext("2d");
    var myPieChart = new Chart(ctx, {
        type: 'pie',
        data: data
        // options: options
    });





    var barData = {
        labels : ["星期一","星期二","星期三","星期四","星期五","星期六","星期日"],
        datasets : [
            {
                label: "异常",
                fillColor: "rgba(109,179,63,0.5)",
                strokeColor: "rgba(109,179,63,0.8)",
                highlightFill: "rgba(109,179,63,0.75)",
                highlightStroke: "rgba(109,179,63,1)",
                data: [65, 59, 80, 81, 56, 55, 40]
            },
            {
                label: "警告",
                fillColor: "rgba(151,187,205,0.5)",
                strokeColor: "rgba(151,187,205,0.8)",
                highlightFill: "rgba(151,187,205,0.75)",
                highlightStroke: "rgba(151,187,205,1)",
                data: [28, 48, 40, 19, 86, 27, 90]
            }
        ]
    };

    //Get context with jQuery - using jQuery's .get() method.
    var barChart = $("#barChart").get(0).getContext("2d");
    //This will get the first returned node in the jQuery collection.
    var mybarChart = new Chart(barChart, {
        type: 'bar',
        data: barData
        // options: options
    });

</script>

<!-- dashboard -->
<script src="/assets/plugins/dashboard/jquery.dashboard.js"></script>
