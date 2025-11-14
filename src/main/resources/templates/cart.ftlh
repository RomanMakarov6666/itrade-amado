<#import "common/template.ftlh" as t>
<@t.template user=user!>

<div class="cart-table-area section-padding-100">
    <div class="container-fluid">
        <div class="row">
            <!-- Cart Products Table -->
            <div class="col-12 col-lg-8">
                <div class="cart-title mt-50">
                    <h2>Корзина пользователя ${user.name!"Не указано"}</h2>
                </div>

                <div class="cart-table clearfix">
                    <table class="table table-responsive">
                        <thead>
                        <tr>
                            <th>Фото</th>
                            <th>Название</th>
                            <th>Цена</th>
                            <th>Описание</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if products?? && products?size gt 0>
                        <#list products as product>
                        <tr>
                            <td>${product.title}</td>

                            <td class="cart_product_img">
                                <a href="/product/${product.id}">
                                    <img src="/images/${product.previewImageId!"default.png"}" alt="Товар">
                                </a>
                            </td>
                            <td class="cart_product_desc">
                                <h5>
                                    <a class="p_title2" href="/product/${product.id}">
                                        ${product.title!"Без названия"}
                                    </a>
                                </h5>
                            </td>
                            <td class="price">
                                        <span>
                                            <a class="p_title2" href="/product/${product.id}">
                                                ${product.price!"0"} руб.
                                            </a>
                                        </span>
                            </td>
                            <td class="cart_product_desc">
                                <div class="descr">
                                    <a class="p_title3" href="/product/${product.id}">
                                        ${product.description!"Нет описания"}
                                    </a>
                                </div>
                            </td>
                            <td class="cart_product_desc">
                                <span>
                                    <form action="/cart/delete" method="post">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <input type="hidden" name="id" value="${product.id}">
                                        <button class="btn amado-btn1" style="background:red">Удалить</button>
                                    </form>
                                </span>
                            </td>
                        </tr>
                        </#list>
                        <#else>
                        <tr>
                            <td colspan="4">Корзина пуста</td>
                        </tr>
                    </#if>
                    </tbody>
                    </table>
                </div>
            </div>

            <!-- User Summary -->
            <div class="col-12 col-lg-4">
                <div class="cart-summary">
                    <h5>Общая информация</h5>
                    <ul class="summary-table">
                        <#if user??>
                        <li><span>Имя:</span> <span>${user.name!"Не указано"}</span></li>
                        <li><span>Телефон:</span> <span>${user.phoneNumber!"Не указан"}</span></li>
                        <li><span>Email:</span> <span>${user.email!"Не указан"}</span></li>
                        <li><span>Стоимость:</span> <span>${costs!"0"} руб.</span></li>
                        <#else>
                        <li>Пользователь не найден</li>
                    </#if>
                    </ul>

            </div>
        </div>
    </div>
</div>
</div>

</@t.template>
