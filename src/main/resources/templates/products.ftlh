<#import "common/template.ftlh" as t>
<@t.template user=user!>

<!-- Product Catagories Area Start -->
<div class="products-catagories-area clearfix">
    <div class="amado-pro-catagory clearfix">

<!--Цикл для коллекции products, где
каждое значение сохраняется в переменной product-->
<#list products as product>

        <!-- Single Catagory -->
        <div class="single-products-catagory clearfix">
            <a href="/product/${product.id}">
                <img src="/images/${product.previewImageId}" alt="">
                <!-- Hover Content -->
                <div class="hover-content">
                    <div class="line"></div>
                    <p>${product.price} руб.</p>
                    <h5>${product.title}</h5>
                </div>
            </a>
        </div>

  <#else>
   <h3>Список товаров пуст</h3>

</#list>

    <p><a href="/">Главная страница</a></p>

<hr>

</div>
</div>
<!-- Product Catagories Area End -->

</@t.template>