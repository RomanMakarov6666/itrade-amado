<#import "common/template.ftlh" as t>
<@t.template user=user!>

<!-- Product Details Area Start -->
<div class="single-product-area section-padding-100 clearfix">
    <div class="container-fluid">

        <div class="row">
            <div class="col-12">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb mt-50">
                        <li class="breadcrumb-item"><a href="#"></a></li>
                    </ol>
                </nav>
            </div>
        </div>

        <div class="row">
            <div class="col-12 col-lg-7">
                <div class="single_product_thumb">
                    <div id="product_details_slider" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <#assign x=0>
                            <#list images as image>
                            <#if x == 0>
                            <li class="active" data-target="#product_details_slider" data-slide-to="0"
                                style="background-image: url(/images/${image.id});"></li>
                            <#else>
                            <li data-target="#product_details_slider" data-slide-to="${x}"
                                style="background-image: url(/images/${image.id});"></li>
                        </#if>
                        <#assign x++>
                    </#list>
                    </ol>

                    <div class="carousel-inner">
                        <#assign x = 1>
                        <#list images as image>
                        <#if x == 1>
                        <div class="carousel-item active">
                            <a class="gallery_img" href="/images/${image.id}">
                                <img class="d-block w-100" src="/images/${image.id}" alt="Слайд">
                            </a>
                        </div>
                        <#assign x++>
                        <#else>
                        <div class="carousel-item">
                            <a class="gallery_img" href="/images/${image.id}">
                                <img class="d-block w-100" src="/images/${image.id}" alt="Слайд">
                            </a>
                        </div>
                    </#if>
                </#list>
            </div>
        </div>
    </div>
</div>

<div class="col-12 col-lg-5">
    <div class="single_product_desc">
        <div class="product-meta-data">
            <div class="line"></div>
            <p class="product-price">${product.price} руб.</p>
            <a href="product-details.html">
                <h6>${product.title}</h6>
            </a>
            <div class="ratings-review mb-15 d-flex align-items-center justify-content-between">
                <div class="ratings">
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                    <i class="fa fa-star" aria-hidden="true"></i>
                </div>
                <div class="review">
                    <a href="#">Оставить отзыв</a>
                </div>
            </div>
            <p class="avaibility"><i class="fa fa-circle"></i> В наличии</p>
        </div>

        <div class="short_overview my-5">
            <p>Все товары:
                <a href="/user/${product.user.id}">${product.user.name}</a>
            </p>
        </div>

        <div class="short_overview my-5">
            <p>${product.description}</p>
        </div>

        <#if user?? && user.id?? && product?? && product.user??>
        <#if user.id != product.user.id>
        <form action="/cart/add" method="post">
            <#if _csrf??>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </#if>
        <input type="hidden" name="productId" value="${product.id}">
        <button type="submit" class="btn amado-btn">Add to cart</button>
        </form>
        <#else>
        <form action="/product/delete/${product.id}" method="post">
            <#if _csrf??>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </#if>
        <button class="btn amado-btn" style="background:red">Удалить</button>
        </form>
    </#if>
    <#else>
    <a href="/login" class="btn amdao-btn" style="background:#ffc89c">Войдите, чтобы купить</a>
</#if>
</form>
</div>
</div>
</div>
</div>
</div>
<!-- Product Details Area End -->

</@t.template>
