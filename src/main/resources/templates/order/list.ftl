<html>
<head>
    <meta charset="utf-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover table-condensed">
                <thead>
                <tr>
                    <th>订单ID</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>地址</th>
                    <th>金额</th>
                    <th>订单状态</th>
                    <th>支付状态</th>
                    <th>创建时间</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTOPage.content as orderDTO>
                <tr>
                    <td>${orderDTO.orderId}</td>
                    <td>${orderDTO.buyerName}</td>
                    <td>${orderDTO.buyerPhone}</td>
                    <td>${orderDTO.buyerAddress}</td>
                    <td>${orderDTO.orderAmount}</td>
                    <td>${orderDTO.getOrderStatusEnum().getMsg()}</td>
                    <td>${orderDTO.getPayStatusEnum().getMsg()}</td>
                    <td>${orderDTO.createTime}</td>
                    <td >详情</td>
                    <td >
                        <#if orderDTO.getOrderStatusEnum().getCode()==1>
                            <a href="cancel?orderId=${orderDTO.orderId}">取消</a>
                        </#if>
                        </td>

                </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-md-12 column">
            <ul class="pagination">
                <li>
                    <a href="#">Prev</a>
                </li>
                <li>
                    <a href="#">1</a>
                </li>
                <li>
                    <a href="#">2</a>
                </li>
                <li>
                    <a href="#">3</a>
                </li>
                <li>
                    <a href="#">4</a>
                </li>
                <li>
                    <a href="#">5</a>
                </li>
                <li>
                    <a href="#">Next</a>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
